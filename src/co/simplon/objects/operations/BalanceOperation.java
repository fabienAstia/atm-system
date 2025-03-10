package co.simplon.objects.operations;

import co.simplon.objects.Card;
import co.simplon.objects.MessagePrinter;
import co.simplon.objects.UserInfo;

import java.util.Scanner;

import static co.simplon.objects.operations.UtilsOperation.*;

public class BalanceOperation {

    public BalanceOperation() {}

    public void getBalance(Scanner scanner, UserInfo userInfo, Card card){
        while(count < 3) {
            if (!verified) {
                MessagePrinter.displayMessage();
                String input = scanner.nextLine();
                if (verifyPinCode(input, card, userInfo)) {
                    count = 0;
                    MessagePrinter.balanceMsg(userInfo.getBalance());
                    return;
                }
                count++;
                if (count == 3) {
                    MessagePrinter.displayMessage();
                    lockCard(card, userInfo);
                }
            } else {
                MessagePrinter.balanceMsg(userInfo.getBalance());
                return;
            }
        }
    }
}