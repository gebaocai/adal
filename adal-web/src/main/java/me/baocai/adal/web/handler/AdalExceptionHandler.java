package me.baocai.adal.web.handler;

import lombok.extern.slf4j.Slf4j;
import me.baocai.adal.web.common.CommonResponse;
import me.baocai.adal.web.exception.JsonException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>
 * 统一异常处理
 * </p>
 */
@ControllerAdvice
@Slf4j
public class AdalExceptionHandler {
	private static final String DEFAULT_ERROR_VIEW = "error";

	/**
	 * 统一 json 异常处理
	 *
	 * @param exception JsonException
	 * @return 统一返回 json 格式
	 */
	@ExceptionHandler(value = JsonException.class)
	@ResponseBody
	public CommonResponse jsonErrorHandler(JsonException exception) {
		log.error("【JsonException】:{}", exception.getMessage());
		return CommonResponse.ofException(exception);
	}

//	/**
//	 * 统一 页面 异常处理
//	 *
//	 * @param exception PageException
//	 * @return 统一跳转到异常页面
//	 */
//	@ExceptionHandler(value = JsonException.class)
//	public ModelAndView pageErrorHandler(JsonException exception) {
//		log.error("【DemoPageException】:{}", exception.getMessage());
//		ModelAndView view = new ModelAndView();
//		view.addObject("message", exception.getMessage());
//		view.setViewName(DEFAULT_ERROR_VIEW);
//		return view;
//	}
}
