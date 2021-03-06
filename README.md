# “Rubiks”
Веб приложение для просмотра различных кубиков-Рубика и добавления их в закладки. Пользователи
могут регистрироваться и сохранять в закладки понравившийся кубик. Администратор может добавлять
кубики и редактировать их, а так же блокировать кубики и пользователей.
### Роли:
1. #### Администратор.
   - Блокировка пользователя.
   - Редактирование собственного профиля.
   - Блокировка кубика
   - Просмотр кубиков
   - Просмотр пользователей
   - Редактирование кубика
   - Добавление кубика
   - Сохранение кубик в закладки
1. #### Пользователь(зарегистрированный)
   - Редактирование собственного профиля.
   - Просмотр всех своих закладок.
   - Просмотр всех кубиков.
   - Добавление в закладки
1. #### Пользователь(не зарегистрированный) 
   - Просмотр всех кубиков.
   - Просмотр подробного описания кубика.
   - Регистрация в системе.
### Страницы:
1. #### Contact Us.
  - Контакная информация компании (адрес и геолокация).
2. #### Каталог.
  - Вывод всех доступных кубиков.
  - Поиск кубика по производителю.
  - Поиск кубика по форме.
  - Поиск кубика по размерности.
  - Поиск кубика по диапозону цены.
  - Поиск кубика по модели.
3. #### Rubik.
  - Подробная информация о кубике.
  - Сохранить (зарегистрированный пользователь).
  - Редактирование(администратор).
4. #### Вход в систему.
    - ##### Обязательные поля.
      - Логин пользователя.
      - Пароль пользователя.
5. #### Регистрация.
   - ##### Обязательные поля.
      - Логин.
      - Пароль.
      - Почта.
      - Мобильный телефон.
6. #### Профиль пользователя.
    - Информация о пользователе.
    - Смена пароля.
7. #### Закладки (зарегистрированный пользователь).
    - Вывод всех кубиков, сохранённых пользователем.
8. #### Добавление кубика (администратор).
    - ##### Обязательные поля.
      - Модель кубика.
      - Цена.
      - Вес.
      - Информация.
      - Размерность.
      - Производитель.
      - Форма.
      - Цветовая схема.
      - Выбор картинок.
9. #### Редактирование кубика (администратор).
    - ##### Обязательные поля.
      - Модель кубика.
      - Цена.
      - Вес.
      - Информация.
      - Размерность.
      - Производитель.
      - Форма.
      - Цветовая схема.
10. #### Пользователи (администратор).
    - Поиск пользователей по никнейму.
    - Вывод всех пользователей.
    - Блокировка пользователя.

### Схема базы данных.
   ![Database scheme](https://github.com/egoist301/-JavaST_2019_july/blob/master/rubik/web/img/database.PNG)
