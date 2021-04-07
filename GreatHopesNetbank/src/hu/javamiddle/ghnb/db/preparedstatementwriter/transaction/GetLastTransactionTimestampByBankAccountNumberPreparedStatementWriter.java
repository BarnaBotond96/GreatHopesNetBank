package hu.javamiddle.ghnb.db.preparedstatementwriter.transaction;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import hu.javamiddle.ghnb.db.entity.Transaction;
import hu.javamiddle.ghnb.db.preparedstatementwriter.PreparedStatementWriter;

public class GetLastTransactionTimestampByBankAccountNumberPreparedStatementWriter implements PreparedStatementWriter<Transaction> {

	private final String bankAccountNumber;

	public GetLastTransactionTimestampByBankAccountNumberPreparedStatementWriter(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	@Override
	public void write(PreparedStatement preparedStatement, Transaction entity) throws SQLException {
		preparedStatement.setString(1, bankAccountNumber);
		preparedStatement.setString(2, bankAccountNumber);
	}

}