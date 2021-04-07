package hu.javamiddle.ghnb.db.resultsetreader.transaction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import hu.javamiddle.ghnb.db.entity.Transaction;
import hu.javamiddle.ghnb.db.resultsetreader.ResultSetReader;

public class TransactionTimestampResultSetReader implements ResultSetReader<Transaction> {

	@Override
	public List<Transaction> read(ResultSet resultSet) throws SQLException {
		List<Transaction> transactions = new ArrayList<>();
		resultSet.next();
		Timestamp transactionDateAsTimestamp = resultSet.getTimestamp("transaction_date");
		LocalDateTime transactionDate = null;
		if (transactionDateAsTimestamp != null) {
			transactionDate = transactionDateAsTimestamp.toLocalDateTime();
			Transaction transaction = Transaction.builder()
					.withTransactionDate(transactionDate)
					.build();
			transactions.add(transaction);
		}
		return transactions;
	}

}