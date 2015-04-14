
	package util;

	import reference.*;

	public class Log
	{
		public static void log(String logLevel, String text)
		{
			if(Reference.LOGGING==true)
			System.out.println(Reference.NAME+ " " + logLevel + " " + text);
		}

		public static void warn(String text)
		{
			log("[WARN]", text);
		}

		public static void debug(String text)
		{
			if(Reference.DEBUG_MODE == true)
			log("[DEBUG]", text);
		}

		public static void error(String text)
		{
			log("[ERROR]", text);
		}

		public static void info(String text)
		{
			log("[INFO]", text);
		}

		public static void fatal(String text)
		{
			log("[FATAL]", text);
		}
	}
