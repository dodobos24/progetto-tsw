<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MyTickez</title>
    <link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="./css/artista.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link
        href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap"
        rel="stylesheet">
    <link rel="icon" type="image/png" href="./img/favicon.png" />
</head>

<body>
    <%@ include file="header.jsp" %>
    <main>
        <section id="fotoArtistaGrande">
            <div class="blur">
                <div class="infoArtista">
                    <span class="fontBolder"><%= ((ArtistBean)request.getAttribute("artist")).getName() %></span>
                    <p>Descrizione artista Lorem ipsum dolor sit amet consectetur adipisicing elit. Ab officiis exercitationem, sit deleniti quas alias voluptas minus earum, distinctio at accusamus esse molestias eligendi nobis animi consequatur minima expedita itaque?</p>
                </div>
            </div>
        </section>

        <div>
            <img src="./img/TravisScot.png" alt="">
            <div class="flexDirectionColumn infoEvento">
                <span class="h3">Titolo Evento</span>
                <span class="luoghi"><ion-icon name="map"></ion-icon>Torino - Bologna - Firenze - Roma - Napoli - Messina</span>
                <span class="date"><ion-icon name="calendar-clear"></ion-icon>Dal GG/MM/AAA al Dal GG/MM/AAA</span>
            </div>
        </div>
    </main>
    <%@ include file="footer.jsp" %>
    <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
    <script src="./script/main.js"></script>
</body>

</html>
