package org.jordyn.vsav.exception;

/**
 * Wrapper exception class
 * @author jordyn
 *
 */
public class VsavRepoException extends RuntimeException {

	private static final long serialVersionUID = 6486971982034151258L;

	private String exceptionType;
	private String className;
	private String methodName;

	public VsavRepoException(final Exception exception) {
		super(exception);
	}

	public String getExceptionType() {
		return exceptionType;
	}

	public void setExceptionType(final String exceptionType) {
		this.exceptionType = exceptionType;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(final String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(final String methodName) {
		this.methodName = methodName;
	}



}
