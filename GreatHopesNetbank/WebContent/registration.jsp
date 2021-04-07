<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="shortcut icon" href="images/bank2.png" type="image/png">
<link rel="stylesheet" type="text/css" href="registration.css">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<title>GHNB | Regisztráció</title>
<style>
body {
	background-image: url(images/whit.jpg);
}
</style>
</head>
<body>
	<div class="form">
		<h2>Regisztráció</h2>
		<div class="input">
			<form action="validateAndSaveRegistration" method="post">
				<div class="inputBox">
					<label for="loginName">Felhasználónév</label> 
					<input type="text" class="form-control<c:if test='${validationErrors.containsKey("loginNameValidationResult") || validationErrors.containsKey("loginNameExists")}'> is-invalid</c:if>" name="loginName" id="loginName" placeholder="" value="${param.loginName}">
					<c:if test='${validationErrors.containsKey("loginNameValidationResult")}'>
						<div class="invalid-feedback">${validationErrors.get("loginNameValidationResult")}</div>
					</c:if>
					<c:if test='${validationErrors.containsKey("loginNameExists")}'>
						<div class="invalid-feedback">${validationErrors.get("loginNameExists")}</div>
					</c:if>
				</div>
				<div class=ln>
					<div class="inputBox">
						<div class="lastName">
							<label for="lastName">Vezetéknév</label> 
							<input type="text" class="form-control" name="lastName" placeholder="" value="${param.lastName}">
						</div>
					</div>
					<div class="inputBox">
						<div class="firstName">
							<label for="firstName">Keresztnév</label> 
							<input type="text" class="form-control" name="firstName" id="firstName" placeholder="" value="${param.firstName}">
						</div>
					</div>
				</div>
				<div class=ln>
					<div class="inputBox">
						<div class=pw1>
							<label for="password">Jelszó</label> 
							<input type="password" class="form-control<c:if test='${validationErrors.containsKey("invalidPassword") || validationErrors.containsKey("passwordDoesNotMatchWithConfirmation")}'> is-invalid</c:if>" name="password" id="password" placeholder="">
							<c:if test='${validationErrors.containsKey("invalidPassword")}'>
								<div class="invalid-feedback">${validationErrors.get("invalidPassword")}</div>
							</c:if>
							<c:if test='${validationErrors.containsKey("passwordDoesNotMatchWithConfirmation")}'>
								<div class="invalid-feedback">${validationErrors.get("passwordDoesNotMatchWithConfirmation")}</div>
							</c:if>
						</div>
					</div>
					<div class="inputBox">
						<div class=pw2>
							<label for="passwordConfirmation">Jelszó megerősítés</label> 
							<input type="password" class="form-control<c:if test='${validationErrors.containsKey("invalidPassword") || validationErrors.containsKey("passwordDoesNotMatchWithConfirmation")}'> is-invalid</c:if>" name="passwordConfirmation" id="passwordConfirmation" placeholder="">
							<c:if test='${validationErrors.containsKey("invalidPassword")}'>
								<div class="invalid-feedback">${validationErrors.get("invalidPassword")}</div>
							</c:if>
							<c:if test='${validationErrors.containsKey("passwordDoesNotMatchWithConfirmation")}'>
								<div class="invalid-feedback">${validationErrors.get("passwordDoesNotMatchWithConfirmation")}</div>
							</c:if>
						</div>
					</div>
				</div>
				<div class="inputBox">
					<label for="roleId">Szerepkör</label> 
					<select name="roleId" id="roleId">
						<option value="0" <c:if test='${param.roleId == null || param.roleId == 0}'>selected="selected"</c:if>></option>
						<c:forEach var="userRole" items="${userRoles}">
							<option value="${userRole.id}" <c:if test='${param.roleId != null && param.roleId == userRole.id}'>selected="selected"</c:if>>${userRole.displayName}</option>
						</c:forEach>
					</select>
				</div>
				<div class="inputBox">
					<label for="address">Cím</label> 
					<input type="text" class="form-control<c:if test='${validationErrors.containsKey("wrongAddress")}'> is-invalid</c:if>" name="address" id="address" placeholder="1038. Budapest, Fürdő utca 2." value="${param.address}">
					<c:if test='${validationErrors.containsKey("wrongAddress")}'>
						<div class="invalid-feedback">${validationErrors.get("wrongAddress")}</div>
					</c:if>	
				</div>
				<div class="inputBox">
					<label for="email">E-mail cím</label> 
					<input type="email" class="form-control<c:if test='${validationErrors.containsKey("wrongEmail") || validationErrors.containsKey("emailExists")}'> is-invalid</c:if>" name="email" id="email" placeholder="példa@domain.org" value="${param.email}">
					<c:if test='${validationErrors.containsKey("wrongEmail")}'>
						<div class="invalid-feedback">${validationErrors.get("wrongEmail")}</div>
					</c:if>
					<c:if test='${validationErrors.containsKey("emailExists")}'>
						<div class="invalid-feedback">${validationErrors.get("emailExists")}</div>
					</c:if>				
				</div>
				<div class=ln>
					<div class="inputBox">
						<div class="phone">
							<label for="phone">Telefonszám</label> 
							<input type="text" class="form-control<c:if test='${validationErrors.containsKey("wrongPhone")}'> is-invalid</c:if>" name="phone" id="phone" placeholder="+36 30 123 4567" value="${param.phone}">
							<c:if test='${validationErrors.containsKey("wrongPhone")}'>
								<div class="invalid-feedback">${validationErrors.get("wrongPhone")}</div>
							</c:if>	
						</div>
					</div>
					<div class="inputBox">
						<div class="birth">
							<label for="dateOfBirth">Születési dátum</label> 
							<input type="text" class="form-control<c:if test='${validationErrors.containsKey("invalidDateOfBirth")}'> is-invalid</c:if>" name="dateOfBirth" id="dateOfBirth" placeholder="2001. 01. 01." value="${param.dateOfBirth}">
							<c:if test='${validationErrors.containsKey("invalidDateOfBirth")}'>
								<div class="invalid-feedback">${validationErrors.get("invalidDateOfBirth")}</div>
							</c:if>	
						</div>
					</div>
				</div>
				<div class="inputBox">
					<button type="submit">Regisztráció</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>