package co.simplon.objects.entities;

import co.simplon.objects.operations.*;
import co.simplon.objects.utils.Parser;

import java.util.List;

import static co.simplon.objects.utils.Builder.buildUsersInfo;
import static co.simplon.objects.utils.Parser.readFile;
import static co.simplon.objects.utils.Printer.*;
import static co.simplon.objects.utils.Reader.*;
import static co.simplon.objects.utils.Writer.updateAccountInfos;

public class AtmService {

    public static final String PATH = "src/userInfo.txt";
    public static boolean verified = false;
    public static Integer count = 0;

    public AtmService() {}

    public void doOperations() {
        List<UserInfo> usersInfo = buildUsersInfo(readFile(PATH));
        UserInfo userInfo = usersInfo.get(1);
        Card card = new Card(userInfo);

        String input;
        do{
            insertCardMsg();
            input = read();
        } while (!input.equals("X"));
        String choice;
        do {
            choice = chooseOperation();
            if (choice.equals("1")) {
                BalanceOperation balanceOperation = new BalanceOperation();
                balanceOperation.getBalance(userInfo, card);
            }
            if (choice.equals("2")) {
                WithdrawOperation withdrawOperation = new WithdrawOperation();
                withdrawOperation.doWithdraw(userInfo, card);
            }
        } while (!choice.equals("X"));
        quitMsg();
        updateAccountInfos(PATH, userInfo);
    }

    public static String chooseOperation(){
        displayOperations();
        return read();
    }

}