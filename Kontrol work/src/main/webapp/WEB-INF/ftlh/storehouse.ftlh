<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Товары</title>
</head>
<body>
<form method="post" action="/storehouse/1/supply">
    <div>
        <label for="name">Название</label>
        <input id="name" name="name">
        <br>
        <label for="price">Цена</label>
        <input id="price" name="price">
        <br>
        <label for="quantity">Количество</label>
        <input id="quantity" name="quantity">
        <br>
        <input class="btn" type="submit" value="Сохранить">
    </div>
</form>
</body>
</html>
