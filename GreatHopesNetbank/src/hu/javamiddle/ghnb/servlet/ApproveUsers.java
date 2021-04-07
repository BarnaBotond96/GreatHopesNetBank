package hu.javamiddle.ghnb.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hu.javamiddle.ghnb.db.dao.UserDao;
import hu.javamiddle.ghnb.db.entity.User;
import hu.javamiddle.ghnb.db.preparedstatementwriter.user.ApproveUserPreparedStatementWriter;
import hu.javamiddle.ghnb.db.sqlbuilder.user.ApproveUserSqlBuilder;

public class ApproveUsers extends HttpServlet {

	private static final String SELECTED_USER = "selectedUser_";
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao userDao = new UserDao(new ApproveUserSqlBuilder(), new ApproveUserPreparedStatementWriter());
		request.getParameterMap()
			.keySet().stream()
			.filter(key -> key.startsWith(SELECTED_USER))
			.map(key -> key.replaceFirst(SELECTED_USER, ""))
			.forEach(loginName -> userDao.update(User.builder()
				.withLoginName(loginName)
				.build()));
		boolean isAtLeastOneCheckboxSelected = request.getParameterMap()
			.keySet().stream()
			.anyMatch(key -> key.startsWith(SELECTED_USER));
		response.sendRedirect("loadApproveUsers?" + (isAtLeastOneCheckboxSelected ? "approveSuccessful" : ""));
	}

}