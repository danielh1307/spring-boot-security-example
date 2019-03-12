package danielh1307.springbootsecurityexample.ctrl;

import danielh1307.springbootsecurityexample.domain.User;

public interface CurrentUserProvider {

    User getCurrentUser();
}
