package hu.javamiddle.ghnb.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hu.javamiddle.ghnb.db.dao.BankAccountDao;
import hu.javamiddle.ghnb.db.entity.BankAccount;
import hu.javamiddle.ghnb.db.preparedstatementwriter.bankaccount.UpdateBankAccountAliasNamePreparedStatementWriter;
import hu.javamiddle.ghnb.db.sqlbuilder.bankaccount.UpdateBankAccountAliasNameSqlBuilder;

public class ValidateAndSaveBankAccountDetails extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bankAccountNumber = request.getParameter("bankAccountNumber");
		String aliasName = request.getParameter("aliasName");

		Map<String, String> validationErrors = new HashMap<>();
		if (aliasName == null || !aliasName.matches("^.{5,30}$")) {
			validationErrors.put("aliasNameValidationResult", "Nem megfelelő bankszámla név.");
		}

		if (validationErrors.isEmpty()) {
			BankAccountDao bankAccountDao = new BankAccountDao(new UpdateBankAccountAliasNameSqlBuilder(), new UpdateBankAccountAliasNamePreparedStatementWriter());
			BankAccount bankAccount = BankAccount.builder()
				.withBankAccountNumber(bankAccountNumber)
				.withAliasName(aliasName)
				.build();
			bankAccountDao.update(bankAccount);
			request.getRequestDispatcher("loadBankAccountDetails").forward(request, response);
		} else {
			request.setAttribute("validationErrors", validationErrors);
			request.getRequestDispatcher("loadBankAccountDetails").forward(request, response);
		}
	}

}