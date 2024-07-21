<%@ page import="java.util.List" %>
<%@ page import="model.UserBean" %>
<%@ page import="model.DatabaseUtility" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <title>I tuoi Acquisti - MyTickez</title>
    <link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="./css/tickets.css">
    <link rel="icon" type="image/png" href="./img/favicon.png" />
    <style>
        .ticketsTable {
            width: 100%;
            border-collapse: collapse;
        }

        .ticketsTable th, .ticketsTable td {
            border: 1px solid #ddd;
            padding: 8px;
        }

        .ticketsTable th {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: left;
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <script>
        var isAuthenticated = <%= isAuthenticated %>;
    </script>

    <%@ include file="header.jsp" %>

    <main>
        <section class="ticketsSection">
            <div class="ticketsContainer">
                <h1>I tuoi acquisti</h1>
                <%
                    try (Connection connection = DatabaseUtility.getConnection()) {
                        String query = "SELECT tickets.ticket_id, tickets.event_id, tickets.user_id, events.event_name, events.event_date, tickets.purchase_date " +
                                       "FROM tickets " +
                                       "JOIN events ON tickets.event_id = events.event_id " +
                                       "WHERE tickets.user_id = ?";
                        try (PreparedStatement statement = connection.prepareStatement(query)) {
                            statement.setInt(1, user.getId());
                            ResultSet resultSet = statement.executeQuery();

                            if (!resultSet.isBeforeFirst()) {
                                out.println("<p>Non hai effettuato alcun acquisto.</p>");
                            } else {
                                out.println("<table class='ticketsTable'>");
                                out.println("<thead>");
                                out.println("<tr>");
                                out.println("<th>ID Biglietto</th>");
                                out.println("<th>Nome Evento</th>");
                                out.println("<th>Data Evento</th>");
                                out.println("<th>Data Acquisto</th>");
                                out.println("</tr>");
                                out.println("</thead>");
                                out.println("<tbody>");
                                while (resultSet.next()) {
                                    int ticketId = resultSet.getInt("ticket_id");
                                    String eventName = resultSet.getString("event_name");
                                    String eventDate = resultSet.getString("event_date");
                                    String purchaseDate = resultSet.getString("purchase_date");

                                    out.println("<tr>");
                                    out.println("<td>" + ticketId + "</td>");
                                    out.println("<td>" + eventName + "</td>");
                                    out.println("<td>" + eventDate + "</td>");
                                    out.println("<td>" + purchaseDate + "</td>");
                                    out.println("</tr>");
                                }
                                out.println("</tbody>");
                                out.println("</table>");
                            }
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                        out.println("<p>Si è verificato un errore nel recupero degli acquisti.</p>");
                    }
                %>
            </div>
        </section>
    </main>

    <%@ include file="footer.jsp" %>
    <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
    <script src="./script/main.js"></script>
</body>
</html>
