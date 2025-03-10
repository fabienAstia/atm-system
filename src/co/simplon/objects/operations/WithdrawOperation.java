package co.simplon.objects.operations;

import co.simplon.objects.entities.Card;
import co.simplon.objects.entities.UserAccount;

import java.util.Objects;

import static co.simplon.objects.entities.AtmService.count;
import static co.simplon.objects.entities.AtmService.verified;
import static co.simplon.objects.utils.Printer.*;
import static co.simplon.objects.utils.Converter.onlyDigits;
import static co.simplon.objects.utils.Converter.toInt;
import static co.simplon.objects.utils.Reader.read;

public class WithdrawOperation {

    private static final int AVAILABLE_CASH = 10000;

    public WithdrawOperation() {}

    public void doWithdraw(UserAccount userAccount, Card card) {
        while (count < 3) {
            if (!verified) {
                pincodeMessage();
                String input = read();
                if (card.verifyPinCode(input, userAccount)) {
                    verified = true;
                    count = 0;
                    operation(userAccount);
                    return;
                }
                count++;
                if (count == 3) {
                    pincodeMessage();
                    card.lockCard(userAccount);
                }
            } else {
                operation(userAccount);
                return;
            }
        }
    }

    private void operation(UserAccount userAccount) {
        String amountChoice = chooseAmount();
        String customAmount;
        if (amountChoice.equals("6")) {
            do {
                chooseAmountMsg();
                customAmount = read();
            } while(isInvalid(userAccount, customAmount));
        } else if (onlyDigits(amountChoice) && toInt(amountChoice) >= 1 && toInt(amountChoice) <= 5){
            customAmount = String.valueOf(Objects.requireNonNull(FixedAmount.fromChoice(amountChoice)).getValue());
            withdrawIfPossible(userAccount, customAmount);
        }
    }

    private boolean isInvalid(UserAccount userAccount, String customAmount) {
        return !customAmount.equals("X")
                && (!onlyDigits(customAmount) || !withdrawIfPossible(userAccount, customAmount));
    }

    private boolean withdrawIfPossible(UserAccount userAccount, String customAmount) {
        if (checkUserHasMoney(userAccount, toInt(customAmount))
                && checkAtmHasMoney(toInt(customAmount))
                && checkValidAmount(toInt(customAmount))) {
            successWithdrawMsg();
            userAccount.setBalance(userAccount.getBalance() - toInt(customAmount));
            return true;
        }
        return false;
    }

    public String chooseAmount() {
        displayAmounts();
        return read();
    }

    private boolean checkUserHasMoney(UserAccount userAccount, int amount) {
        if (userAccount.getBalance() < amount) {
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