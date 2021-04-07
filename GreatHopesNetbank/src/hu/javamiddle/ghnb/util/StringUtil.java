package hu.javamiddle.ghnb.util;

public final class StringUtil {

	private StringUtil() {
	}

	public static boolean isBlank(String text) {
		return text == null || text.isBlank();
	}

}