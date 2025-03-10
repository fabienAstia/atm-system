package objects;

import objects.operations.BalanceOperation;
import objects.operations.WithdrawOperation;

import java.util.List;
import java.util.Scanner;

import static objects.MessagePrinter.*;
import static objects.operations.Parser.buildUsersInfo;
import static objects.operations.Parser.readFile;
import static objects.operations.UtilsOperation.*;
import static objects.operations.Writer.updateAccountInfos;

public class AtmService {

    private static final Scanner SCANNER = new Scanner(System.in);
    public static final String PATH = "src/userInfo.txt";
    private static String input;

    public AtmService() {}

    public void doOperations() {
        List<UserInfo> usersInfo = buildUsersInfo(readFile(PATH));
        UserInfo userInfo = usersInfo.get(1);
        Card card = new Card(userInfo);

        do{
            insertCardMsg();
            read();
        } while (!input.equals("X"));
        do {
            chooseOperation();
            if (choice.equals("1")) {
                BalanceOperation balanceOperation = new BalanceOperation();
                balanceOperation.getBalance(SCANNER, userInfo, card);
            }
            if (choice.equals("2")) {
                WithdrawOperation withdrawOperation = new WithdrawOperation();
                withdrawOperation.doWithdraw(SCANNER, userInfo, card);
            }
        } while (!choice.equals("X"));
        quitMsg();
        updateAccountInfos(PATH, userInfo);
    }

    private void read(){
        input = SCANNER.nextLine();
    }

    public static void chooseOperation(){
        displayOperations();
        choice = SCANNER.nextLine();
    }

}