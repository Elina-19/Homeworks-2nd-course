<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Sign In</title>
</head>
<body>
<div>
    <#if error??>
        ${error}
    </#if>
</div>
<form method="post" action="${rc.getContextPath()}/signIn">
    <input name="email" placeholder="Email" value="${(form.email)!}">
    <br>
    <input name="password" placeholder="Password" value="${(form.password)!}">
    <br>
    <input type="submit" value="Sign In">
</form>
</body>
</html>
