package ga.baocai.adal.web.exception;

import lombok.Getter;
import ga.baocai.adal.web.common.Status;

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
