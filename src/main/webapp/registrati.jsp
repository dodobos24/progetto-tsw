<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="./css/login.css">
    <link rel="icon" type=”image/png” href="./img/favicon.png" />
</head>
<body class="flexCenter">
    <form class="flexCenter flexDirectionColumn gap" id="registrati" action="" method="post">
        <input class="buttonShadow" name="usarname" type="text" placeholder="Usarname" required>
        <input class="buttonShadow" name="email" type="email" placeholder="E-mail" required>
        <input class="buttonShadow" id="passwordRegistrati" name="password" type="password" placeholder="Password" required>
        <input class="buttonShadow" id="ripetiPasswordRegistrati"type="password" placeholder="Ripeti password" required>
        <input class="buttonShadow button hoverRotate2" name="registrati" type="submit" value="Login">
        <span>Sei gi� registrato? <a href="login.jsp">Accedi</a></span>
    </form>
    <script src="./script/main.js"></script>
</body>
</html>
