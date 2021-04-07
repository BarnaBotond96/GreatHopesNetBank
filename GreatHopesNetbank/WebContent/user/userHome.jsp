<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="shortcut icon" href="../images/bank2.png" type="image/png">
<link rel="stylesheet" type="text/css" href="userHome.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<title>GHNB | Főoldal</title>
<style>
.carousel-inner img {
	width: 100%;
	height: 100%;
}

body {
	background-image: url(../images/whit.jpg);
	background-repeat: round;
}
</style>
</head>
<body>
	<div class="navbar">
		<jsp:include page="userNavigation.jsp" flush="true">
			<jsp:param name="activeMenuOption" value="1" />
		</jsp:include>
	</div>
	<div>
		<div class="welcome">
			<h4>Üdvözöljük ${loggedInUser.firstName}!</h4>
		</div>
		<br>
		<c:if
			test='${param.message != null && param.message.equals("successfulTransfer")}'>
			<div class="alert alert-success alert-dismissible fade show" role="alert">
				<strong>Az átutalási megbízást a bank sikeresen megkapta!</strong>
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
		</c:if>
		<br>
		<div id="demo" class="carousel slide" data-ride="carousel">
			<ul class="carousel-indicators">
				<li data-target="#demo" data-slide-to="0" class="active"></li>
				<li data-target="#demo" data-slide-to="1"></li>
				<li data-target="#demo" data-slide-to="2"></li>
				<li data-target="#demo" data-slide-to="3"></li>
			</ul>
			<div class="carousel-inner">
				<div class="carousel-item active">
					<img src="../images/bank3.jpg" alt="bankImage1" width="1100" height="500">
					<div class="carousel-caption">
						<h3>
							<em>Királyi Posta Takarékpénztár</em>
						</h3>
					</div>
				</div>
				<div class="carousel-item">
					<img src="../images/bank4.jpg" alt="bankImage2" width="1100" height="500">
					<div class="carousel-caption">
						<h3>
							<em>Gresham Palota</em>
						</h3>
					</div>
				</div>
				<div class="carousel-item">
					<img src="../images/parlament.jpg" alt="bankImage3" width="1100" height="500">
					<div class="carousel-caption">
						<h3>
							<em>Parlament</em>
						</h3>
					</div>
				</div>
				<div class="carousel-item">
					<img src="../images/chainb.jpg" alt="bankImage4" width="1100" height="500">
					<div class="carousel-caption">
						<h3>
							<em>Chain Bridge</em>
						</h3>
					</div>
				</div>
			</div>
			<a class="carousel-control-prev" href="#demo" data-slide="prev">
				<span class="carousel-control-prev-icon"></span>
			</a> <a class="carousel-control-next" href="#demo" data-slide="next">
				<span class="carousel-control-next-icon"></span>
			</a>
		</div>
		<br>
		<div class="ln">
			<div class="left">
				<div class="card" style="width: 400px">
					<img class="card-img-top" src="../images/bitcoincard.jpg"
						alt="Card image" style="width: 100%">
					<div class="card-body">
						<h4 class="card-title" style="color: #1f1f1f">
							<em>Engedélyezi a PayPal a kriptopénzek használatát a
								hálózatán</em>
						</h4>
						<p class="card-text">Első körben az amerikai ügyfeleknek lesz
							lehetősége digitális valutával kereskedni az online platformon.</p>
					</div>
				</div>
			</div>
			<br>
			<div class="middle">
				<div class="card" style="width: 400px">
					<img class="card-img-top" src="../images/bitcoin.jpg"
						alt="Card image" style="width: 100%">
					<div class="card-body">
						<h4 class="card-title" style="color: #1f1f1f">
							<em>A bitcoin beelőzte a világ két legnagyobb óriásának
								összértékét</em>
						</h4>
						<p class="card-text">A kriptovaluta összértéke ma már
							magasabb, mint a Visa és a Mastercard együttes piaci értéke (871
							milliárd dollár).</p>
					</div>
				</div>
			</div>
			<br>
			<div class="right">
				<div class="card" style="width: 400px">
					<img class="card-img-top" src="../images/smsvirus2.jpg"
						alt="Card image" style="width: 100%">
					<div class="card-body">
						<h4 class="card-title" style="color: #1f1f1f">
							<em>A bankok segítenek, ha adathalászok csapdájába esünk</em>
						</h4>
						<p class="card-text">Az olyan támadások esetén, mint az
							ál-csomagküldésről szóló sms-ek, egyből érdemes zárolni a
							bankszámlánkat.</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	<br>
</body>
</html>