package hu.javamiddle.ghnb.servlet;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hu.javamiddle.ghnb.db.dao.TransactionDao;
import hu.javamiddle.ghnb.db.entity.Transaction;
import hu.javamiddle.ghnb.db.preparedstatementwriter.transaction.GetTransactionPreparedStatementWriter;
import hu.javamiddle.ghnb.db.resultsetreader.transaction.FullTransactionResultSetReader;
import hu.javamiddle.ghnb.db.sqlbuilder.transaction.GetTransactionSqlBuilder;
import hu.javamiddle.ghnb.dto.TransactionHistoryDto;

public class LoadTransactionHistoryScreen extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bankAccountNumber = request.getParameter("bankAccountNumber");
		TransactionDao transactionDao = new TransactionDao(new GetTransactionSqlBuilder(), new GetTransactionPreparedStatementWriter(bankAccountNumber), new FullTransactionResultSetReader());
		List<Transaction> transactions = transactionDao.retrieve(null);
		List<TransactionHistoryDto> transactionHistoryEntries = transactions.stream()
			.map(transaction -> TransactionHistoryDto.builder()
				.withTransactionId(transaction.getTransactionId())
				.withAmount(transaction.getAmount())
				.withCurrencyType("HUF")
				.withTransactionDate(transaction.getTransactionDate().format(DateTimeFormatter.ofPattern("yyyy. MM. dd. hh:mm:ss")))
				.build())
			.collect(Collectors.toUnmodifiableList());
		request.setAttribute("transactionHistoryEntries", transactionHistoryEntries);
		request.getRequestDispatcher("transactionHistory.jsp").forward(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}