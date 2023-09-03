package org.tanvir.model;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author tanvirtareq
 * @since 7/16/23
 */

@Component
public class LoginForm {
    @NotEmpty(message = "{email.not.blank}")
    @Size(max = 50, message = "{string.max.size}")
    private String email;

    @NotEmpty(message = "password.required")
    @Size(max = 300, message = "{string.max.size}")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}