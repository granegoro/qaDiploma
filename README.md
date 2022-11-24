### Документация

[План автоматизации тестирования](https://github.com/granegoro/qaDiploma/blob/main/docs/Plan.md)

[Отчет о проведенном тестировании](https://github.com/granegoro/qaDiploma/blob/main/docs/Report.md)

[Отчет об автоматизации](https://github.com/granegoro/qaDiploma/blob/main/docs/Summary.md)

# Процедура запуска автотестов

## Подготовка окружения
Необходимо установить следующее ПО:
1. [Java](https://adoptium.net/temurin/releases/?utm_source=pocket_reader&version=11)
2. [Docker Desktop](https://www.docker.com/products/docker-desktop/)
3. [Intellij IDEA](https://www.jetbrains.com/idea/download/)


## Запуск приложения
1. Клонировать репозиторий с проектом в Intellij IDEA
2. Поднять контейнер с базой данных, введя в терминале команду:
```
docker-compose up
```
3. Запустить симулятор банковских сервисов, предварительно переместившись в 
каталог `gate-simulator`. Запуск осуществляется командой:
```
npm start
```
4. Запустить SUT командой:
```
java -jar artifacts/aqa-shop.jar
```

Приложение будет доступно по адресу http://localhost:8080/

## Запуск тестов
Дважды нажать клавишу Ctrl, в открывшемся окне ввести команду:
```
./gradlew clean test
```
По умолчанию будет выполнено подключение к СУБД MySQL.
Для подключения к PostgreSQL необходимо при запуске тестов передать параметр:
```
./gradlew test -Ddb.url=jdbc:postgresql://localhost:5432/app
```

## Формирование Allure Report
Дважды нажать клавишу Ctrl, в открывшемся окне ввести команду:
```
./gradlew allureServe
```
