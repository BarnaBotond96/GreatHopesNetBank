package hu.javamiddle.ghnb.dto;

import lombok.Getter;

@Getter
public class BankAccountDetailsDto {

	private String bankAccountNumber;
	private String aliasName;
	private Long currentBalance;
	private String currencyType;
	private String bankAccountStatus;
	private String bankAccountType;
	private String lastTransactionTimestamp;

	private BankAccountDetailsDto(Builder builder) {
		this.bankAccountNumber = builder.bankAccountNumber;
		this.aliasName = builder.aliasName;
		this.currentBalance = builder.currentBalance;
		this.currencyType = builder.currencyType;
		this.bankAccountStatus = builder.bankAccountStatus;
		this.bankAccountType = builder.bankAccountType;
		this.lastTransactionTimestamp = builder.lastTransactionTimestamp;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {

		private String bankAccountNumber;
		private String aliasName;
		private Long currentBalance;
		private String currencyType;
		private String bankAccountStatus;
		private String bankAccountType;
		private String lastTransactionTimestamp;

		private Builder() {
		}

		public Builder withBankAccountNumber(String bankAccountNumber) {
			this.bankAccountNumber = bankAccountNumber;
			return this;
		}

		public Builder withAliasName(String aliasName) {
			this.aliasName = aliasName;
			return this;
		}

		public Builder withCurrentBalance(Long currentBalance) {
			this.currentBalance = currentBalance;
			return this;
		}

		public Builder withCurrencyType(String currencyType) {
			this.currencyType = currencyType;
			return this;
		}

		public Builder withBankAccountStatus(String bankAccountStatus) {
			this.bankAccountStatus = bankAccountStatus;
			return this;
		}

		public Builder withBankAccountType(String bankAccountType) {
			this.bankAccountType = bankAccountType;
			return this;
		}

		public Builder withLastTransactionTimestamp(String lastTransactionTimestamp) {
			this.lastTransactionTimestamp = lastTransactionTimestamp;
			return this;
		}

		public BankAccountDetailsDto build() {
			return new BankAccountDetailsDto(this);
		}

	}

}