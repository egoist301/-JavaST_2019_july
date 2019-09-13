package by.training.catalog.bean;

public class Account extends Entity {
    private String username;
    private String password;
    private Role role;
    private String email;
    private int phone;

    public Account() {

    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(final int phoneNew) {
        phone = phoneNew;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String usernameNew) {
        username = usernameNew;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String passwordNew) {
        password = passwordNew;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(final Role roleNew) {
        role = roleNew;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String emailNew) {
        email = emailNew;
    }

}
