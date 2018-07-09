package scau.zxck.base.utils;

public class StackTraceUtil {

	public static String getStackTrace(Throwable errors) {
		StringBuilder result = new StringBuilder();
		result.append(errors.toString());
		StackTraceElement[] trace = errors.getStackTrace();
		for (int i = 0; i < trace.length; i++) {
			result.append("\n\tat " + trace[i].toString());
		}
		Throwable ourCause = errors.getCause();
		if (ourCause != null) {
			result.append(getStackTraceAsCause(ourCause, trace));
		}
		return result.toString();
	}

	private static String getStackTraceAsCause(Throwable ourCause, StackTraceElement[] causedTrace) {
		StringBuilder result = new StringBuilder();
		StackTraceElement[] trace   = ourCause.getStackTrace();
		int m = trace.length - 1, n = causedTrace.length - 1;
		while (m >= 0 && n >= 0 && trace[m].equals(causedTrace[n])) {
			m--;
			n--;
		}
		int framesInCommon = trace.length - 1 - m;
		result.append("\nCaused by: " + ourCause.toString());
		for (int i = 0; i <= m; i++)
			result.append("\n\tat " + trace[i].toString());
		if (framesInCommon != 0)
			result.append("\n\t... " + framesInCommon + " more");

		Throwable tempCause = ourCause.getCause();
		if (tempCause != null) {
			result.append(getStackTraceAsCause(tempCause, trace));
		}
		return result.toString();
	}
}
