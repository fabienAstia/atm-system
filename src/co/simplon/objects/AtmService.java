package co.simplon.objects;

import co.simplon.objects.operations.*;

import java.util.List;

import static co.simplon.objects.MessagePrinter.*;
import static co.simplon.objects.operations.InputReader.*;
import static co.simplon.objects.operations.UtilsOperation.*;
import static co.simplon.objects.operations.Writer.updateAccountInfos;

public class AtmService {

    public static final String PATH = "src/userInfo.txt";

    public AtmService() {}

    public void doOperations() {
        List<UserInfo> usersInfo = Parser.buildUsersInfo(Parser.readFile(PATH));
        UserInfo userInfo = usersInfo.get(1);
        Card card = new Card(userInfo);
        String input;
        do{
            insertCardMsg();
            input = read();
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

//    private void readInput(){
//        input = read();
//    }

    public static void chooseOperation(){
        displayOperations();
        choice = read();
    }

}