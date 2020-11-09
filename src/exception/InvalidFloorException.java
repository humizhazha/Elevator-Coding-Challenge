package exception;

public class InvalidFloorException extends RuntimeException{
	private String message;

	public InvalidFloorException(String string) {
		this.message = string;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
