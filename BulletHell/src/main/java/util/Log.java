package util;

import reference.*;

@SuppressWarnings(value = { "all" })
public class Log {

	public static void log(String logLevel, String text) {
		if (Config.LOGGING == true) System.out.println(Config.NAME + " " + logLevel + " " + text);
	}

	public static void warn(String text) {
		log("[WARN]", text);
	}

	public static void debug(String text) {
		if (Config.DEBUG_MODE == true) log("[DEBUG]", text);
	}

	public static void error(String text) {
		log("[ERROR]", text);
	}

	public static void info(String text) {
		log("[INFO]", text);
	}

	public static void fatal(String text) {
		log("[FATAL]", text);
	}
}
