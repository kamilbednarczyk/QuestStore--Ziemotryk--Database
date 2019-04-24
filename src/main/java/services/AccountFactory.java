package services;

import models.Account;

import java.util.Map;

public class AccountFactory {

    public Account getAdminAccountFromForm(Map<String, String> inputs) {
        return getAccountFromFormWithPermission(inputs, 3);
    }

    public Account getMentorAccountFromForm(Map<String, String> inputs) {
        return getAccountFromFormWithPermission(inputs, 2);
    }

    public Account getCodecoolerAccountFromForm(Map<String, String> inputs) {
        return getAccountFromFormWithPermission(inputs, 1);
    }

    private Account getAccountFromFormWithPermission(Map<String, String> inputs, int permission) {
        return new Account(
                inputs.get("login"),
                inputs.get("password"),
                permission
        );
    }
}
