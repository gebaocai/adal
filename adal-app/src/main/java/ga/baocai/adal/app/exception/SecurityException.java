package ga.baocai.adal.app.exception;

import ga.baocai.adal.app.common.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 全局异常
 * </p>
 */
@EqualsAndHashCode(callSuper = true)
public class SecurityException extends BaseException {
    public SecurityException(Status status) {
        super(status);
    }

    public SecurityException(Status status, Object data) {
        super(status, data);
    }

    public SecurityException(Integer code, String message) {
        super(code, message);
    }

    public SecurityException(Integer code, String message, Object data) {
        super(code, message, data);
    }
}
