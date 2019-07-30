package themayoras.trackmywaterchange.service;

import java.util.function.Function;

import javax.servlet.http.Cookie;

public interface CookieService {

	<T>T getValue(Cookie cookie, Function<String, T> function);
}
