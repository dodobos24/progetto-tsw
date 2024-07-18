<HTML data-page="arte">

<HEAD>
	<title>MyTickez</title>
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
	<link rel="icon" type=”image/ico” href="./img/favicon.ico" />
	<link rel="stylesheet" href="./css/style.css">
</HEAD>   

<BODY>
	<%@ include file="header.jsp" %>
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
		
		<section class="allScreen flexCenter noBorder flexDirectionColumn sliderSection" >
			<p class="fontBolder h2">DI TENDENZA</p>
			<div id="slider">
				<div id="slides">
					<div class="slide slide1">
						<div class="blur">
							<div class="info">
								<p>Nome Artista</p>
								<p>01 Gennaio 2023</p>
								<p>Luogo</p>
							</div>
						</div>
					</div>
					<div class="slide" id="slide2">
						<div class="blur">
							<div class="info">
								<p>Nome Artista</p>
								<p>01 Gennaio 2023</p>
								<p>Luogo</p>
							</div>
						</div>
					</div>
					<div class="slide" id="slide3">
						<div class="blur">
							<div class="info">
								<p>Nome Artista</p>
								<p>01 Gennaio 2023</p>
								<p>Luogo</p>
							</div>
						</div>
					</div>
					<div class="slide" id="slide4"> 
						<div class="blur">
							<div class="info">
								<p>Nome Artista</p>
								<p>01 Gennaio 2023</p>
								<p>Luogo</p>
							</div>
						</div>
					</div>
					<div class="slide" id="slide5">
						<div class="blur">
							<div class="info">
								<p>Nome Artista</p>
								<p>01 Gennaio 2023</p>
								<p>Luogo</p>
							</div>
						</div>
					</div>
					<div class="slide slide1">
						<div class="blur">
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
	</main>
	<%@ include file="footer.jsp" %>
	<script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
	<script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
	<script src="./script/main.js"></script>
</BODY>

</HTML>