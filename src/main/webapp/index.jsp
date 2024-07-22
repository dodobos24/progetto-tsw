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