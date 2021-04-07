package hu.javamiddle.ghnb.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hu.javamiddle.ghnb.db.dao.UserDao;
import hu.javamiddle.ghnb.db.entity.User;
import hu.javamiddle.ghnb.db.preparedstatementwriter.user.GetUserByEmailPreparedStatementWriter;
import hu.javamiddle.ghnb.db.preparedstatementwriter.user.GetUserByLoginNameAndPasswordPreparedStatementWriter;
import hu.javamiddle.ghnb.db.preparedstatementwriter.user.UpdateUserPreparedStatementWriter;
import hu.javamiddle.ghnb.db.resultsetreader.user.FullUserResultSetReader;
import hu.javamiddle.ghnb.db.sqlbuilder.user.GetUserByEmailSqlBuilder;
import hu.javamiddle.ghnb.db.sqlbuilder.user.GetUserByLoginNameAndPasswordSqlBuilder;
import hu.javamiddle.ghnb.db.sqlbuilder.user.UpdateUserSqlBuilder;
import hu.javamiddle.ghnb.util.PasswordManager;
import hu.javamiddle.ghnb.util.StringUtil;

public class SaveProfileEditScreen extends HttpServlet {

	private static final String LOGGED_IN_USER = "loggedInUser";
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newPassword = request.getParameter("newPassword");
		String newPasswordConfirmation = request.getParameter("newPasswordConfirmation");
		String oldPassword = request.getParameter("oldPassword");
		String address = request.getParameter("address").trim();
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String newsletterAsString = request.getParameter("newsletter");
		boolean newsletter = newsletterAsString != null;

		User loggedInUser = (User) request.getSession().getAttribute(LOGGED_IN_USER);

		PasswordManager passwordManager = new PasswordManager();
		Map<String, String> validationErrors = new HashMap<>();

		if (newPassword != null && !newPassword.equals(newPasswordConfirmation)) {
			validationErrors.put("passwordDoesNotMatchWithConfirmation", "Nem egyeznek a jelszavak.");
		}

		if (!StringUtil.isBlank(newPassword) && !newPassword.matches(".{6,}+")) {
			validationErrors.put("invalidPassword", "Rövid jelszó.");
		}

		if (StringUtil.isBlank(oldPassword)) {
			validationErrors.put("oldPasswordBlank", "A jelenlegi jelszó üres.");
		} else {
			UserDao userDao = new UserDao(new GetUserByLoginNameAndPasswordSqlBuilder(), new GetUserByLoginNameAndPasswordPreparedStatementWriter(), new FullUserResultSetReader());
			List<User> users = userDao.retrieve(loggedInUser);
			loggedInUser = users.get(0);
			String oldPasswordHash = passwordManager.encrypt(oldPassword);
			if (!loggedInUser.getPasswordHash().equals(oldPasswordHash)) {
				validationErrors.put("oldPasswordWrong", "A jelenlegi jelszó helytelen.");
			}
		}

		if (!phone.matches(".[0-9]{2}\\s+[0-9]{2}\\s+[0-9]{3}\\s+[0-9]{4}")) {
			validationErrors.put("phoneWrong", "Érvénytelen telefonszám.");
		}

		if (!address.matches("\\d{4}+.\\s+[a-zA-Z]+,\\s.{1,}")) {
			validationErrors.put("addressWrong", "Érvénytelen cím.");
		}

		UserDao queryUserDao = new UserDao(new GetUserByEmailSqlBuilder(), new GetUserByEmailPreparedStatementWriter(), new FullUserResultSetReader());
		List<User> retrievedUserEmails = queryUserDao.retrieve(User.builder().withEmail(email).build());

		if (!retrievedUserEmails.isEmpty() && !email.equals(loggedInUser.getEmail())) {
			validationErrors.put("emailExists", "Az email cím már létezik.");

		}
		if (!email.matches("[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}")) {
			validationErrors.put("wrongEmail", "Érvénytelen email cím.");
		}

		if (validationErrors.isEmpty()) {
			UserDao retrieveUserDao = new UserDao(new GetUserByLoginNameAndPasswordSqlBuilder(), new GetUserByLoginNameAndPasswordPreparedStatementWriter(), new FullUserResultSetReader());
			List<User> users = retrieveUserDao.retrieve(loggedInUser);
			loggedInUser = users.get(0);
			loggedInUser.setPasswordHash(newPassword == null ? loggedInUser.getPasswordHash() : passwordManager.encrypt(newPassword));
			loggedInUser.setPostalAddress(address);
			loggedInUser.setPhone(phone);
			loggedInUser.setEmail(email);
			loggedInUser.setNewsletter(newsletter);
			UserDao updateUserDao = new UserDao(new UpdateUserSqlBuilder(), new UpdateUserPreparedStatementWriter());
			updateUserDao.update(loggedInUser);
			HttpSession session = request.getSession();
			session.setAttribute(LOGGED_IN_USER, loggedInUser);
			response.sendRedirect("loadUserDataForProfileEdit?saveSuccessful");
		} else {
			request.setAttribute("validationErrors", validationErrors);
			request.getRequestDispatcher("loadUserDataForProfileEdit").forward(request, response);
		}
	}

}