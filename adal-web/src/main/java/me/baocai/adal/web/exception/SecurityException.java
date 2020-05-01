package me.baocai.adal.web.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.baocai.adal.web.common.Status;

/**
 * <p>
 * 全局异常
 * </p>
 */
@EqualsAndHashCode(callSuper = true)
@Data
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
