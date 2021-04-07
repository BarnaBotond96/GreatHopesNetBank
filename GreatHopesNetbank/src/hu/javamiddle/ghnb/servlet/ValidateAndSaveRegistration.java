package hu.javamiddle.ghnb.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hu.javamiddle.ghnb.Role;
import hu.javamiddle.ghnb.UserStatus;
import hu.javamiddle.ghnb.db.dao.UserDao;
import hu.javamiddle.ghnb.db.entity.User;
import hu.javamiddle.ghnb.db.preparedstatementwriter.user.CreateUserPreparedStatementWriter;
import hu.javamiddle.ghnb.db.preparedstatementwriter.user.GetUserByEmailPreparedStatementWriter;
import hu.javamiddle.ghnb.db.preparedstatementwriter.user.GetUserByLoginNamePreparedStatementWriter;
import hu.javamiddle.ghnb.db.resultsetreader.user.FullUserResultSetReader;
import hu.javamiddle.ghnb.db.sqlbuilder.user.CreateUserSqlBuilder;
import hu.javamiddle.ghnb.db.sqlbuilder.user.GetUserByEmailSqlBuilder;
import hu.javamiddle.ghnb.db.sqlbuilder.user.GetUserByLoginNameSqlBuilder;
import hu.javamiddle.ghnb.util.PasswordManager;

public class ValidateAndSaveRegistration extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginName = request.getParameter("loginName");
		String lastName = request.getParameter("lastName");
		String firstName = request.getParameter("firstName");
		String password = request.getParameter("password");
		String passwordConfirmation = request.getParameter("passwordConfirmation");
		String roleIdAsString = request.getParameter("roleId");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String dateOfBirthAsString = request.getParameter("dateOfBirth");
		String newsletterAsString = request.getParameter("newsletter");

		Map<String, String> validationErrors = new HashMap<>();

		UserDao queryUserDao = new UserDao(new GetUserByLoginNameSqlBuilder(), new GetUserByLoginNamePreparedStatementWriter(), new FullUserResultSetReader());
		List<User> retrievedUserLoginNames = queryUserDao.retrieve(User.builder()
			.withLoginName(loginName)
			.build());

		if (!retrievedUserLoginNames.isEmpty()) {
			validationErrors.put("loginNameExists", "Ez a felhasználónév foglalt.");
		}

		if (loginName == null || !loginName.trim().matches("^.{5,30}$") || loginName.trim().contains(" ")) {
			validationErrors.put("loginNameValidationResult", "Rossz felhasználónév.");
		}

		if (password != null && !password.matches("^.{6,}$")) {
			validationErrors.put("invalidPassword", "Rövid jelszó. (Legalább 6 karakter)");
		}

		if (password != null && !password.equals(passwordConfirmation)) {
			validationErrors.put("passwordDoesNotMatchWithConfirmation", "Nem egyeznek meg a megadott jelszavak.");
		}

		if (!phone.matches(".[0-9]{2}\\s+[0-9]{2}\\s+[0-9]{3}\\s+[0-9]{4}")) {
			validationErrors.put("wrongPhone", "Érvénytelen telefonszám.");
		}

		if (!address.matches("\\d{4}+.\\s+[a-zA-Z]+,\\s.{1,}")) {
			validationErrors.put("wrongAddress", "Érvénytelen cím.");
		}

		UserDao queryUserDaoByEmail = new UserDao(new GetUserByEmailSqlBuilder(), new GetUserByEmailPreparedStatementWriter(), new FullUserResultSetReader());
		List<User> retrievedUserEmails = queryUserDaoByEmail.retrieve(User.builder()
			.withEmail(email)
			.build());

		if (!retrievedUserEmails.isEmpty()) {
			validationErrors.put("emailExists", "Az email cím már létezik.");

		}
		if (!email.matches("[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}")) {
			validationErrors.put("wrongEmail", "Érvénytelen email cím.");
		}

		if (!dateOfBirthAsString.matches("\\d{4}+.\\s+\\d{2}+.\\s+\\d{2}+.")) {
			validationErrors.put("invalidDateOfBirth", "Érvénytelen születési dátum.");
		}

		if (validationErrors.isEmpty()) {
			String passwordHash = new PasswordManager().encrypt(password);
			Role role = Role.getById(Long.parseLong(roleIdAsString));
			boolean newsletter = newsletterAsString != null;
			LocalDate dateOfBirth = LocalDate.parse(dateOfBirthAsString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			LocalDateTime now = LocalDateTime.now();
			User user = User.builder()
				.withLoginName(loginName)
				.withPasswordHash(passwordHash)
				.withFirstName(firstName)
				.withLastName(lastName)
				.withRole(role)
				.withPostalAddress(address)
				.withPhone(phone)
				.withEmail(email)
				.withNewsletter(newsletter)
				.withDateOfBirth(dateOfBirth)
				.withRegistrationDate(now)
				.withUserStatus(UserStatus.PENDING)
				.build();
			UserDao userDao = new UserDao(new CreateUserSqlBuilder(), new CreateUserPreparedStatementWriter());
			userDao.create(user);
			response.sendRedirect("login.jsp?registrationSuccessful=true");
		} else {
			request.setAttribute("validationErrors", validationErrors);
			request.getRequestDispatcher("loadRegistration").forward(request, response);
		}
	}

}
