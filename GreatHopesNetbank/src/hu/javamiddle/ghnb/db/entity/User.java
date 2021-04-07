package hu.javamiddle.ghnb.db.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import hu.javamiddle.ghnb.Role;
import hu.javamiddle.ghnb.UserStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

	private String loginName;
	private String passwordHash;
	private String firstName;
	private String lastName;
	private Role role;
	private String postalAddress;
	private String phone;
	private String email;
	private Boolean newsletter;
	private LocalDate dateOfBirth;
	private LocalDateTime registrationDate;
	private LocalDateTime lastLoginDate;
	private UserStatus userStatus;

	private User(Builder builder) {
		this.loginName = builder.loginName;
		this.passwordHash = builder.passwordHash;
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.role = builder.role;
		this.postalAddress = builder.postalAddress;
		this.phone = builder.phone;
		this.email = builder.email;
		this.newsletter = builder.newsletter;
		this.dateOfBirth = builder.dateOfBirth;
		this.registrationDate = builder.registrationDate;
		this.lastLoginDate = builder.lastLoginDate;
		this.userStatus = builder.userStatus;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {

		private String loginName;
		private String passwordHash;
		private String firstName;
		private String lastName;
		private Role role;
		private String postalAddress;
		private String phone;
		private String email;
		private Boolean newsletter;
		private LocalDate dateOfBirth;
		private LocalDateTime registrationDate;
		private LocalDateTime lastLoginDate;
		private UserStatus userStatus;

		private Builder() {
		}

		public Builder withLoginName(String loginName) {
			this.loginName = loginName;
			return this;
		}

		public Builder withPasswordHash(String passwordHash) {
			this.passwordHash = passwordHash;
			return this;
		}

		public Builder withFirstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public Builder withLastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public Builder withRole(Role role) {
			this.role = role;
			return this;
		}

		public Builder withPostalAddress(String postalAddress) {
			this.postalAddress = postalAddress;
			return this;
		}

		public Builder withPhone(String phone) {
			this.phone = phone;
			return this;
		}

		public Builder withEmail(String email) {
			this.email = email;
			return this;
		}

		public Builder withNewsletter(Boolean newsletter) {
			this.newsletter = newsletter;
			return this;
		}

		public Builder withDateOfBirth(LocalDate dateOfBirth) {
			this.dateOfBirth = dateOfBirth;
			return this;
		}

		public Builder withRegistrationDate(LocalDateTime registrationDate) {
			this.registrationDate = registrationDate;
			return this;
		}

		public Builder withLastLoginDate(LocalDateTime lastLoginDate) {
			this.lastLoginDate = lastLoginDate;
			return this;
		}

		public Builder withUserStatus(UserStatus userStatus) {
			this.userStatus = userStatus;
			return this;
		}

		public User build() {
			return new User(this);
		}

	}

}