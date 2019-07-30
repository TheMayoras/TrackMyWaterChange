package themayoras.trackmywaterchange.service;

import java.util.function.Function;

import javax.servlet.http.Cookie;

import org.springframework.stereotype.Service;

@Service
public class CookieServiceImpl implements CookieService {

	@Override
	public <T> T getValue(Cookie cookie, Function <String, T> conversionFunction) {
		return conversionFunction.apply(cookie.getValue());
	}

}
