package main.java;

@SuppressWarnings("serial")
public class ParserFailureException extends RuntimeException {
	public ParserFailureException(String msg) {
		super(msg);
	}
}
