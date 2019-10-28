package com.greco.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.web.context.request.WebRequest;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import com.greco.exception.ServerException;

@Configuration
public class ExceptionHandlerConfiguration {

    @Bean
    public ErrorAttributes errorAttributes() {
        return new DefaultErrorAttributes() {

            @Override
            public Map<String, Object> getErrorAttributes(
                    WebRequest webRequest,
                    boolean includeStackTrace) {
                Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);

                Throwable error = getError(webRequest);
                if (error instanceof ServerException) {
                    errorAttributes.put("code", ((ServerException) error).getCode());
                    errorAttributes.put("status", ((ServerException) error).getHttpStatus().value());
                    errorAttributes.put("annexMessage", ((ServerException) error).getAnnexMessage());
                } else if (error instanceof RuntimeException && error.getCause() != null && error.getCause() instanceof ServerException) {
                    errorAttributes.put("code", ((ServerException) error.getCause()).getCode());
                    if (errorAttributes.get("message") != null) {
                        errorAttributes.put("message", error.getCause().getMessage());
                    }
                }
                return errorAttributes;
            }
        };
    }
}
