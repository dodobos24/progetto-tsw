<%@ page import="java.util.List" %>
<%@ page import="model.UserBean" %>
<%@ page import="model.DatabaseUtility" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="model.EventBean" %>
<%@ page import="model.EventDao" %>
<%@ page import="model.EventDaoInterface" %>

<%
    EventDao eventDao = new EventDao();
    List<EventBean> teatroEvents = null;
    try {
    	teatroEvents = eventDao.getEventsByType("teatro");
    } catch (SQLException e) {
        e.printStackTrace();
    }
    System.out.println("Events retrieved: " + teatroEvents.size());
%>

<%
    UserBean user = (session != null) ? (UserBean) session.getAttribute("currentSessionUser") : null;
    boolean isAuthenticated = (user != null);
    boolean isAdmin = isAuthenticated && user.isAdmin();
%>

<!DOCTYPE html>
<html data-page="teatro">
<head>
    <title>MyTickez</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
    <link rel="icon" type="image/ico" href="./img/favicon.ico" />
    <link rel="stylesheet" href="./css/style.css">
</head>
<body>
    <script>
        var isAuthenticated = <%= isAuthenticated %>;
        var isAdmin = <%= isAdmin %>;
    </script>

	
    
    <%@ include file="header.jsp" %>
    <main>
        <section class="allScreen firstSection">
            <div class="logoCentrale flexCenter">
                <span id="my">My</span><span id="tickez">Tickez</span>
            </div>
        </section>

        <h2 align="center">Eventi teatrali</h2>

        <section class="eventsSection">
            <div class="eventsContainer">
                <%
                    if (teatroEvents != null && !teatroEvents.isEmpty()) {
                        for (EventBean event : teatroEvents) {
                %>
                    <div class="eventCard">
                        <div class="eventImage">
                            <% if (event.getImage() != null) { %>
                                <img src="<%= event.getImage() %>" alt="<%= event.getName() %>">
                            <% } %>
                        </div>
                        <div class="eventDetails">
                            <h3><%= event.getName() %></h3>
                            <p class="eventDate">Data: <%= event.getDate().toString() %></p>
                            <p class="eventVenue">Luogo: <%= event.getVenue() %></p>
                            <p class="eventDescription">Descrizione: <%= event.getDescription() %></p>
                            <p class="eventOrganizer">Organizzatore: <%= event.getOrganizer() %></p>
                            <p class="eventPrice">Prezzo: <%= event.getPrice() %> $</p>
                            <button onclick="openModal(<%= event.getId() %>)" id="BuyButton">Aggiungi al carrello</button>
                            <% if(isAdmin) { %>
                            <form action="DeleteEventServlet" method="get" onsubmit="setReturnUrl()">
							    <input type="hidden" name="eventId" value="<%= event.getId() %>">
							    <input type="hidden" id="returnUrl" name="returnUrl">
							    <button type="submit" style="background-color: red; color: white; margin-top: 10px;">Elimina</button>
							</form>
							<% } %>
                        </div>
                    </div>
                <%
                        }
                    } else {
                %>
                    <p>Nessun evento trovato.</p>
                <%
                    }
                %>
            </div>
        </section>
    </main>

    <!-- Modal -->
    <div id="quantityModal" class="modal">
        <div class="modal-content">
            <div class="modal-header">
                <h2>Inserisci Quantità</h2>
                <span class="close" onclick="closeModal()">&times;</span>
            </div>
            <div class="modal-body">
                <form id="addToCartForm" method="post" action="CarrelloServlet?action=addToCart">
                    <input type="hidden" id="eventId" name="eventId">
                    <label for="quantity">Quantità: </label>
                    <input type="number" id="quantity" name="quantity" min="1" required>
                    <div class="modal-footer">
                        <button type="submit">Conferma</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <%@ include file="footer.jsp" %>
    <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
    <script src="./script/main.js"></script>
    
    <script>
        function openModal(eventId) {
            document.getElementById('eventId').value = eventId;
            document.getElementById('quantityModal').style.display = "block";
        }

        function closeModal() {
            document.getElementById('quantityModal').style.display = "none";
        }

        function addToCart() {
            var eventId = document.getElementById('eventId').value;
            var quantity = document.getElementById('quantity').value;
            if (quantity > 0) {
                window.location.href = 'CarrelloServlet?action=addToCart&eventId=' + eventId + '&quantity=' + quantity;
            }
        }

        function deleteEvent(eventId) {
            if (confirm('Sei sicuro di voler eliminare questo evento?')) {
                window.location.href = 'EventServlet?action=delete&eventId=' + eventId;
            }
        }

        window.onclick = function(event) {
            if (event.target === document.getElementById('quantityModal')) {
                closeModal();
            }
        }
        
        function setReturnUrl() {
            var returnUrl = window.location.pathname + window.location.search;
            console.log("Return URL set to: " + returnUrl); // Debug
            document.getElementById('returnUrl').value = returnUrl;
        }
        
    </script>
</body>
</html>
