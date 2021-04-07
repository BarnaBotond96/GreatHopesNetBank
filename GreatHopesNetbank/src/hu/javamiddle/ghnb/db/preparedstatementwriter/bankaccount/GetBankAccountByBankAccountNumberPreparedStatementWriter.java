package hu.javamiddle.ghnb.db.preparedstatementwriter.bankaccount;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import hu.javamiddle.ghnb.db.entity.BankAccount;
import hu.javamiddle.ghnb.db.preparedstatementwriter.PreparedStatementWriter;

public class GetBankAccountByBankAccountNumberPreparedStatementWriter implements PreparedStatementWriter<BankAccount> {

	@Override
	public void write(PreparedStatement preparedStatement, BankAccount bankAccount) throws SQLException {
		preparedStatement.setString(1, bankAccount.getBankAccountNumber());
	}

}