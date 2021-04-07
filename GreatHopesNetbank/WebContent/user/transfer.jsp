<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" 	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="shortcut icon" href="../images/bank2.png" type="image/png">
<link rel="stylesheet" type="text/css" href="transfer.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<title>GHNB | Átutalás</title>
<style>
body {
	background-image: url(../images/whit.jpg);
}
</style>
</head>
<body>
	<div class="navbar">
		<jsp:include page="userNavigation.jsp" flush="true">
			<jsp:param name="activeMenuOption" value="2" />
		</jsp:include>
	</div>
	<div class="form">
		<h2>Átutalás</h2>
		<div class="input">
			<form action="saveTransfer" method="post">
				<div class="inputBox">
					<label for="bankAccountNumber">Forrás bankszámlaszáma</label> <input
						type="text" class="form-control" name="bankAccountNumber"
						id="bankAccountNumber" readonly="readonly"
						value="${param.bankAccountNumber}">
				</div>
				<div class="inputBox">
					<label for="toBankAccountNumber">Kedvezményezett
						bankszámlaszáma</label> <input type="text" class="form-control"
						name="toBankAccountNumber" id="toBankAccountNumber"
						value="${param.toBankAccountNumber}">
				</div>
				<div class="inputBox">
					<label for="beneficiaryName">Kedvezményezett neve</label> <input
						type="text" class="form-control" name="beneficiaryName"
						id="beneficiaryName" value="${param.beneficiaryName}">
				</div>
				<div class="inputBox">
					<label for="amount">Összeg</label> <input type="number"
						class="form-control<c:if test='${validationErrors.containsKey("amountTooMuch")}'> is-invalid</c:if>"
						name="amount" id="amount" value="${param.amount}">
					<c:if test='${validationErrors.containsKey("amountTooMuch")}'>
						<div class="invalid-feedback">${validationErrors.get("amountTooMuch")}</div>
					</c:if>
				</div>
				<div class="inputBox">
					<label for="currencyType">Devizanem</label> <input type="text"
						class="form-control" name="currencyType" id="currencyType"
						readonly="readonly" value="HUF">
				</div>
				<div class="inputBox">
					<label for="transactionComment">Közlemény</label>
					<textarea class="form-control" name="transactionComment"
						id="transactionComment" rows="3">${param.transactionComment}</textarea>
				</div>
				<button type="submit">Átutalás indítása</button>
			</form>
		</div>
	</div>
</body>
</html>