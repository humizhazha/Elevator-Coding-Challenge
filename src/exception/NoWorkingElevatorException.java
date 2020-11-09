package exception;

public class NoWorkingElevatorException extends RuntimeException {
	private String message;

	public NoWorkingElevatorException(String string) {
		this.message = string;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
