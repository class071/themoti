package com.daily.themoti.jwt.config.guard;

import com.daily.themoti.jwt.config.CustomUserDetails;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@NoArgsConstructor
public class AuthenticationHelper {

    public static Long extractUserId() {
        return Long.valueOf(getUserDetails().getUserId());
    }

    private static CustomUserDetails getUserDetails() {
        return (CustomUserDetails) getAuthentication().getPrincipal();
    }

    private static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
