package themayoras.trackmywaterchange.security;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;

public class UsernamePasswordJsonAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final String BODY_ATTR = "baseNode";

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        RequestContextHolder.currentRequestAttributes().setAttribute(
                "baseNode",
                getBaseJsonNode(request),
                RequestAttributes.SCOPE_REQUEST
        );

        return super.attemptAuthentication(request, response);
    }

    /**
     * Obtains the password passed as a field with the JSON request body
     *
     * @param request the request
     * @return the password obtained (null if not found)
     */
    @Override
    protected String obtainPassword(HttpServletRequest request) {
        Object nodeObject = RequestContextHolder
                                    .getRequestAttributes()
                                    .getAttribute(BODY_ATTR, RequestAttributes.SCOPE_REQUEST);

        if (nodeObject != null) {
            if (nodeObject instanceof JsonNode) {
                JsonNode node = (JsonNode) nodeObject;
                return node.path(super.getPasswordParameter()).textValue();
            }
        }
        return null;
    }

    /**
     * Obtains the username passed as a field with the JSON request body
     *
     * @param request the request
     * @return the username obtained (null if not found)
     */
    @Override
    protected String obtainUsername(HttpServletRequest request) {
        Object nodeObject = RequestContextHolder
                                    .getRequestAttributes()
                                    .getAttribute(BODY_ATTR, RequestAttributes.SCOPE_REQUEST);
        if (nodeObject != null) {
            if (nodeObject instanceof JsonNode) {
                JsonNode node = (JsonNode) nodeObject;
                return node.path(super.getUsernameParameter()).textValue();
            }
        }
        return null;
    }

    /**
     * Gets a json node from the request context by this same object
     *
     * @param request the request
     * @return a JSON node
     */
    protected JsonNode getBaseJsonNode(HttpServletRequest request) {
        try {
            BufferedReader reader = request.getReader();

            String body = reader.lines().collect(Collectors.joining());

            return (new ObjectMapper()).readTree(body);
        } catch (IOException ex) {
            return null;
        }

    }
}
