package com.xantrix.webapp.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "app.security.users")
public class SecurityProperties {
    private User read;
    private User write;

    @Setter
    @Getter
    public static class User {
        private String username;
        private String password;
    }

}
