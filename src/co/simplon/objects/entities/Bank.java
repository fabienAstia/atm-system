package co.simplon.objects.entities;

import java.util.List;

import static co.simplon.objects.utils.Builder.buildUserAccounts;
import static co.simplon.objects.utils.Parser.readFile;
import static co.simplon.objects.utils.Writer.write;

public class Bank {

    private static final String PATH = "src/bank_accounts.csv";
    private List<UserAccount> accounts = buildUserAccounts(readFile(PATH));

    public Bank() {
    }

    public static void updateBankAccount(UserAccount userAccount) {
        List<List<String>> oldContent = readFile(PATH);
        List<String> matchingAccount = oldContent.stream()
                .filter(row -> String.valueOf(row.get(2)).equals(userAccount.getPincode()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Aucun compte trouvé avec ce PIN."));
        matchingAccount.set(1, userAccount.getBalance().toString());
        matchingAccount.set(3, userAccount.isActivated().toString());
        write(PATH, oldContent);
    }
}
