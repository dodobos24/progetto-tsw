<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="model.UserBean" %>
<%@ page import="model.UserDao" %>
<%@ page import="java.sql.SQLException" %>
<%
    UserDao userDao = new UserDao();
    List<UserBean> users = null;
    String errorMessage = null;
    try {
        users = userDao.getAllUsers();
    } catch (SQLException e) {
        e.printStackTrace();
        errorMessage = "Errore nel recupero degli utenti: " + e.getMessage();
    }
%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista Utenti</title>
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="../css/users.css">
    <style>
        
    </style>
</head>
<body>
    <jsp:include page="./adminHeader.jsp" />
    <main>
        <h1>Lista degli utenti</h1>
        <% if (errorMessage != null) { %>
            <p class="error"><%= errorMessage %></p>
        <% } else if (users != null && !users.isEmpty()) { %>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Username</th>
                        <th>Email</th>
                        <th>Nome</th>
                        <th>Cognome</th>
                        <th>Saldo</th>
                        <th>Admin</th>
                        <th>Azione</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (UserBean user : users) { %>
                    <tr>
                        <td><%= user.getId() %></td>
                        <td><%= user.getUsername() %></td>
                        <td><%= user.getEmail() %></td>
                        <td><%= user.getNome() %></td>
                        <td><%= user.getCognome() %></td>
                        <td><%= user.getSaldo() %></td>
                        <td><%= user.isAdmin() ? "Sì" : "No" %></td>
                        <td>
                            <% if (user.isAdmin()) { %>
                                <form action="../AdminSiNoServlet" method="post">
								    <input type="hidden" name="userId" value="<%= user.getId() %>">
								    <input type="hidden" name="action" value="removeAdmin">
								    <button type="submit" class="btn-rimuovi">Rimuovi Admin</button>
								</form>
                            <% } else { %>
                                <form action="../AdminSiNoServlet" method="post">
								    <input type="hidden" name="userId" value="<%= user.getId() %>">
								    <input type="hidden" name="action" value="addAdmin">
								    <button type="submit" class="btn-aggiungi">Aggiungi Admin</button>
								</form>
                            <% } %>
                        </td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        <% } else { %>
            <p>Nessun utente trovato.</p>
        <% } %>
    </main>
    <jsp:include page="./adminFooter.jsp" />
</body>
</html>
