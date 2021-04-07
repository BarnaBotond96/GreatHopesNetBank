package hu.javamiddle.ghnb.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hu.javamiddle.ghnb.db.dao.RoleDao;
import hu.javamiddle.ghnb.db.entity.Role;
import hu.javamiddle.ghnb.db.preparedstatementwriter.EmptyPreparedStatementWriter;
import hu.javamiddle.ghnb.db.resultsetreader.role.FullRolesResultSetReader;
import hu.javamiddle.ghnb.db.sqlbuilder.role.GetAllRolesSqlBuilder;

public class LoadRegistrationScreen extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RoleDao roleDao = new RoleDao(new GetAllRolesSqlBuilder(), new EmptyPreparedStatementWriter<Role>(), new FullRolesResultSetReader());
		List<Role> roles = roleDao.retrieve(null);
		request.setAttribute("userRoles", roles);
		request.getRequestDispatcher("registration.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}