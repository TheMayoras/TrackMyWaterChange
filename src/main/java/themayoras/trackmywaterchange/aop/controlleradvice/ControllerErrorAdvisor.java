package themayoras.trackmywaterchange.aop.controlleradvice;

import org.springframework.web.bind.MissingRequestCookieException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerErrorAdvisor {

	@ExceptionHandler(MissingRequestCookieException.class)
	public String handleMissingCookieAdvice(Exception ex) {
		return "redirect:/user/login-expired";
	}
}
