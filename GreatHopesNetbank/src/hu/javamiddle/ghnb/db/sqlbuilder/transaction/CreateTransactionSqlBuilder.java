package hu.javamiddle.ghnb.db.sqlbuilder.transaction;

import hu.javamiddle.ghnb.db.sqlbuilder.SqlBuilder;

public class CreateTransactionSqlBuilder implements SqlBuilder {

	@Override
	public String build() {
		return "INSERT INTO transaction (transaction_id, from_bank_account_number, to_bank_account_number, beneficiary_name, amount, transaction_comment) VALUES (nextval('transaction_sequence'), ?, ?, ?, ?, ?)";
	}

}