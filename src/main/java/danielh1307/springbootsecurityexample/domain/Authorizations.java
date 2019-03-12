package danielh1307.springbootsecurityexample.domain;

import danielh1307.springbootsecurityexample.ctrl.CurrentUserProvider;
import org.springframework.stereotype.Component;

@Component("auth")
public class Authorizations {

    private final CurrentUserProvider currentUserProvider;

    public Authorizations(CurrentUserProvider currentUserProvider) {
        this.currentUserProvider = currentUserProvider;
    }

    public boolean userHasRole() {
        return this.currentUserProvider.getCurrentUser().hasRole();
    }
}
