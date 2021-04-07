package hu.javamiddle.ghnb.dto;

import lombok.Getter;

@Getter
public class ClientDto {

	private final String loginName;
	private final String displayName;

	private ClientDto(Builder builder) {
		this.loginName = builder.loginName;
		this.displayName = builder.displayName;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {

		private String loginName;
		private String displayName;

		private Builder() {
		}

		public Builder withLoginName(String loginName) {
			this.loginName = loginName;
			return this;
		}

		public Builder withDisplayName(String displayName) {
			this.displayName = displayName;
			return this;
		}

		public ClientDto build() {
			return new ClientDto(this);
		}

	}

}