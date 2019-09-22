package by.training.catalog.bean;

import java.util.Objects;

public class User extends Entity {
    private String username;
    private String password;
    private Role role;
    private String email;
    private int phone;

    public User(final long id, final String usernameNew,
                final String passwordNew,
                final Role roleNew, final String emailNew,
                final int phoneNew, final boolean blockedNew) {
        super(id, blockedNew);
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
        if (!(oNew instanceof User)) {
            return false;
        }
        if (!super.equals(oNew)) {
            return false;
        }
        User user = (User) oNew;
        return getPhone() == user.getPhone()
                && Objects.equals(getUsername(), user.getUsername())
                && Objects.equals(getPassword(), user.getPassword())
                && getRole() == user.getRole()
                && Objects.equals(getEmail(), user.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects
                .hash(super.hashCode(), getUsername(), getPassword(), getRole(),
                        getEmail(), getPhone());
    }

    @Override
    public String toString() {
        return "User{" + super.toString()
                + ", username='" + username + '\''
                + ", password='" + password + '\''
                + ", role=" + role
                + ", email='" + email + '\''
                + ", phone=" + phone + '}';
    }
}
