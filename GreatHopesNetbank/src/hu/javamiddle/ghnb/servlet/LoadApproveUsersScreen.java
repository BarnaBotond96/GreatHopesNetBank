package hu.javamiddle.ghnb.servlet;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hu.javamiddle.ghnb.UserStatus;
import hu.javamiddle.ghnb.db.dao.UserDao;
import hu.javamiddle.ghnb.db.entity.User;
import hu.javamiddle.ghnb.db.preparedstatementwriter.user.GetUserByUserStatusPreparedStatementWriter;
import hu.javamiddle.ghnb.db.resultsetreader.user.FullUserResultSetReader;
import hu.javamiddle.ghnb.db.sqlbuilder.user.GetUserByUserStatusSqlBuilder;
import hu.javamiddle.ghnb.dto.UserToBeApprovedDto;


public class LoadApproveUsersScreen extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao userDao = new UserDao(new GetUserByUserStatusSqlBuilder(), new GetUserByUserStatusPreparedStatementWriter(UserStatus.PENDING.getUserStatusId()), new FullUserResultSetReader());
		List<User> usersToBeApproved = userDao.retrieve(null);
		List<UserToBeApprovedDto> usersToBeApprovedDtos = usersToBeApproved.stream()
				.map(user -> UserToBeApprovedDto.builder()
						.withLoginName(user.getLoginName())
						.withRegistrationDate(user.getRegistrationDate().format(DateTimeFormatter.ofPattern("yyyy. MM. dd.")))
						.build())
				.collect(Collectors.toList());
		request.setAttribute("usersToBeApproved", usersToBeApprovedDtos);
		if (request.getParameter("approveSuccessful") != null) {
			request.setAttribute("approveSuccessful", true);
		}
		request.getRequestDispatcher("approveUsers.jsp").forward(request, response);
	}

}