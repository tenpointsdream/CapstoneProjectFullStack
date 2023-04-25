package cogent.com.exception;

@SuppressWarnings("serial")
public class UserDoesNotExistException extends Exception {

	public UserDoesNotExistException(String msg) {
		super(msg);
	}
}
