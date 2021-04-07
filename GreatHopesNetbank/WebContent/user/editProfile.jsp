<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="shortcut icon" href="../images/bank2.png" type="image/png">
<link rel="stylesheet" type="text/css" href="editProfile.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<title>GHNB | Személyes adatok</title>
<style>
body {
	background-image: url(../images/whit.jpg);
	background-repeat: round;
}
</style>
</head>
<body>
	<div class="navbar">
		<jsp:include page="userNavigation.jsp" flush="true">
			<jsp:param name="activeMenuOption" value="3" />
		</jsp:include>
	</div>
	<div class="form">
		<h2>Személyes adatok</h2>

		<div class="input">
			<form action="saveProfile" method="post">
				<div class="inputBox">
					<label for="loginName">Bejelentkezési név</label> <input
						type="text" class="form-control" name="loginName" id="loginName"
						value="${loggedInUser.loginName}" readonly="readonly">
				</div>
				<div class="inputBox">
					<label for="lastName">Vezetéknév</label> <input type="text"
						class="form-control" name="lastName" id="lastName"
						value="${loggedInUser.lastName}" readonly="readonly">
				</div>
				<div class="inputBox">
					<label for="firstName">Keresztnév</label> <input type="text"
						class="form-control" name="firstName" id="firstName"
						value="${loggedInUser.firstName}" readonly="readonly">
				</div>
				<div class="inputBox">
					<label for="oldPassword">Régi jelszó</label> <input type="password"
						class="form-control<c:if test='${validationErrors.containsKey("oldPasswordWrong") || validationErrors.containsKey("oldPasswordBlank")}'> is-invalid</c:if>"
						name="oldPassword" id="oldPassword">
					<c:if test='${validationErrors.containsKey("oldPasswordWrong")}'>
						<div class="invalid-feedback">${validationErrors.get("oldPasswordWrong")}</div>
					</c:if>
					<c:if test='${validationErrors.containsKey("oldPasswordBlank")}'>
						<div class="invalid-feedback">${validationErrors.get("oldPasswordBlank")}</div>
					</c:if>
				</div>
				<div class="inputBox">
					<label for="newPassword">Új jelszó</label> <input type="password"
						class="form-control<c:if test='${validationErrors.containsKey("invalidPassword") || validationErrors.containsKey("passwordDoesNotMatchWithConfirmation")|| validationErrors.containsKey("oldPasswordMatchWithNewPassword")}'> is-invalid</c:if>"
						name="newPassword" id="newPassword">
					<c:if test='${validationErrors.containsKey("invalidPassword")}'>
						<div class="invalid-feedback">${validationErrors.get("invalidPassword")}</div>
					</c:if>
					<c:if
						test='${validationErrors.containsKey("passwordDoesNotMatchWithConfirmation")}'>
						<div class="invalid-feedback">${validationErrors.get("passwordDoesNotMatchWithConfirmation")}</div>
					</c:if>
					<c:if
						test='${validationErrors.containsKey("oldPasswordMatchWithNewPassword")}'>
						<div class="invalid-feedback">${validationErrors.get("oldPasswordMatchWithNewPassword")}</div>
					</c:if>
					<div class="inputBox">
						<label for="newPasswordConfirmation">Új jelszó megerősítés</label>
						<input type="password"
							class="form-control<c:if test='${validationErrors.containsKey("invalidPassword") || validationErrors.containsKey("passwordDoesNotMatchWithConfirmation") || validationErrors.containsKey("oldPasswordMatchWithNewPassword")}'> is-invalid</c:if>"
							name="newPasswordConfirmation" id="newPasswordConfirmation">
						<c:if test='${validationErrors.containsKey("invalidPassword")}'>
							<div class="invalid-feedback">${validationErrors.get("invalidPassword")}</div>
						</c:if>
						<c:if
							test='${validationErrors.containsKey("passwordDoesNotMatchWithConfirmation")}'>
							<div class="invalid-feedback">${validationErrors.get("passwordDoesNotMatchWithConfirmation")}</div>
						</c:if>
						<c:if
							test='${validationErrors.containsKey("oldPasswordMatchWithNewPassword")}'>
							<div class="invalid-feedback">${validationErrors.get("oldPasswordMatchWithNewPassword")}</div>
						</c:if>
					</div>
				</div>

				<div class="inputBox">
					<label for="address">Levelezési cím</label> <input type="text"
						class="form-control<c:if test='${validationErrors.containsKey("addressWrong")}'> is-invalid</c:if>"
						name="address" id="address" value="${loggedInUser.postalAddress}">
					<c:if test='${validationErrors.containsKey("addressWrong")}'>
						<div class="invalid-feedback">${validationErrors.get("addressWrong")}</div>
					</c:if>
				</div>
				<div class="inputBox">
					<label for="phone">Telefonszám</label> <input type="text"
						class="form-control<c:if test='${validationErrors.containsKey("phoneWrong")}'> is-invalid</c:if>"
						name="phone" id="phone" value="${loggedInUser.phone}">
					<c:if test='${validationErrors.containsKey("phoneWrong")}'>
						<div class="invalid-feedback">${validationErrors.get("phoneWrong")}</div>
					</c:if>
				</div>
				<div class="inputBox">
					<label for="email">E-mail cím</label> <input type="email"
						class="form-control<c:if test='${validationErrors.containsKey("emailExists") || validationErrors.containsKey("wrongEmail")}'> is-invalid</c:if>"
						name="email" id="email" value="${loggedInUser.email}">
					<c:if test='${validationErrors.containsKey("emailExists")}'>
						<div class="invalid-feedback">${validationErrors.get("emailExists")}</div>
					</c:if>
					<c:if test='${validationErrors.containsKey("wrongEmail")}'>
						<div class="invalid-feedback">${validationErrors.get("wrongEmail")}</div>
					</c:if>
				</div>
				<div class="inputBox">
					<label for="dateOfBirth">Születési dátum</label> <input type="text"
						class="form-control" name="dateOfBirth" id="dateOfBirth"
						value="${loggedInUser.dateOfBirth}" readonly="readonly">
				</div>
				<div class="inputBox">
					<button type="submit">Mentés</button>
				</div>
				<c:if test="${saveSuccessful}">
					<div class="alert alert-success alert-dismissible fade show my-3"
						role="alert">
						Az adatok mentése sikeresen megtörént.
						<button type="button" class="close" data-dismiss="alert"
							aria-label="Bezár">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
				</c:if>
			</form>
		</div>
	</div>
</body>
</html>