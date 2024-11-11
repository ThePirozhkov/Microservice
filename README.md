Пэт-проект: Работа с микросервисами

-->Transfer Microservices<--
Установка:
Для начала работы запустите файл docker-compose.yml и все основные контейнеры запустятся

Все запросы отправлять на http://localhost:9099/transfer

Форма запроса JSON:
{
    "senderId": "любое ID",
    "receiverId": "любое ID",
    "amount": любое кол-во
}

Данный пэт-проект используется для демонстрации работы микросервисов и их взаимодействии

-->UserMicroservice<--
Установка:
Для начала работы запустите UserMicroserviceApplication, в docker-compose включить контейнеры с Redis и Postgres

Все запросы отправлять на http://localhost:8080/api/users

Форма запроса JSON:
{
    "verified": false,
    "email": "уникальный любой email",
    "password": "любой пароль",
    "login": "уникальный любой логин"
}

Данный модуль используется для демонстрации работы с базами данных Postgres/Redis и кэширования
