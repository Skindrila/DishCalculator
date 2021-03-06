## 1. Введение
Этот план был разработан для тестирования приложения "DishCalculator". Цель тестирования - проверка работоспособности и пригодности приложения для практического использования.

## 2. Объект тестирования
Объект тестирования представляет собой приложение, созданное для составления листа покупок с учетом количества людей и финансовых возможностей пользователя для платформы Android, который должен обладать следующими атрибутами качества:

1. Функциональность:
* функциональная полнота: приложение должно выполнять все заявленные функции в соответствии с SRS
* функциональная корректность: приложение должно выполнять все заявленные функции безошибочно
* функциональная целесообразность: отсутствуют не заявленные функции, которые бы мешали приложению выполнять первоначально поставленные задачи.
2. Удобство использования:
* простота пользовательского интерфейса: интерфейс должен быть достаточно простым для интуитивного использования новым пользователем.

## 3. Риски
* приложение не запустится на ОС Android версией менее Marshmallow (6.0)

## 4. Аспекты тестирования
В ходе тестирования планируется проверить реализацию основных функций приложения. К основным функциям можно отнести следующие пункты:

* создания списка
* просмотр списка
* удаление списка
* добавление продуктов в базу данных
* удаление продуктов из базы даных

### Функциональные требования:
**Создание списка**

Этот вариант использования необходимо протестировать на:
* перехды между окнами
* расчет списка
* генерацию pdf документа

**Просмотр списка**

Этот вариант использования необходимо протестировать на:
* перенаправление в Google Play на страницу Pdf Viewer (в случае если не имеется на стройстве)
* открытие файла

**Удаление списка**

Этот вариант использования необходимо протестировать на:
* удаление pdf документа

**Добавление продуктов в базу данных**

Этот вариант использования необходимо протестировать на:
* корректность добавления объекта в базу данных

**Удаление продуктов из базы данных**

Этот вариант использования необходимо протестировать на:
* корректность удаление объекта из базы данных

## 5. Подходы к тестированию
Тестирование каждого варианта использования будет проводиться ручным подходом.

## 6. Представление результатов
Результаты тестирования представлены в [таблице](https://github.com/messi3nik/DishCalculator/blob/master/Documents/TestPlan/TestResult.md).

## 7. Выводы
Данный тестовый план позволяет протестировать основной функционал приложения. Успешное прохождение всех тестов не гарантирует полной работоспособности на всех версиях платформ и архитектурах, однако позволяет полагать, что данное программное обеспечение работает корректно.