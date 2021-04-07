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

import hu.javamiddle.ghnb.Role;
import hu.javamiddle.ghnb.db.entity.User;

public class AdminAuthorizationFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		if (loggedInUser != null && Role.ADMIN.equals(loggedInUser.getRole())) {
			chain.doFilter(request, response);
		} else {
			((HttpServletResponse) response).sendRedirect(request.getServletContext().getContextPath() + "/login.jsp");
		}
	}

}