<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="shortcut icon" href="../images/bank2.png" type="image/png">
<link rel="stylesheet" type="text/css" href="bankAccounts.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<title>GHNB | Bankszámlák</title>
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
		<h2>Bankszámlák</h2>
		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th>Bankszámlaszám</th>
					<th class="text-right">Aktuális egyenleg</th>
					<th>Devizanem</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="bankAccount" items="${bankAccounts}">
					<tr>
						<td>${bankAccount.bankAccountNumber}</td>
						<td class="text-right"><fmt:formatNumber type="number"
								groupingUsed="true" value="${bankAccount.currentBalance}" /></td>
						<td class="text-right">${bankAccount.currencyType}</td>
						<td>
							<form action="loadBankAccountDetails" method="post">
								<div class="inputBox">
									<input type="hidden" name="bankAccountNumber"
										value="${bankAccount.bankAccountNumber}">
									<button type="submit">Részletek</button>
								</div>
							</form>
						</td>
						<td>
							<form action="loadTransfer" method="post">
								<div class="inputBox">
									<input type="hidden" name="bankAccountNumber"
										value="${bankAccount.bankAccountNumber}">
									<button type="submit">Átutalás</button>
								</div>
							</form>
						</td>
						<td>
							<form action="loadTransactionHistory" method="post">
								<div class="inputBox">
									<input type="hidden" name="bankAccountNumber"
										value="${bankAccount.bankAccountNumber}">
									<button type="submit">Tranzakciós előzmények</button>
								</div>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>