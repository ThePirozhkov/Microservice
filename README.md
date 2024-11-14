# Пэт-проект: Работа с микросервисами

## Описание проекта
### Проект состоит из нескольких модулей:
- **Core** - Содержит java-файлы, репозитории, DTO и т.д., используемые в нескольких модулях.
- **DepositMicroservice** - Модуль отвечающий за "депозит средств" в проекте.
- **MockWebService** - Модуль делающий вид, что что-то обрабатывает на удаленном сервисе.
- **PaymentTransferMicroservice** - Модуль отправляющий Deposit, Withdrawal эвенты на Kafka сервер.
- **WithdrawalMicroservice** - Модуль отвечающий за "списание средств" в проекте.
- **EurekaServer** - Модуль содержащий в себе Eureka Server, регистрирующий микросервисы.
- **Gateway** - Гейтвей для отправки на него запросов.
- **UserMicroservice** - Отдельный от всех модуль, который умеет кэшировать запросы, используется для получения пользователей из БД.
## Интеграция в Spring Cloud
Микросервисы **PaymentTransferMiroservice** и **UserMicroservice** связаны с помощью **EurekaServer**.
Все запросы отправляются на единый URL:
`http://localhost:8888`\
**Для PaymentTransferMicroservice на URL:**\
`http://localhost:8888/transfer` **POST**\
**Для UserMicroservice на URL:**\
Общие запросы: `http://localhost:8888/api/users` **GET,PUT,POST**\
Запрос для определенного пользователя: `http://localhost:8888/api/users/{id}` **GET**
## Docker-compose
Содержатся все компоненты для функционирования проекта: Redis, Postgres и pgAdmin, Kafka сервера и Kafka UI.
## Подробная информация для запуска
  1. В конфигурации запуска приложений **DepositMicroservice**,**PaymentTransferMicroservice**,**WithdrawalMicroservice**,**EurekaServer**,**Gatewat**,**UserMicroservice**
     указать профиль запуска Spring - **"dev".**
  2. Запустить в **docker-compose** сервисы: **kafka_server1**,**kafka_server2**,**kafka_server3**,**postgres**,**redis**, по желанию **kafka-ui**, **pgadmin**.
  3. Запустить **EurekaServer**.
  4. Запустить **все микросервисы**.
  5. Запустить **Gateway**.
  6. Микросервисное мини-приложение запущено.
## JSON для отправки на сервисы
**Для PaymentTransferMicroservice:**
```
{
  "senderId": "абсолютно любой ID",
  "receiverId": "абсолютно любой ID",
  "amount": "любое количество" 
}
```

**Для UserMicroservice:**
```
{
    "verified": false,
    "email": "любой уникальный email",
    "password": "абсолютно любой ID",
    "login": "любой уникальный логин"
}
```
## Данные для авторизации в сервисах
### Postgres
URL: **`http://localhost:5432`**
Порт: **`5432`**
Пользователь: **postgres**
Пароль: **admin**
### pgAdmin
URL: **`http://localhost:5050`**
### Kafka UI
URL: **`http://localhost:8090`**
