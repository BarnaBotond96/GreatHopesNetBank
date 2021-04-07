<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" type="text/css" href="userNavigation.css">
<style>
nav {
	backdrop-filter: blur(8px);
}
</style>
<header>
	<div class="container-fluid" class="visible-desktop navbar navbar-bottom">
		<div class="shadow-lg p-20 mb-0 bg-black rounded">
			<nav class="navbar navbar-expand-lg navbar-light">
				<a class="navbar-brand" style="font-size: 20px">Great Hopes NetBank |</a>
				<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarSupportedContent" style="font-size: 20px">
					<ul class="navbar-nav mr-auto">
						<li class="nav-item mx-2<c:if test="${param.activeMenuOption == 1}"> active</c:if>"><a class="nav-link" href="userHome.jsp">Főoldal</a></li>
						<li class="nav-item mx-2<c:if test="${param.activeMenuOption == 2}"> active</c:if>"><a class="nav-link" href="loadBankAccounts">Bankszámlák</a></li>
						<li class="nav-item mx-2<c:if test="${param.activeMenuOption == 3}"> active</c:if>"><a class="nav-link" href="loadUserDataForProfileEdit">Személyes adatok</a></li>
					</ul>
					<ul class="navbar-nav">
						<li class="nav-item mx-2"><a class="nav-link" href="${pageContext.request.contextPath}/logout">Kijelentkezés</a></li>
					</ul>				
				</div>
			</nav>
		</div>
	</div>
</header>

