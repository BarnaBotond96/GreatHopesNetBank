package hu.javamiddle.ghnb.db.sqlbuilder.transaction;

import hu.javamiddle.ghnb.db.sqlbuilder.SqlBuilder;

public class GetTransactionSqlBuilder implements SqlBuilder {

	@Override
	public String build() {
		return "SELECT * FROM transaction WHERE from_bank_account_number = ? OR to_bank_account_number = ?";
	}

}