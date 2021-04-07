package hu.javamiddle.ghnb.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hu.javamiddle.ghnb.db.entity.User;

public class AuthenticationFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		boolean isLoginPage = httpServletRequest.getRequestURI().endsWith("login.jsp") || httpServletRequest.getRequestURI().endsWith("login");
		boolean isRegistrationPage = httpServletRequest.getRequestURI().endsWith("loadRegistration") || httpServletRequest.getRequestURI().endsWith("validateAndSaveRegistration");
		if (loggedInUser != null || isLoginPage || isRegistrationPage) {
			chain.doFilter(request, response);
		} else {
			((HttpServletResponse) response).sendRedirect(request.getServletContext().getContextPath() + "/login.jsp");
		}
	}

}