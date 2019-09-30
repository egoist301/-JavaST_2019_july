package by.training.catalog.bean;

import java.util.List;

import java.util.Objects;

public class User extends Entity {
    private String username;
    private String password;
    private Role role;
    private String email;
    private int phone;
    private boolean blocked;
    private List<RubiksCube> cubes;

    public User(final long idNew) {
        super(idNew);
    }

    public User(final long idNew, final String usernameNew,
                final String passwordNew) {
        super(idNew);
        username = usernameNew;
        password = passwordNew;
    }

    public User(final long idNew, final String usernameNew,
                final Role roleNew) {
        super(idNew);
        username = usernameNew;
        role = roleNew;
    }

    public User(final long id, final String usernameNew,
                final String passwordNew,
                final String emailNew, final Role roleNew,
                final int phoneNew, final boolean blockedNew) {
        super(id);
        username = usernameNew;
        password = passwordNew;
        role = roleNew;
        email = emailNew;
        phone = phoneNew;
        blocked = blockedNew;
    }

    public List<RubiksCube> getCubes() {
        return cubes;
    }

    public void setCubes(final List<RubiksCube> cubesNew) {
        cubes = cubesNew;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(final boolean blockedNew) {
        blocked = blockedNew;
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
                && Objects.equals(getEmail(), user.getEmail())
                && isBlocked() == user.isBlocked()
                && getCubes().equals(user.getCubes());
    }

    @Override
    public int hashCode() {
        return Objects
                .hash(super.hashCode(), getUsername(), getPassword(), getRole(),
                        getEmail(), getPhone(), isBlocked(), getCubes());
    }

    @Override
    public String toString() {
        return "User{" + super.toString()
                + ", username='" + username + '\''
                + ", password='" + password + '\''
                + ", role=" + role
                + ", email='" + email + '\''
                + ", phone=" + phone
                + ", blocked=" + blocked
                + ", cubes=" + cubes + '}';
    }
}
