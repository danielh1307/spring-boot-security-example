package danielh1307.springbootsecurityexample.infrastructure.security;

import danielh1307.springbootsecurityexample.domain.User;
import org.springframework.security.authentication.AbstractAuthenticationToken;

import static java.util.Collections.emptyList;

public class PreAuthenticatedUserAuthenticationToken extends AbstractAuthenticationToken {

    private final User user;

    PreAuthenticatedUserAuthenticationToken(User user) {
        super(emptyList());
        setAuthenticated(true);

        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return getUser();
    }
}
