package hu.javamiddle.ghnb.db.sqlbuilder.user;

import hu.javamiddle.ghnb.db.sqlbuilder.SqlBuilder;

public class CreateUserSqlBuilder implements SqlBuilder {

	@Override
	public String build() {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO users ( ");
		sql.append("	login_name, ");
		sql.append("	password_hash, ");
		sql.append("	first_name, ");
		sql.append("	last_name, ");
		sql.append("	role_id, ");
		sql.append("	postal_address, ");
		sql.append("	phone, ");
		sql.append("	email, ");
		sql.append("	newsletter, ");
		sql.append("	date_of_birth, ");
		sql.append("	user_status_id ");
		sql.append("	) ");
		sql.append("VALUES ( ");
		sql.append("	?, ");
		sql.append("	?, ");
		sql.append("	?, ");
		sql.append("	?, ");
		sql.append("	?, ");
		sql.append("	?, ");
		sql.append("	?, ");
		sql.append("	?, ");
		sql.append("	?, ");
		sql.append("	?, ");
		sql.append("	? ");
		sql.append("	) ");
		return sql.toString();
	}

}