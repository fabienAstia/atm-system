package co.simplon.objects.entities;

import java.util.List;

import static co.simplon.objects.utils.Builder.buildUserAccounts;
import static co.simplon.objects.utils.Parser.readFile;
import static co.simplon.objects.utils.Writer.write;

public class Bank {

    private static final String PATH = "src/bank_accounts.csv";
    private List<Account> accounts = buildUserAccounts(readFile(PATH));

    public Bank() {}

    public static void updateBankAccount(Account account) {
        List<List<String>> oldContent = readFile(PATH);
        List<String> matchingAccount = getAccountByBban(account, oldContent);
        matchingAccount.set(2, account.getBalance().toString());
        matchingAccount.set(4, account.isActivated().toString());
        write(PATH, oldContent);
    }

    private static List<String> getAccountByBban(Account account, List<List<String>> oldContent) {
        return oldContent.stream()
                .filter(row -> String.valueOf(row.get(1)).equals(account.getBban()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Aucun compte trouvé avec ce PIN."));
    }

    public void activateAccount(Account account){
        account.setIsActivated(true);
    }

    public static boolean checkAccountHasMoney(Account account, int amount) {
        return account.getBalance() >= amount;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

}
