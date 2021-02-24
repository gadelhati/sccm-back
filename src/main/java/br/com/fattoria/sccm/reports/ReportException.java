package br.com.fattoria.sccm.reports;

public class ReportException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ReportException(String message, Exception e) {
		super(message, e);
	}

	public ReportException(Exception e) {
		super(e);
	}
	
}
