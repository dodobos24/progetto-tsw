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
                    <p><strong>Saldo:</strong> <%= user.getSaldo() %> $</p>
                    <p><strong>Admin:</strong> <%= user.isAdmin() ? "Sì" : "No" %></p>
                </div>
                <a href="LogoutServlet" id="logoutButton">Logout</a>
        		<a href="tickets.jsp" id="purchasesButton">Visualizza Acquisti</a>
        		<button id="openModalButton">Ricarica Saldo</button>
                <!-- Modale -->
				<div id="quantityModal" class="modal">
				    <div class="modal-content">
				        <div class="modal-header">
				            <h2 id="modalTitle">Inserisci Importo</h2>
				            <span class="close" onclick="closeModal()">&times;</span>
				        </div>
				        <div class="modal-body">
				            <form id="modalForm" method="post" action="RicaricaSaldoServlet">
				                <label id="inputLabel" for="inputField">Importo:</label>
				                <input type="number" id="inputField" name="amount" min="1" step="0.01" required>
				                <div class="modal-footer">
				                    <button type="submit">Conferma</button>
				                </div>
				            </form>
				        </div>
				    </div>
				</div>

            </div>
        </section>
    </main>

    <%@ include file="footer.jsp" %>
    <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
    <script>
	    // Funzione per aprire il modale
	    function openModal(action) {
	        var modal = document.getElementById("quantityModal");
	        var modalTitle = document.getElementById("modalTitle");
	        var formAction = document.getElementById("modalForm");
	        var inputLabel = document.getElementById("inputLabel");
	        var inputField = document.getElementById("inputField");
	
	        if (action === 'recharge') {
	            modalTitle.innerText = 'Ricarica Saldo';
	            inputLabel.innerText = 'Importo:';
	            inputField.setAttribute('type', 'number');
	            inputField.setAttribute('name', 'amount');
	            inputField.setAttribute('min', '1');
	            inputField.setAttribute('step', '0.01');
	            inputField.setAttribute('required', 'required');
	            formAction.setAttribute('action', 'RicaricaSaldoServlet');
	        }
	
	        modal.style.display = 'block';
	    }
	
	    // Funzione per chiudere il modale
	    function closeModal() {
	        var modal = document.getElementById("quantityModal");
	        modal.style.display = 'none';
	    }
	
	    // Chiudi il modale se clicchi fuori dal contenuto
	    window.onclick = function(event) {
	        var modal = document.getElementById("quantityModal");
	        if (event.target === modal) {
	            closeModal();
	        }
	    }
	
	    // Aggiungi listener al pulsante
	    document.getElementById("openModalButton").addEventListener("click", function() {
	        openModal('recharge');
	    });
	</script>
    
</body>
</html>
