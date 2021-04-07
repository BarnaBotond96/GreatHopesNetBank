package hu.javamiddle.ghnb.db.entity;

import lombok.Getter;

@Getter
public class UserStatus {

	private Long id;
	private String name;

	private UserStatus(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
	}

	public static final class Builder {

		private Long id;
		private String name;

		private Builder() {
		}

		public Builder withId(Long id) {
			this.id = id;
			return this;
		}

		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		public UserStatus build() {
			return new UserStatus(this);
		}

	}

}