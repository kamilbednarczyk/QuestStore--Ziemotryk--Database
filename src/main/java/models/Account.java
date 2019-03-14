package models;


public class Account {
    private int accountId;
    private String login;
    private String password;
    private int permission;

    // Constructor for new Account
    public Account(String login,
                   String password,
                   int permission) {
        if(permission > 3 && permission < 1) {
            throw new IllegalArgumentException(
                    "Permission of an account "
                  + "can only be equal 1, 2 or 3"
            );
        }
        this.login = login;
        this.password = password;
        this.permission = permission;
    }

    // Constructor for existing Account
    public Account(int accountId,
                   String login,
                   String password,
                   int permission) {
        this(login, password, permission);
        this.accountId = accountId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }
}
