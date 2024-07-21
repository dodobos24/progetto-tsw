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
        out.println("<p>User not logged in.</p>");
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
    <title>Il tuo Carrello - MyTickez</title>
    <link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="./css/cart.css">
    <link rel="icon" type="image/png" href="./img/favicon.png" />
</head>
<body>
	<script>
        var isAuthenticated = <%= isAuthenticated %>;
    </script>

    <%@ include file="header.jsp" %>
    <main>
        <section class="firstSection">
            <div class="cartContainer">
                <h1>Il tuo Carrello</h1>
                <%
                    try (Connection connection = DatabaseUtility.getConnection()) {
                        String query = "SELECT events.event_id, events.event_name, events.event_date, events.price, events.venue, artists.artist_name, cartitems.quantity " +
                                       "FROM events " +
                                       "JOIN cartitems ON events.event_id = cartitems.event_id " +
                                       "JOIN carts ON cartitems.cart_id = carts.cart_id " +
                                       "JOIN users ON carts.user_id = users.user_id " +
                                       "LEFT JOIN artists ON events.artist_id = artists.artist_id " +
                                       "WHERE users.user_id = ? " +
                                       "GROUP BY events.event_id, events.event_name, events.event_date, events.price, events.venue, artists.artist_name, cartitems.quantity";
                        try (PreparedStatement statement = connection.prepareStatement(query)) {
                            statement.setInt(1, user.getId());
                            ResultSet resultSet = statement.executeQuery();

                            // Debugging result count
                            int rowCount = 0;

                            out.println("<table class='cartTable'>");
                            out.println("<thead>");
                            out.println("<tr>");
                            out.println("<th>Evento</th>");
                            out.println("<th>Data</th>");
                            out.println("<th>Prezzo per unità</th>");
                            out.println("<th>Luogo</th>");
                            out.println("<th>Artista</th>");
                            out.println("<th>Quantità</th>");
                            out.println("<th>Totale</th>");
                            out.println("<th>Azioni</th>");
                            out.println("</tr>");
                            out.println("</thead>");
                            out.println("<tbody>");
                            while (resultSet.next()) {
                                rowCount++;
                                int eventId = resultSet.getInt("event_id");
                                String eventName = resultSet.getString("event_name");
                                String eventDate = resultSet.getString("event_date");
                                float price = resultSet.getFloat("price");
                                String venue = resultSet.getString("venue");
                                String artistName = resultSet.getString("artist_name");
                                int quantity = resultSet.getInt("quantity");

                                float totalPrice = quantity * price;

                                out.println("<tr>");
                                out.println("<td>" + eventName + "</td>");
                                out.println("<td>" + eventDate + "</td>");
                                out.println("<td>" + price + " €</td>");
                                out.println("<td>" + venue + "</td>");
                                out.println("<td>" + (artistName != null ? artistName : "N/A") + "</td>");
                                out.println("<td>" + quantity + "</td>");
                                out.println("<td>" + totalPrice + " €</td>");
                                out.println("<td>");
                                out.println("<a href='CarrelloServlet?action=removeFromCart&eventId=" + eventId + "'>Rimuovi</a>");
                                out.println("</td>");
                                out.println("</tr>");
                            }
                            out.println("</tbody>");
                            out.println("</table>");

                            if (rowCount == 0) {
                                out.println("<p>Il tuo carrello è vuoto.</p>");
                            }
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                        out.println("<p>Si è verificato un errore nel recupero del carrello.</p>");
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
