package hu.javamiddle.ghnb.db.sqlbuilder.bankaccount;

import hu.javamiddle.ghnb.db.sqlbuilder.SqlBuilder;

public class CreateBankAccountSqlBuilder implements SqlBuilder {

	@Override
	public String build() {
		return "INSERT INTO bank_account (bank_account_number, alias_name, login_name, current_balance, currency_type, bank_account_status_id, bank_account_type_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
	}

}