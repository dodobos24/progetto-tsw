<%@ page session="false" %>
<%@ page import="model.UserBean" %>
<%
    HttpSession session = request.getSession(false);
    UserBean user = (session != null) ? (UserBean) session.getAttribute("currentSessionUser") : null;
    boolean isAuthenticated = (user != null);
    boolean isAdmin = isAuthenticated && user.isAdmin();
%>
<!DOCTYPE html>
<html data-page="home">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MyTickez</title>
    <link rel="stylesheet" href="./css/style.css">
    <link rel="icon" type="image/png" href="./img/favicon.png" />
</head>
<body>
    <script>
        var isAuthenticated = <%= isAuthenticated %>;
        var isAdmin = <%= isAdmin %>;
    </script>
    
    <jsp:include page="header.jsp" />
    <main>
        <section class="allScreen firstSection">
            <div class="logoCentrale flexCenter">
                <span id="my">My</span><span id="tickez">Tickez</span>
            </div>
        </section>

        <section class="unTerzoScreen firstUnTerzoScreen">
            <form class="flexCenter gap" action="" method="get" id="cerca">
                <div class="input flexCenter gap">
                    <span class="flexCenter">
                        <ion-icon name="map"></ion-icon>
                        <select class="buttonShadow noBorder" name="dove" id="dove">
                            <option value="" disabled selected>Dove</option>
                        </select>
                    </span>
                    <span class="flexCenter">
                        <ion-icon name="calendar-clear"></ion-icon>
                        <input class="buttonShadow noBorder" type="date" name="quando" id="quando" placeholder="Quando">
                    </span>
                    <span class="flexCenter">
                        <ion-icon name="server"></ion-icon>
                        <select class="buttonShadow noBorder" name="categoria" id="categoria">
                            <option value="" disabled selected>Categoria</option>
                            <option value="musica">Musica</option>
                            <option value="festival">Festival</option>
                            <option value="teatro">Teatro</option>
                            <option value="arte">Arte</option>
                            <option value="sport">Sport</option>
                        </select>
                    </span>
                    <span class="flexCenter">
                        <ion-icon name="brush"></ion-icon>
                        <select class="buttonShadow noBorder" name="artista" id="artista">
                            <option value="" disabled selected>Artista</option>
                        </select>
                    </span>
                </div>
                <div class="submit flexCenter">
                    <input class="buttonShadow button hoverRotate2" type="submit" value="Cerca">
                </div>
            </form>
        </section>

        <section class="allScreen flexCenter noBorder flexDirectionColumn gap">
            <p class="fontBolder h2">PROSSIMI EVENTI</p>
            <div id="slider">
                <div id="slides">
                    <div class="slide slide1">
                        <div class="blur">
                            <div class="categoria">Musica</div>
                            <div class="info">
                                <p>Nome Artista</p>
                                <p>01 Gennaio 2023</p>
                                <p>Luogo</p>
                            </div>
                        </div>
                    </div>
                    <div class="slide" id="slide2">
                        <div class="blur">
                            <div class="categoria">Festival</div>
                            <div class="info">
                                <p>Nome Artista</p>
                                <p>01 Gennaio 2023</p>
                                <p>Luogo</p>
                            </div>
                        </div>
                    </div>
                    <div class="slide" id="slide3">
                        <div class="blur">
                            <div class="categoria">Teatro</div>
                            <div class="info">
                                <p>Nome Artista</p>
                                <p>01 Gennaio 2023</p>
                                <p>Luogo</p>
                            </div>
                        </div>
                    </div>
                    <div class="slide" id="slide4">
                        <div class="blur">
                            <div class="categoria">Arte</div>
                            <div class="info">
                                <p>Nome Artista</p>
                                <p>01 Gennaio 2023</p>
                                <p>Luogo</p>
                            </div>
                        </div>
                    </div>
                    <div class="slide" id="slide5">
                        <div class="blur">
                            <div class="categoria">Sport</div>
                            <div class="info">
                                <p>Nome Artista</p>
                                <p>01 Gennaio 2023</p>
                                <p>Luogo</p>
                            </div>
                        </div>
                    </div>
                    <div class="slide slide1">
                        <div class="blur">
                            <div class="categoria">Musica</div>
                            <div class="info">
                                <p>Nome Artista</p>
                                <p>01 Gennaio 2023</p>
                                <p>Luogo</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <section class="unTerzoScreen secondUnTerzoScreen flexCenter flexDirectionColumn gap">
            <p class="fontBolder h2">ARTISTI</p>
            <div class="flexCenter">
                <span onclick="window.location.href = 'artista.jsp?artista=travis scott'" class="buttonShadow"></span>
                <span onclick="window.location.href = 'artista.jsp?artista=drake'" class="buttonShadow"></span>
                <span onclick="window.location.href = 'artista.jsp?artista=blanco'" class="buttonShadow"></span>
                <span onclick="window.location.href = 'artista.jsp?artista=Sfera Ebbasta'" class="buttonShadow"></span>
            </div>
            <a class="buttonShadow button hoverRotate1" href="artisti.jsp">Vedi tutti</a>
        </section>

        <%
            if (isAdmin) {
        %>
            <nav class="adminNav">
                <ul>
                    <li><button class="buttonShadow button hoverRotate1" onclick="window.location.href='admin/addEvents.jsp'">Aggiungi Eventi</button></li>
                    <li><button class="buttonShadow button hoverRotate1" onclick="window.location.href='admin/users.jsp'">Visualizza Utenti</button></li>
                </ul>
            </nav>
        <%
            }
        %>

    </main>
    <jsp:include page="footer.jsp" />
    <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
    <script src="./script/main.js"></script>
</body>
</html>