package co.simplon.objects.operations;

import co.simplon.objects.entities.Card;
import co.simplon.objects.utils.Printer;
import co.simplon.objects.entities.UserInfo;

import java.util.Scanner;

import static co.simplon.objects.utils.UtilsOperation.*;

public class BalanceOperation {

    public BalanceOperation() {}

    public void getBalance(Scanner scanner, UserInfo userInfo, Card card){
        while(count < 3) {
            if (!verified) {
                Printer.displayMessage();
                String input = scanner.nextLine();
                if (verifyPinCode(input, card, userInfo)) {
                    count = 0;
                    Printer.balanceMsg(userInfo.getBalance());
                    return;
                }
                count++;
                if (count == 3) {
                    Printer.displayMessage();
                    lockCard(card, userInfo);
                }
            } else {
                Printer.balanceMsg(userInfo.getBalance());
                return;
            }
        }
    }
}