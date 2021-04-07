package hu.javamiddle.ghnb.db.sqlbuilder.transaction;

import hu.javamiddle.ghnb.db.sqlbuilder.SqlBuilder;

public class GetLastTransactionTimestampByBankAccountNumberSqlBuilder implements SqlBuilder {

	@Override
	public String build() {
		return "SELECT transaction_date FROM transaction WHERE from_bank_account_number = ? OR to_bank_account_number = ? ORDER BY transaction_date DESC LIMIT 1";
	}

}