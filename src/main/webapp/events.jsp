<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Eventi - <%= request.getAttribute("eventType") %></title>
</head>
<body>
    <h1>Eventi di <%= request.getAttribute("eventType") %></h1>
    <%
        java.util.List<model.EventBean> events = (java.util.List<model.EventBean>) request.getAttribute("events");
        if (events != null && !events.isEmpty()) {
    %>
        <ul>
            <%
                for (model.EventBean event : events) {
            %>
                <li><%= event.getName() %> - <%= event.getDate() %> - <%= event.getVenue() %></li>
            <%
                }
            %>
        </ul>
    <%
        } else {
    %>
        <p>Nessun evento disponibile per questa categoria.</p>
    <%
        }
    %>
</body>
</html>

