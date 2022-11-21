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
Параметры для запуска контейнеров 
содержатся в файле 'docker-compose.yml'. Необходимо предварительно указать в файле application.properties учётные 
данные и URL для подключения к необходимой СУБД
4. Запустить симулятор банковских сервисов, предварительно переместившись в 
каталог 'gate-simulator'. Запуск осуществляется командой:
```
npm start
```
5. Запустить SUT командой:
```
java -jar artifacts/aqa-shop.jar
```

Приложение будет доступно по адресу http://localhost:8080/

## Запуск тестов
Дважды нажать клавишу Ctrl, в открывшемся окне ввести команду:
```
./gradlew clean test
```

## Формирование Allure Report
Дважды нажать клавишу Ctrl, в открывшемся окне ввести команду:
```
./gradlew allureServe
```