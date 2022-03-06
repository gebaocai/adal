package ga.baocai.adal.app.exception;

import ga.baocai.adal.app.common.Status;
import lombok.Getter;

/**
 */
@Getter
public class JsonException extends BaseException {

	public JsonException(Status status) {
		super(status);
	}

	public JsonException(Integer code, String message) {
		super(code, message);
	}
}
