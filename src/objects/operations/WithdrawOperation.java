package objects.operations;

import objects.Card;
import objects.UserInfo;

import java.util.Objects;
import java.util.Scanner;

import static objects.MessagePrinter.*;
import static objects.operations.UtilsOperation.*;
import static objects.operations.UtilsOperation.count;

public class WithdrawOperation {

    private final static Scanner SCANNER = new Scanner(System.in);
    private static final int AVAILABLE_CASH = 10000;
    private String amountChoice;
    private String customAmount;

    public WithdrawOperation() {}

    public void doWithdraw(Scanner scanner, UserInfo userInfo, Card card) {
        while (count < 3) {
            if (!verified) {
                displayMessage();
                String input = scanner.nextLine();
                if (verifyPinCode(input, card, userInfo)) {
                    count = 0;
                    operation(userInfo);
                    return;
                }
                count++;
                if (count == 3) {
                    displayMessage();
                    lockCard(card, userInfo);
                }
            } else {
                operation(userInfo);
                return;
            }
        }
    }

    private void operation(UserInfo userInfo) {
        chooseAmount();
        if (amountChoice.equals("6")) {
            do {
                chooseAmountMsg();
                read();
            } while(isInvalid(userInfo));
        } else if (onlyDigits(amountChoice) && toInt(amountChoice) >= 1 && toInt(amountChoice) <= 5){
            customAmount = String.valueOf(Objects.requireNonNull(FixedAmount.fromChoice(amountChoice)).getValue());
            withdrawIfPossible(userInfo);
        }
    }

    private boolean isInvalid(UserInfo userInfo) {
        return !customAmount.equals("X") && (!onlyDigits(customAmount) || !withdrawIfPossible(userInfo));
    }

    private Integer toInt(String string){
        return Integer.parseInt(string);
    }

    private void read(){
        customAmount = SCANNER.nextLine();
    }

    private boolean onlyDigits(String input){
        return  input.matches("[0-9]+");
    }

    private boolean withdrawIfPossible(UserInfo userInfo) {
        if (checkUserHasMoney(userInfo, toInt(customAmount))
                && checkAtmHasMoney(toInt(customAmount))
                && checkValidAmount(toInt(customAmount))) {
            successWithdrawMsg();
            userInfo.setBalance(userInfo.getBalance() - toInt(customAmount));
            return true;
        }
        return false;
    }

    public void chooseAmount() {
        displayAmounts();
        amountChoice = SCANNER.nextLine();
    }

    private boolean checkUserHasMoney(UserInfo userInfo, int amount) {
        if (userInfo.getBalance() < amount) {
            notEnoughUserCashMsg();
            return false;
        }
        return true;
    }

    private boolean checkAtmHasMoney(int amount) {
        if (AVAILABLE_CASH < amount) {
            notEnoughAtmCashMsg();
            return false;
        }
        return true;
    }

    private boolean checkValidAmount(int amount) {
        if (amount % 10 != 0) {
            isTenMultipleMsg();
            return false;
        }
        if (amount > 500) {
            maxAmountToWithdrawMsg();
            return false;
        }
        if (amount == 0) {
            minAmountToWidthdrawMsg();
            return false;
        }
        return true;
    }
}