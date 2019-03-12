package danielh1307.springbootsecurityexample.domain;

public class User {

    private final String firstname;
    private final String lastname;
    private final boolean hasRole;

    public User(String firstname, String lastname, boolean hasRole) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.hasRole = hasRole;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public boolean hasRole() {
        return hasRole;
    }
}
