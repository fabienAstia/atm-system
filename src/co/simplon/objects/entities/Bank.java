package co.simplon.objects.entities;

import java.util.List;

import static co.simplon.objects.utils.Builder.buildUserAccounts;
import static co.simplon.objects.utils.Parser.readBankFile;
import static co.simplon.objects.utils.PathProvider.bankPathFile;
import static co.simplon.objects.utils.Writer.writeBankFile;

public class Bank {

    private final List<Account> accounts = buildUserAccounts(readBankFile(bankPathFile));

    public Bank() {}

    public static void updateBankAccount(Account account) {
        List<List<String>> oldContent = readBankFile(bankPathFile);
        List<String> matchingAccount = getAccountByBban(account, oldContent);
        matchingAccount.set(2, account.getBalance().toString());
        matchingAccount.set(4, account.isActivated().toString());
        writeBankFile(bankPathFile, oldContent);
    }

    private static List<String> getAccountByBban(Account account, List<List<String>> bankAccountFileContent) {
        return bankAccountFileContent.stream()
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
