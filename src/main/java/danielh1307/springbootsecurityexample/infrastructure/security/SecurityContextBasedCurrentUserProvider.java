package danielh1307.springbootsecurityexample.infrastructure.security;

import danielh1307.springbootsecurityexample.ctrl.CurrentUserProvider;
import danielh1307.springbootsecurityexample.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityContextBasedCurrentUserProvider implements CurrentUserProvider {

    @Override
    public User getCurrentUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();

        if (!(authentication instanceof PreAuthenticatedUserAuthenticationToken)) {
            throw new IllegalStateException("no user available in security context");
        }

        PreAuthenticatedUserAuthenticationToken preAuthenticatedUserAuthenticationToken = (PreAuthenticatedUserAuthenticationToken) authentication;

        User user = preAuthenticatedUserAuthenticationToken.getUser();

        if (user == null) {
            throw new IllegalStateException("no user available in security context");
        }

        return user;
    }
}
