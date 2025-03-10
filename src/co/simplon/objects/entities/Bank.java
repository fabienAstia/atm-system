package co.simplon.objects.entities;

import java.util.List;

import static co.simplon.objects.utils.Builder.buildUserAccounts;
import static co.simplon.objects.utils.Parser.readFile;

public class Bank {

    private static final String PATH = "src/bank_accounts.csv";
    private List<UserAccount> accounts = buildUserAccounts(readFile(PATH));

    public Bank() {
    }
}
