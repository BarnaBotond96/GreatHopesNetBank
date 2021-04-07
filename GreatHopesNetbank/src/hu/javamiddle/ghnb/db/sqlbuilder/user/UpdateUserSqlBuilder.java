package hu.javamiddle.ghnb.db.sqlbuilder.user;

import hu.javamiddle.ghnb.db.sqlbuilder.SqlBuilder;

public class UpdateUserSqlBuilder implements SqlBuilder {

	@Override
	public String build() {
		return "UPDATE users SET password_hash = ?, postal_address = ?, phone = ?, email = ?, newsletter = ? WHERE login_name = ?";
	}

}