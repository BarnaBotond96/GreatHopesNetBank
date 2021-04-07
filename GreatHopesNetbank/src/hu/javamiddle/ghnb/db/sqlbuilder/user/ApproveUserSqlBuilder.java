package hu.javamiddle.ghnb.db.sqlbuilder.user;

import hu.javamiddle.ghnb.UserStatus;
import hu.javamiddle.ghnb.db.sqlbuilder.SqlBuilder;

public class ApproveUserSqlBuilder implements SqlBuilder {

	@Override
	public String build() {
		return "UPDATE users SET user_status_id = " + UserStatus.ACTIVE.getUserStatusId() + " WHERE login_name = ?";
	}

}