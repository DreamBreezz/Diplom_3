# Diplom_3

Третья часть дипломного проекта курса QA Java Automation в Яндекс.Практикум.

Цель проекта — создать UI-тесты для сайта
[Stellar Burgers](https://stellarburgers.nomoreparties.site/).

## Использованные технологии
* Java 11
* Maven 3.8
* JUnit 4
* Rest Assured 5.3.0
* Allure 2.20.0
* Selenium 4.19.1
* WebDriverManager 5.7.0
* Gson 2.8.9
* JavaFaker 1.0.2

## Настройка проекта
Не требуется.

## Запуск тестов в Chrome

```bash
mvn test
```

## Запуск тестов в Yandex.Browser
Предварительно скорректируйте:
* browser.version = версия chrome driver, подходящая к вашему Яндекс.Браузеру
* webdriver.yandex.bin = путь к исполнительному файлу Яндекс.Браузера на вашем устройстве
```bash
mvn -Dbrowser=yandex -Dbrowser.version=122.0.6261.94 -Dwebdriver.yandex.bin="/Applications/Yandex.app/Contents/MacOS/Yandex" test
```
Либо используйте собственный способ.

## Открытие отчёта Allure

```bash
mvn allure:serve
```
