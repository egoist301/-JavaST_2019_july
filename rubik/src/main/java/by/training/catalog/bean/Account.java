package by.training.catalog.bean;

public class Account extends Entity {
    private String username;
    private String password;
    private AccountType accountType;
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

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(final AccountType accountTypeNew) {
        accountType = accountTypeNew;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String emailNew) {
        email = emailNew;
    }

}
