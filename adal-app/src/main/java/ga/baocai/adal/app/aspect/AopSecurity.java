package ga.baocai.adal.app.aspect;

import ga.baocai.adal.app.config.RSASecurityConfig;
import ga.baocai.adal.app.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 使用 aop 切面记录请求日志信息
 * </p>
 */
@Aspect
@Component
@Slf4j
@Order(1)
public class AopSecurity {

	@Autowired
	private RSASecurityConfig rsaSecurityConfig;

	/**
	 * 切入点
	 */
	@Pointcut("execution(public * ga.baocai.adal.app.controller.*Controller.*(..))")
	public void security() {

	}

	/**
	 * 环绕操作
	 *
	 * @param point 切入点
	 * @return 原方法返回值
	 * @throws Throwable 异常信息
	 */
	@Around("security()")
	public Object aroundLog(ProceedingJoinPoint point) throws Throwable {
		Object result = point.proceed();

		result = SecurityUtil.encrypt(result, rsaSecurityConfig);
		return result;
	}

}
