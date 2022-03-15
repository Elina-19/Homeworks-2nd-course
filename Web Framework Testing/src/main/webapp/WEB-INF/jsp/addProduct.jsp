<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add product</title>
</head>
<body>
<h1>Add product</h1>
<form action="/addProduct" method="POST">
    <div>
        <label for="name" class="form-label">Name</label>
        <input id="name" class="form-control" name="name" placeholder="Name">
    </div>
    <div>
        <label for="dateOfCreating" class="form-label">Date of creating</label>
        <input id="dateOfCreating" name="dateOfCreating" placeholder="Date of creating">
    </div>
    <div>
        <label for="password" class="form-label">Expiration date</label>
        <input id="password" name="expirationDate" placeholder="Expiration date">
    </div>
    <br>
    <input type="submit" class="btn" value="Сохранить">
</form>
</body>
</html>
