package co.simplon.objects.entities;

import java.util.List;

import static co.simplon.objects.utils.Builder.buildUserAccounts;
import static co.simplon.objects.utils.Parser.readFile;
import static co.simplon.objects.utils.Writer.write;

public class Bank {

    private static final String PATH = "src/bank_accounts.csv";
    private List<UserAccount> accounts = buildUserAccounts(readFile(PATH));

    public Bank() {}

    public static void updateBankAccount(UserAccount userAccount) {
        List<List<String>> oldContent = readFile(PATH);
        List<String> matchingAccount = getAccountByBban(userAccount, oldContent);
        matchingAccount.set(2, userAccount.getBalance().toString());
        matchingAccount.set(4, userAccount.isActivated().toString());
        write(PATH, oldContent);
    }

    private static List<String> getAccountByBban(UserAccount userAccount, List<List<String>> oldContent) {
        return oldContent.stream()
                .filter(row -> String.valueOf(row.get(1)).equals(userAccount.getBban()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Aucun compte trouvé avec ce PIN."));
    }

    public List<UserAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<UserAccount> accounts) {
        this.accounts = accounts;
    }
}
