package by.training.catalog.service;

/**
 * Password encoder interface.
 */
public interface PasswordEncoder {
    /**
     * @param password password.
     * @return hash of password.
     */
    String encodePassword(String password);

    /**
     * Verify passwords.
     *
     * @param password1 password1.
     * @param password2 password2.
     * @return equal or not.
     */
    boolean verify(String password1, String password2);
}
