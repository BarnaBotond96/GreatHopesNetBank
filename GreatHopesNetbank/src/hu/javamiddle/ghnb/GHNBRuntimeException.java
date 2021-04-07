package hu.javamiddle.ghnb;

public class GHNBRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public GHNBRuntimeException() {
	}

	public GHNBRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public GHNBRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public GHNBRuntimeException(String message) {
		super(message);
	}

	public GHNBRuntimeException(Throwable cause) {
		super(cause);
	}

}