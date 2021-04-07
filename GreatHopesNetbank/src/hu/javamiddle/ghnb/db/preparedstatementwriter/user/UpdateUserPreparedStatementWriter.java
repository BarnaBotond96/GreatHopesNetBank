package hu.javamiddle.ghnb.db.preparedstatementwriter.user;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import hu.javamiddle.ghnb.db.entity.User;
import hu.javamiddle.ghnb.db.preparedstatementwriter.PreparedStatementWriter;

public class UpdateUserPreparedStatementWriter implements PreparedStatementWriter<User> {

	@Override
	public void write(PreparedStatement preparedStatement, User entity) throws SQLException {
		preparedStatement.setString(1, entity.getPasswordHash());
		preparedStatement.setString(2, entity.getPostalAddress());
		preparedStatement.setString(3, entity.getPhone());
		preparedStatement.setString(4, entity.getEmail());
		preparedStatement.setString(5, Boolean.TRUE.equals(entity.getNewsletter()) ? "Y" : "N");
		preparedStatement.setString(6, entity.getLoginName());
	}

}