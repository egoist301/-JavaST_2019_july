package by.training.catalog.service.impl;

import by.training.catalog.service.PasswordEncoder;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

/**
 * Argon encoder implement password encoder.
 */
public class ArgonEncoder implements PasswordEncoder {
    /**
     * Argon2. Algorithm for encoding.
     */
    private Argon2 argon2 =
            Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

    /**
     * Encode password.
     *
     * @param password password.
     * @return hash.
     */
    @Override
    public String encodePassword(final String password) {
        final int iNew = 4;
        final int iNew1 = 256;
        return argon2.hash(2, iNew1 * iNew1, iNew, password);
    }

    /**
     * Verify passwords.
     *
     * @param password1 password1.
     * @param password2 password2.
     * @return equal or not.
     */
    @Override
    public boolean verify(final String password1, final String password2) {
        return argon2.verify(password1, password2);
    }
}
