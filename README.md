# libs:common-dto

Общий модуль DTO для всех сервисов TeamTask (`api-gateway`, `auth-service`,
`task-service`, `project-service`, `calendar-service`, `meeting-service`,
`notification-service`, `user-service`). Модуль не разворачивается отдельно —
подключается как Gradle-зависимость (`implementation(project(":libs:common-dto"))`).

Пакет: `com.teamtask.dto`

## Структура

```
com.teamtask.dto/
├── Identified.kt          # базовый интерфейс: val id: Identifier
├── Identifier.kt          # типобезопасная обёртка над id (см. "Открытые вопросы")
├── Timestamp.kt           # базовый интерфейс: createdAt / updatedAt: Instant
│
├── UserDto.kt
├── TaskDto.kt
├── ProjectDto.kt
├── MeetingDto.kt
├── CalendarEventDto.kt
├── DayViewDto.kt
├── NotificationSettingsDto.kt
│
└── requests/
    ├── CreateTaskRequest.kt / UpdateTaskRequest.kt / UpdateTaskStatusRequest.kt
    ├── CreateProjectRequest.kt / UpdateProjectRequest.kt
    ├── InviteMemberRequest.kt / UpdateMemberRoleRequest.kt
    ├── CreateMeetingRequest.kt / UpdateMeetingRequest.kt
    ├── RsvpRequest.kt / UpdateMeetingNotesRequest.kt
    ├── CreateCalendarEventRequest.kt / UpdateCalendarEventRequest.kt
    ├── RegisterUserRequest.kt / UpdateUserRequest.kt
    └── RegisterFcmTokenRequest.kt
```

**Правило разделения**: response-DTO (то, что сервис отдаёт клиенту) лежат в
корне пакета. Request-DTO (то, что сервис принимает во входящих запросах)
лежат в `requests/`. Один и тот же объект никогда не используется в обе
стороны — см. "Response vs Request" ниже.

## Базовые интерфейсы

```kotlin
interface Identified {
    val id: Identifier
}

interface Timestamp {
    val createdAt: Instant
    val updatedAt: Instant
}
```

Response-DTO с собственным `id` и датами реализуют оба интерфейса:

```kotlin
data class TaskDto(
    override val id: Identifier,
    override val createdAt: Instant,
    override val updatedAt: Instant,
    val title: String,
    // ...
) : Identified, Timestamp
```

Request-DTO (`CreateTaskRequest`, `UpdateTaskRequest` и т.д.) эти интерфейсы
**не** реализуют — id и даты либо ещё не существуют (Create), либо
проставляются сервером, а не клиентом (см. обсуждение `createdAt`/`updatedAt`
в чате: они не должны быть editable через API).

## Конвенции модуля

1. **Все DTO — `data class` с `val`-полями.** Иммутабельность, thread-safety,
   `copy()` вместо мутации. DTO никогда не редактируются напрямую — сервис
   читает значения из request-DTO и применяет их к mutable Entity в своём
   слое персистентности (Entity здесь не хранится — она уже в каждом сервисе).

2. **Response vs Request — разные классы**, даже если поля во многом
   совпадают:
    - Response: вложенные объекты там, где клиенту нужно отображать данные
      без дополнительных запросов (`TaskDto.assignees: List<UserPublicDto>`,
      а не `List<Identifier>`).
    - Request: только id на входе (`CreateTaskRequest.assigneeIds: List<Identifier>`),
      клиент не присылает чужие профили.
    - Update-DTO — все поля nullable с `= null` по умолчанию; `null` значит
      "не менять", в отличие от пустого значения, которое значит "явно
      сбросить" (актуально для списков вроде `assigneeIds`).

3. **Даты — `java.time.Instant`, не `LocalDateTime`.** Хранит момент на
   шкале UTC без завязки на часовой пояс; конвертация в локальное время
   пользователя происходит на клиенте или в `calendar-service` через
   `ZoneId.of(user.timezone)`. Исключение: `Project.dueDate` и подобные
   "просто дата без времени" поля — там `LocalDate`.

4. **Enum-подобные поля — `String` + Jakarta Validation**, а не отдельный
   Kotlin-тип с константами:
   ```kotlin
   @field:Pattern(regexp = "todo|in_progress|in_review|done|cancelled")
   val status: String
   ```
   Обязательно с `@field:` use-site target — иначе аннотация в Kotlin
   `data class` цепляется к параметру конструктора, а не к полю, и Bean
   Validation её не увидит.

5. **`recurrenceRule`, `checklist`, `notificationSettings` и подобные
   "сложные" поля** не хранятся как сырой JSON/строка на уровне DTO —
   `checklist: List<ChecklistItemDto>`, `notificationSettings: NotificationSettingsDto`
   и т.д. Конвертация в/из JSONB — забота сервиса-владельца таблицы, не
   этого модуля.

## Зависимости модуля

```kotlin
dependencies {
    implementation(kotlin("stdlib"))
    implementation("jakarta.validation:jakarta.validation-api:3.1.0")
    testImplementation(kotlin("test"))
}
```

`spring-boot-starter-validation` сюда **не** подключается — это рантайм-
зависимость, нужна только в сервисах, которые реально валидируют
(`task-service`, `auth-service` и т.д.), не в общей библиотеке DTO.

## `Identifier`

```kotlin
public typealias Identifier = String
```

Это `typealias`, не отдельный тип — на уровне компилятора `Identifier` и
`String` полностью взаимозаменяемы. Компилятор **не** поймает ошибку, если
где-то случайно передать `taskId` туда, где ожидается `userId`: обе — просто
`String`. Практический эффект typealias — только читаемость сигнатур
(`fun findTask(id: Identifier)` понятнее, чем `fun findTask(id: String)`),
никакой рантайм- или compile-time защиты это не добавляет.