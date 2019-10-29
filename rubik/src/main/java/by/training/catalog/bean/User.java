package by.training.catalog.bean;

import java.util.List;
import java.util.Objects;

/**
 * User.
 */
public class User extends Entity {
    /**
     * Username or login.
     */
    private String username;
    /**
     * Password.
     */
    private String password;
    /**
     * Role.
     */
    private Role role;
    /**
     * Email.
     */
    private String email;
    /**
     * Phone(7 numbers).
     */
    private int phone;
    /**
     * Blocked or not.
     */
    private boolean blocked;
    /**
     * Bookmarks.
     */
    private List<RubiksCube> cubes;

    /**
     * Super constructor.
     *
     * @param idNew id of user.
     */
    public User(final long idNew) {
        super(idNew);
    }

    /**
     * Constructor with parameters.
     *
     * @param idNew       id of cube.
     * @param usernameNew username of login.
     * @param passwordNew password.
     */
    public User(final long idNew, final String usernameNew,
                final String passwordNew) {
        super(idNew);
        username = usernameNew;
        password = passwordNew;
    }

    /**
     * Constructor with parameter.
     *
     * @param idNew       id of user.
     * @param usernameNew username or login.
     * @param roleNew     role.
     */
    public User(final long idNew, final String usernameNew,
                final Role roleNew) {
        super(idNew);
        username = usernameNew;
        role = roleNew;
    }

    /**
     * Constructor with all parameters.
     *
     * @param id          id of user.
     * @param usernameNew username or login.
     * @param passwordNew password.
     * @param emailNew    email.
     * @param roleNew     role.
     * @param phoneNew    phone.
     * @param blockedNew  blocked.
     */
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

    /**
     * Getter.
     *
     * @return bookmarks.
     */
    public List<RubiksCube> getCubes() {
        return cubes;
    }

    /**
     * Setter.
     *
     * @param cubesNew bookmarks.
     */
    public void setCubes(final List<RubiksCube> cubesNew) {
        cubes = cubesNew;
    }

    /**
     * Getter.
     *
     * @return blocked.
     */
    public boolean isBlocked() {
        return blocked;
    }

    /**
     * Setter.
     *
     * @param blockedNew blocked.
     */
    public void setBlocked(final boolean blockedNew) {
        blocked = blockedNew;
    }

    /**
     * Getter.
     *
     * @return number phone.
     */
    public int getPhone() {
        return phone;
    }

    /**
     * Setter.
     *
     * @param phoneNew number phone.
     */
    public void setPhone(final int phoneNew) {
        phone = phoneNew;
    }

    /**
     * Getter.
     *
     * @return username or login.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter.
     *
     * @param usernameNew username or login.
     */
    public void setUsername(final String usernameNew) {
        username = usernameNew;
    }

    /**
     * Getter.
     *
     * @return password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter.
     *
     * @param passwordNew password.
     */
    public void setPassword(final String passwordNew) {
        password = passwordNew;
    }

    /**
     * Getter.
     *
     * @return role.
     */
    public Role getRole() {
        return role;
    }

    /**
     * Getter.
     *
     * @param roleNew role.
     */
    public void setRole(final Role roleNew) {
        role = roleNew;
    }

    /**
     * Getter.
     *
     * @return email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter.
     *
     * @param emailNew email.
     */
    public void setEmail(final String emailNew) {
        email = emailNew;
    }

    /**
     * Equal user with other any object.
     *
     * @param oNew any object.
     * @return equal or not.
     */
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
                && isBlocked() == user.isBlocked();
    }

    /**
     * Hash code.
     *
     * @return hash code.
     */
    @Override
    public int hashCode() {
        return Objects
                .hash(super.hashCode(), getUsername(), getPassword(), getRole(),
                        getEmail(), getPhone(), isBlocked(), getCubes());
    }

    /**
     * User to string.
     *
     * @return user.
     */
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
