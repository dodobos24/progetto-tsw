<body>
    <header>
        <div class="logo_nav">
            <div onclick="window.location.href = 'index.jsp'" class="logo flexCenter hoverRotate2">
                <img src="../img/icon.png" alt="logo">
            </div>
        
            <div class="nav">
                <span onclick="window.location.href = '../index.jsp'" class="" id="home">Home</span>
                <span onclick="window.location.href = '../musica.jsp'" id="musica">Musica</span>
                <span onclick="window.location.href = '../festival.jsp'" id="festival">Festival</span>
                <span onclick="window.location.href = '../teatro.jsp'" id="teatro">Teatro</span>
                <span onclick="window.location.href = '../arte.jsp'" id="arte">Arte</span>
                <span onclick="window.location.href = '../sport.jsp'" id="sport">Sport</span>
                <span onclick="window.location.href = '../addEvents.jsp'" id="add-events-button" class="admin-only">Aggiungi Eventi</span>
            </div>
        </div>

        <div class="carrello_account">
            <span class="carrello flexCenter">
                <ion-icon name="cart-sharp" id="cartIcon"></ion-icon>
            </span>
            <span class="account flexCenter">
                <ion-icon name="person-sharp" id="accountIcon"></ion-icon>
            </span>
        </div>
    </header>

    <script>
        document.addEventListener('DOMContentLoaded', function() {
            var accountIcon = document.getElementById('accountIcon');
            var cartIcon = document.getElementById('cartIcon');
            var isAuthenticated = window.isAuthenticated || false;

            accountIcon.addEventListener('click', function() {
                if (isAuthenticated) {
                    window.location.href = 'account.jsp';
                } else {
                    window.location.href = 'login.jsp';
                }
            });
            
            cartIcon.addEventListener('click', function() {
                if (isAuthenticated) {
                    window.location.href = 'cart.jsp';
                } else {
                	window.location.href = 'login.jsp';
                    alert('Non sei autenticato. Per favore, accedi per visualizzare il carrello.');
                }
            });
        });
    </script>
</body>
