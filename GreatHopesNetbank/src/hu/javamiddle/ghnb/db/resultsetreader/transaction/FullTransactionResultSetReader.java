package hu.javamiddle.ghnb.db.resultsetreader.transaction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import hu.javamiddle.ghnb.db.entity.Transaction;
import hu.javamiddle.ghnb.db.resultsetreader.ResultSetReader;

public class FullTransactionResultSetReader implements ResultSetReader<Transaction> {

	@Override
	public List<Transaction> read(ResultSet resultSet) throws SQLException {
		List<Transaction> results = new ArrayList<>();
		while (resultSet.next()) {
			long transactionId = resultSet.getLong("transaction_id");
			String fromBankAccountNumber = resultSet.getString("from_bank_account_number");
			String toBankAccountNumber = resultSet.getString("to_bank_account_number");
			String beneficiaryName = resultSet.getString("beneficiary_name");
			LocalDateTime transactionDate = resultSet.getTimestamp("transaction_date").toLocalDateTime();
			long amount = resultSet.getLong("amount");
			String transactionComment = resultSet.getString("transaction_comment");
			Transaction transaction = Transaction.builder()
					.withTransactionId(transactionId)
					.withFromBankAccountNumber(fromBankAccountNumber)
					.withToBankAccountNumber(toBankAccountNumber)
					.withBeneficiaryName(beneficiaryName)
					.withTransactionDate(transactionDate)
					.withAmount(amount)
					.withTransactionComment(transactionComment)
					.build();
			results.add(transaction);
		}
		return results;
	}

}