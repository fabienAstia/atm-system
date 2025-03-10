package co.simplon.objects.entities;

import java.util.List;

import static co.simplon.objects.utils.Builder.buildUserAccounts;

public class Bank {

    private static final String PATH = "src/userInfo.txt";
    private List<UserAccount> accounts = buildUserAccounts(PATH);

    public Bank() {
    }
}
