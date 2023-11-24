
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <h1>Login Session</h1>
    <form action="http://localhost:8080/login" method="post">
        <label for="usuario">Usuario:</label>
        <br>
        <input type="text" id="usuario" name="usuario" />
        <br>
        <br>
        <label for="senha">Senha:</label>
        <br>
        <input type="password" id="senha" name="senha" />
        <br>
        <br>
        <button type="submit">Entrar</button>
    </form>
</body>
</html>