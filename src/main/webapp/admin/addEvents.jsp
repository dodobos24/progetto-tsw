<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin - Aggiungi eventi</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../css/login.css">
    <link rel="icon" type="€image/png"€ href="./img/favicon.png" />
</head>
<body class="flexCenter">
    <form class="flexCenter flexDirectionColumn gap" id="addEvents" action="../AddEventsServlet" method="post">
	    <input class="buttonShadow" name="name" type="text" placeholder="Nome" required>
	    <input class="buttonShadow" name="date" type="text" placeholder="Data" required>
	    <input class="buttonShadow" name="venue" type="text" placeholder="Luogo" required>
	    <textarea class="buttonShadow" name="description" placeholder="Descrizione" required></textarea>
	    <select id="type" name="type" class="buttonShadow" required>
	        <option value="" disabled selected>Tipo</option>
	        <option value="Musica">Musica</option>
	        <option value="Festival">Festival</option>
	        <option value="Teatro">Teatro</option>
	        <option value="Arte">Arte</option>
	        <option value="Sport">Sport</option>
	    </select>
	    <input class="buttonShadow" name="organizer" type="text" placeholder="Organizzatore" required>
	    <input class="buttonShadow" name="price" type="text" placeholder="Prezzo" required>
	    <input class="buttonShadow" name="artist_id" type="text" placeholder="Artista(id)" required>
	    <input class="buttonShadow" name="image" type="text" placeholder="Immagine(url)">
	    <input class="buttonShadow button hoverRotate2" type="submit" value="Aggiungi">
	</form>
    
    <script src="./script/main.js"></script>
</body>
</html>
