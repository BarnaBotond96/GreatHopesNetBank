package hu.javamiddle.ghnb.db.preparedstatementwriter.user;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import hu.javamiddle.ghnb.db.entity.User;
import hu.javamiddle.ghnb.db.preparedstatementwriter.PreparedStatementWriter;

public class CreateUserPreparedStatementWriter implements PreparedStatementWriter<User> {

	@Override
	public void write(PreparedStatement preparedStatement, User user) throws SQLException {
		int i = 1;
		preparedStatement.setString(i++, user.getLoginName());
		preparedStatement.setString(i++, user.getPasswordHash());
		preparedStatement.setString(i++, user.getFirstName());
		preparedStatement.setString(i++, user.getLastName());
		preparedStatement.setLong(i++, user.getRole().getRoleId());
		preparedStatement.setString(i++, user.getPostalAddress());
		preparedStatement.setString(i++, user.getPhone());
		preparedStatement.setString(i++, user.getEmail());
		preparedStatement.setString(i++, Boolean.TRUE.equals(user.getNewsletter()) ? "Y" : "N");
		preparedStatement.setDate(i++, Date.valueOf(user.getDateOfBirth()));
		preparedStatement.setLong(i++, user.getUserStatus().getUserStatusId());
	}

}