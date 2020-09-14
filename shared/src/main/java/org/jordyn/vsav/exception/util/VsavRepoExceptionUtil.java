package org.jordyn.vsav.exception.util;

import org.jordyn.vsav.exception.VsavRepoException;
import org.springframework.stereotype.Component;

/**
 * This is a utility class to convert exceptions into a standard type
 * @author jordyn
 *
 */
@Component
public final class VsavRepoExceptionUtil {

	public static VsavRepoException convertException(final Exception exception) {
		VsavRepoException vsavRepoException = new VsavRepoException(exception) {
			private static final long serialVersionUID = 6026796525632901840L;
		};
		StackTraceElement[] stackTrace = exception.getStackTrace();
		String className = "";
		String methodName = "";
		String exceptionType = exception.getClass().getName();

		if(stackTrace.length > 0) {
			className = stackTrace[0].getClassName();
			methodName = stackTrace[0].getMethodName();
		}

		vsavRepoException.setClassName(className);
		vsavRepoException.setMethodName(methodName);
		vsavRepoException.setExceptionType(exceptionType);

		return vsavRepoException;
	}
}