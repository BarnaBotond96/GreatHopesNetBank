package hu.javamiddle.ghnb.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hu.javamiddle.ghnb.GHNBRuntimeException;
import hu.javamiddle.ghnb.UserStatus;
import hu.javamiddle.ghnb.db.dao.UserDao;
import hu.javamiddle.ghnb.db.entity.User;
import hu.javamiddle.ghnb.db.preparedstatementwriter.user.GetUserByLoginNameAndPasswordPreparedStatementWriter;
import hu.javamiddle.ghnb.db.resultsetreader.user.FullUserResultSetReader;
import hu.javamiddle.ghnb.db.sqlbuilder.user.GetUserByLoginNameAndPasswordSqlBuilder;
import hu.javamiddle.ghnb.util.PasswordManager;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginName = request.getParameter("loginName");
		String password = request.getParameter("password");

		String passwordHash = new PasswordManager().encrypt(password);
		User user = User.builder()
			.withLoginName(loginName)
			.withPasswordHash(passwordHash)
			.build();
		UserDao userDao = new UserDao(new GetUserByLoginNameAndPasswordSqlBuilder(), new GetUserByLoginNameAndPasswordPreparedStatementWriter(), new FullUserResultSetReader());
		List<User> users = userDao.retrieve(user);
		if (users.isEmpty()) {
			request.setAttribute("invalidLoginNameOrPassword", true);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else {
			User userToLogIn = users.get(0);
			if (UserStatus.ACTIVE.equals(userToLogIn.getUserStatus())) {
				HttpSession session = request.getSession();
				session.setAttribute("loggedInUser", userToLogIn);
				String redirectTo;
				switch (userToLogIn.getRole()) {
				case ADMIN:
					redirectTo = "admin/adminHome.jsp";
					break;
				case USER:
					redirectTo = "user/userHome.jsp";
					break;
				default:
					throw new GHNBRuntimeException("Illegális szerepkör: " + userToLogIn.getRole());
				}
				response.sendRedirect(redirectTo);
			} else {
				request.setAttribute("invalidLoginNameOrPassword", true);
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}
	}

}