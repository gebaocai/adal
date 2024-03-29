package ga.baocai.adal.web.common;

import ga.baocai.adal.web.exception.BaseException;
import lombok.Data;

/**
 * <p>
 * 通用的 API 接口封装
 * </p>
 */
@Data
public class CommonResponse {
    /**
     * 状态码
     */
    private Integer code;

    /**
     * 返回内容
     */
    private String message;

    /**
     * 返回数据
     */
    private Object data;

    /**
     * 无参构造函数
     */
    private CommonResponse() {

    }

    /**
     * 全参构造函数
     *
     * @param code    状态码
     * @param message 返回内容
     * @param data    返回数据
     */
    private CommonResponse(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 构造一个自定义的API返回
     *
     * @param code    状态码
     * @param message 返回内容
     * @param data    返回数据
     * @return CommonResponse
     */
    public static CommonResponse of(Integer code, String message, Object data) {
        return new CommonResponse(code, message, data);
    }

    /**
     * 构造一个成功且不带数据的API返回
     *
     * @return CommonResponse
     */
    public static CommonResponse ofSuccess() {
        return ofSuccess(null);
    }

    /**
     * 构造一个成功且带数据的API返回
     *
     * @param data 返回数据
     * @return CommonResponse
     */
    public static CommonResponse ofSuccess(Object data) {
        return ofStatus(Status.SUCCESS, data);
    }

    /**
     * 构造一个成功且自定义消息的API返回
     *
     * @param message 返回内容
     * @return CommonResponse
     */
    public static CommonResponse ofMessage(String message) {
        return of(Status.SUCCESS.getCode(), message, null);
    }

    /**
     * 构造一个有状态的API返回
     *
     * @param status 状态 {@link Status}
     * @return CommonResponse
     */
    public static CommonResponse ofStatus(Status status) {
        return ofStatus(status, null);
    }

    /**
     * 构造一个有状态且带数据的API返回
     *
     * @param status 状态 {@link IStatus}
     * @param data   返回数据
     * @return CommonResponse
     */
    public static CommonResponse ofStatus(IStatus status, Object data) {
        return of(status.getCode(), status.getMessage(), data);
    }

    /**
     * 构造一个异常的API返回
     *
     * @param t   异常
     * @param <T> {@link BaseException} 的子类
     * @return CommonResponse
     */
    public static <T extends BaseException> CommonResponse ofException(T t) {
        return of(t.getCode(), t.getMessage(), t.getData());
    }
}
