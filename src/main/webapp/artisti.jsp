<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.ArtistBean" %>
<%@ page import="model.ArtistDao" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MyTickez</title>
    <link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="./css/artisti.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
    <link rel="icon" type="image/png" href="./img/favicon.png" />
</head>
<body>
    <%@ include file="header.jsp" %>
    <main>
        <section class="allScreen firstSection">
            <div class="logoCentrale flexCenter">
                <span id="my">My</span><span id="tickez">Tickez</span>
            </div>
        </section>

        <section class="containerArtisti flexCenter flexDirectionColumn gap">
            <div class="fontBolder h2">Artisti</div>
            <%
                ArtistDao artistDao = new ArtistDao();
                List<ArtistBean> artists = artistDao.getAllArtists();
                if (artists != null) {
                    for (ArtistBean artist : artists) {
            %>
                <span>
                    <a href="#"><img src="./img/<%= artist.getName().replace(" ", "") %>.png" alt="<%= artist.getName() %>">
                        <span><%= artist.getName() %></span>
                    </a>
                </span>
            <%
                    }
                }
            %>
        </section>
    </main>
    <%@ include file="footer.jsp" %>
    <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
    <script src="./script/main.js"></script>
</body>
</html>
