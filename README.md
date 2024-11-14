# Пэт-проект: Работа с микросервисами

<h2>Описание проекта</h2>
<h3>Проект состоит из нескольких модулей:</h3>
<li><b>Core</b></li>
<p>Содержит java-файлы, репозитории, DTO и т.д., используемые в нескольких модулях.</p>
<li><b>DepositMicroservice</b></li>
<p>Модуль отвечающий за "депозит средств" в проекте.</p>
<li><b>MockWebService</b></li>
<p>Модуль делающий вид, что что-то обрабатывает на удаленном сервисе.</p>
<li><b></b>PaymentTransferMicroservice</li>
<p>Модуль отправляющий Deposit, Withdrawal эвенты на Kafka сервер</p>
<li><b>WithdrawalMicroservice</b></li>
<p>Модуль отвечающий за "списание средств" в проекте.</p>
<li><b>EurekaServer</b></li>
<p>Модуль содержащий в себе Eureka Server, регистрирующий микросервисы.</p>
<li><b>Gateway</b></li>
<p>Гейтвей для отправки на него запросов.</p>
<li><b>UserMicroservice</b></li>
<p>Отдельный от всех модуль, который умеет кэшировать запросы, используется для получения пользователей из БД.</p>
<h2>Интеграция в Spring Cloud</h2>
<p>Микросервисы PaymentTransferMiroservice и UserMicroservice связаны с помощью EurekaServer.</p>
<p>Все запросы отправляются на единый URL:</p>
`<p>http://localhost:8888</p>`
<p>Для PaymentTransferMicroservice на URL:</p>
`<p>http://localhost:8888/transfer</p>`
