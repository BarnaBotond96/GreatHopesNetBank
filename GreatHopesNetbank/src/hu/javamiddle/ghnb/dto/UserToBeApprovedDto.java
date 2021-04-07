package hu.javamiddle.ghnb.dto;

import lombok.Getter;

@Getter
public class UserToBeApprovedDto {

	private final String loginName;
	private final String registrationDate;

	private UserToBeApprovedDto(Builder builder) {
		this.loginName = builder.loginName;
		this.registrationDate = builder.registrationDate;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {

		private String loginName;
		private String registrationDate;

		private Builder() {
		}

		public Builder withLoginName(String loginName) {
			this.loginName = loginName;
			return this;
		}

		public Builder withRegistrationDate(String registrationDate) {
			this.registrationDate = registrationDate;
			return this;
		}

		public UserToBeApprovedDto build() {
			return new UserToBeApprovedDto(this);
		}

	}

}