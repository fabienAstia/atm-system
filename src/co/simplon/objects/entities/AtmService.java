package co.simplon.objects.entities;

import co.simplon.objects.operations.*;

import java.util.List;

import static co.simplon.objects.utils.Builder.buildUserAccounts;
import static co.simplon.objects.utils.Parser.readFile;
import static co.simplon.objects.utils.Printer.*;
import static co.simplon.objects.utils.Reader.*;

public class AtmService {

    public static final String PATH = "src/bank_accounts.csv";
    public static boolean verified = false;
    public static Integer count = 0;

    public AtmService() {}

    public void doOperations() {
        List<UserAccount> accounts = buildUserAccounts(readFile(PATH));
        UserAccount userAccount = accounts.get(1);
        Card card = new Card(userAccount);

        String input;
        do{
            insertCardMsg();
            input = read();
        } while (!input.equals("X"));
        String choice;
        do {
            choice = chooseOperation();
            if (choice.equals("1")) {
                getBalance(userAccount, card);
            }
            if (choice.equals("2")) {
                withdraw(userAccount, card);
            }
        } while (!choice.equals("X"));
        quitMsg();
        Bank.updateBankAccount(userAccount);
    }

    private static void withdraw(UserAccount userAccount, Card card) {
        WithdrawOperation withdrawOperation = new WithdrawOperation();
        withdrawOperation.doWithdraw(userAccount, card);
    }

    private static void getBalance(UserAccount userAccount, Card card) {
        BalanceOperation balanceOperation = new BalanceOperation();
        balanceOperation.getBalance(userAccount, card);
    }

    public static String chooseOperation(){
        displayOperations();
        return read();
    }

}