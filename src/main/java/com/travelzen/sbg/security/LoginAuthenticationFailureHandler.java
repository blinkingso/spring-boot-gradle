package com.travelzen.sbg.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.ExceptionMappingAuthenticationFailureHandler;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author andrew
 * @createtime 2017-09-08
 * @ide Intellij Idea
 **/
@Component
public class LoginAuthenticationFailureHandler extends ExceptionMappingAuthenticationFailureHandler {

    private final Map<String, String> failureUrlMap = new HashMap<>();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String url = failureUrlMap.get(exception.getClass().getName());

        if (url != null) {
            getRedirectStrategy().sendRedirect(request, response, url);
        } else {
            super.onAuthenticationFailure(request, response, exception);
        }
    }

    @Override
    public void setExceptionMappings(Map<?, ?> failureUrlMap) {
        this.failureUrlMap.clear();
        for (Map.Entry<?, ?> entry : failureUrlMap.entrySet()) {
            Object exception = entry.getKey();
            Object url = entry.getValue();
            Assert.isInstanceOf(String.class, exception,
                    "Exception key must be a String (the exception classname).");
            Assert.isInstanceOf(String.class, url, "URL must be a String");
            Assert.isTrue(UrlUtils.isValidRedirectUrl((String) url),
                    "Not a valid redirect URL: " + url);
            this.failureUrlMap.put((String) exception, (String) url);
        }
    }
}
