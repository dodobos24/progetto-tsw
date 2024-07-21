<%@ page import="model.UserBean" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%
    UserBean user = (session != null) ? (UserBean) session.getAttribute("currentSessionUser") : null;

    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    
    boolean isAuthenticated = (user != null);
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profilo Utente - MyTickez</title>
    <link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="./css/account.css">
    <link rel="icon" type="image/png" href="./img/favicon.png" />
    <style>
        
    </style>
</head>
<body>
	<script>
            var isAuthenticated = <%= isAuthenticated %>;
    </script>

    <jsp:include page="header.jsp" />

    <main>
        <section class="profileSection">
            <div class="profileContainer">
                <h1>Benvenuto/a, <%= user.getNome() %>!</h1>
                <div class="profileDetails">
                	<p><strong>Id:</strong> <%= user.getId() %></p>
                    <p><strong>Nome:</strong> <%= user.getNome() %></p>
                    <p><strong>Cognome:</strong> <%= user.getCognome() %></p>
                    <p><strong>Username:</strong> <%= user.getUsername() %></p>
                    <p><strong>Email:</strong> <%= user.getEmail() %></p>
                    <p><strong>Admin:</strong> <%= user.isAdmin() ? "Sì" : "No" %></p>
                </div>
                <a href="LogoutServlet" id="logoutButton">Logout</a>
        		<a href="acquisti.jsp" id="purchasesButton">Visualizza Acquisti</a>
            </div>
        </section>
    </main>

    <%@ include file="footer.jsp" %>
    <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
</body>
</html>
