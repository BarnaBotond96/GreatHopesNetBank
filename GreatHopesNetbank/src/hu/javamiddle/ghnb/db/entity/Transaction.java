package hu.javamiddle.ghnb.db.entity;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class Transaction {

	private Long amount;
	private Long transactionId;
	private String beneficiaryName;
	private String transactionComment;
	private String toBankAccountNumber;
	private String fromBankAccountNumber;
	private LocalDateTime transactionDate;

	private Transaction(Builder builder) {
		this.amount = builder.amount;
		this.transactionId = builder.transactionId;
		this.beneficiaryName = builder.beneficiaryName;
		this.transactionComment = builder.transactionComment;
		this.toBankAccountNumber = builder.toBankAccountNumber;
		this.fromBankAccountNumber = builder.fromBankAccountNumber;
		this.transactionDate = builder.transactionDate;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {

		private Long amount;
		private Long transactionId;
		private String beneficiaryName;
		private String transactionComment;
		private String toBankAccountNumber;
		private String fromBankAccountNumber;
		private LocalDateTime transactionDate;

		private Builder() {
		}

		public Builder withAmount(Long amount) {
			this.amount = amount;
			return this;
		}

		public Builder withTransactionId(Long transactionId) {
			this.transactionId = transactionId;
			return this;
		}

		public Builder withBeneficiaryName(String beneficiaryName) {
			this.beneficiaryName = beneficiaryName;
			return this;
		}

		public Builder withTransactionComment(String transactionComment) {
			this.transactionComment = transactionComment;
			return this;
		}

		public Builder withToBankAccountNumber(String toBankAccountNumber) {
			this.toBankAccountNumber = toBankAccountNumber;
			return this;
		}

		public Builder withFromBankAccountNumber(String fromBankAccountNumber) {
			this.fromBankAccountNumber = fromBankAccountNumber;
			return this;
		}

		public Builder withTransactionDate(LocalDateTime transactionDate) {
			this.transactionDate = transactionDate;
			return this;
		}

		public Transaction build() {
			return new Transaction(this);
		}

	}

}