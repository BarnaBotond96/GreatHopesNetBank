package hu.javamiddle.ghnb.db.preparedstatementwriter.user;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import hu.javamiddle.ghnb.db.entity.User;
import hu.javamiddle.ghnb.db.preparedstatementwriter.PreparedStatementWriter;

public class GetUserByUserStatusPreparedStatementWriter implements PreparedStatementWriter<User> {

	private final long userStatusId;

	public GetUserByUserStatusPreparedStatementWriter(long userStatusId) {
		this.userStatusId = userStatusId;
	}

	@Override
	public void write(PreparedStatement preparedStatement, User entity) throws SQLException {
		preparedStatement.setLong(1, userStatusId);
	}

}