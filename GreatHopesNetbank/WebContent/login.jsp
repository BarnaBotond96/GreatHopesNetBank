<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="shortcut icon" href="images/bank2.png" type="image/png">
<link rel="stylesheet" type="text/css" href="login.css">
<title>GHNB | Bejelentkezés</title>
<style>
body {
	background-image: url(images/whit.jpg);
}

</style>
</head>
<body>
	<div class="form">
		<h2>Bejelentkezés</h2>
		<div class="input">
			<form action="login" method="post">
				<div class="inputBox">
					<label for="loginName">Felhasználónév</label> <input type="text" name="loginName" id="loginName" placeholder="">
				</div>
				<div class="inputBox">
					<label for="password">Jelszó</label> <input type="password" name="password" id="password" placeholder="">
				</div>
				<button type="submit" name="signin">Belépés</button>
			</form>
			<form action="loadRegistration" method="post">
				<button type="submit" name="registration">Regisztráció</button>
			</form>
		</div>
		<br>
		<c:if test="${invalidLoginNameOrPassword}">
			<div class="alert alert-danger alert-dismissible fade show" role="alert">
				<strong>Rossz felhasználónév vagy jelszó.</strong>
				<button type="button" class="close" data-dismiss="alert" aria-label="Bezár">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
		</c:if>
		<c:if test="${param.registrationSuccessful}">
			<div class="alert alert-warning alert-dismissible fade show" role="alert">
				<strong>Sikeres regisztráció.</strong> Az adminisztrátorok jóváhagyása után bejelentkezhet.
				<button type="button" class="close" data-dismiss="alert" aria-label="Bezár" style="width: 22px"> 
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
		</c:if>
	</div>
</body>
</html>