package themayoras.trackmywaterchange.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * An AuthenticationSuccessHandler that responds to successful authentication with a configured HTTP status
 */
public class HttpStatusSuccessHandler implements AuthenticationSuccessHandler {
    private HttpStatus responseStatus;

    public HttpStatusSuccessHandler(HttpStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        System.err.println("Handling the success");
        if (response.isCommitted()) {
            return;
        }

        response.setStatus(responseStatus.value());
    }
}
