package by.training.catalog.bean;

import java.util.Objects;

public class Account extends Entity {
    private String username;
    private String password;
    private Role role;
    private String email;
    private int phone;

    public Account(final long id, final String usernameNew,
                   final String passwordNew,
                   final Role roleNew, final String emailNew,
                   final int phoneNew) {
        super(id);
        username = usernameNew;
        password = passwordNew;
        role = roleNew;
        email = emailNew;
        phone = phoneNew;
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

    @Override
    public boolean equals(final Object oNew) {
        if (this == oNew) {
            return true;
        }
        if (!(oNew instanceof Account)) {
            return false;
        }
        if (!super.equals(oNew)) {
            return false;
        }
        Account account = (Account) oNew;
        return getPhone() == account.getPhone() &&
                Objects.equals(getUsername(), account.getUsername()) &&
                Objects.equals(getPassword(), account.getPassword()) &&
                getRole() == account.getRole() &&
                Objects.equals(getEmail(), account.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects
                .hash(super.hashCode(), getUsername(), getPassword(), getRole(),
                        getEmail(), getPhone());
    }

    @Override
    public String toString() {
        return "Account{" + super.toString() +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                '}';
    }
}
