package co.simplon.objects.operations;

import co.simplon.objects.entities.Account;
import co.simplon.objects.entities.Bank;
import co.simplon.objects.entities.Card;
import co.simplon.objects.entities.FixedAmount;
import co.simplon.objects.utils.Printer;

import java.util.Objects;

import static co.simplon.objects.entities.AtmService.count;
import static co.simplon.objects.entities.AtmService.verified;
import static co.simplon.objects.entities.Bank.checkAccountHasMoney;
import static co.simplon.objects.utils.Builder.buildAtmCash;
import static co.simplon.objects.utils.Parser.readAtmFile;
import static co.simplon.objects.utils.Printer.*;
import static co.simplon.objects.utils.Converter.onlyDigits;
import static co.simplon.objects.utils.Converter.toInt;
import static co.simplon.objects.utils.Reader.read;
import static co.simplon.objects.utils.Writer.writeAtmFile;

public class WithdrawOperation {

    public static Integer availableCash = toInt(buildAtmCash(readAtmFile("src/available_cash.csv")));
    String path = "src/available_cash.csv";

    public WithdrawOperation() {}

    public void doWithdraw(Account account, Card card, Bank bank) {
        while (count < 3) {
            if (!verified) {
                pincodeMessage();
                String input = read();
                if (card.verifyPinCode(input)) {
                    if(!account.isActivated()){
                        bank.activateAccount(account);
                        Printer.unlockCardMsg();
                    }
                    verified = true;
                    count = 0;
                    operation(account);
                    return;
                }
                count++;
                if (count == 3) {
                    pincodeMessage();
                    card.lockCard(account);
                }
            } else {
                operation(account);
                return;
            }
        }
    }

    private void operation(Account account) {
        String amountChoice = chooseAmount();
        String customAmount;
        if (amountChoice.equals("6")) {
            do {
                chooseAmountMsg();
                customAmount = read();
            } while(isInvalid(account, customAmount));
        } else if (onlyDigits(amountChoice) && toInt(amountChoice) >= 1 && toInt(amountChoice) <= 5){
            customAmount = String.valueOf(Objects.requireNonNull(FixedAmount.fromChoice(amountChoice)).getValue());
            withdrawIfPossible(account, customAmount);
        }
    }

    private boolean isInvalid(Account account, String customAmount) {
        return !customAmount.equals("X")
                && (!onlyDigits(customAmount) || !withdrawIfPossible(account, customAmount));
    }

    private boolean withdrawIfPossible(Account account, String customAmount) {
        if (checkUserHasMoney(account, toInt(customAmount))
                && checkAtmHasMoney(toInt(customAmount))
                && checkValidAmount(toInt(customAmount))) {
            successWithdrawMsg();
            account.setBalance(account.getBalance() - toInt(customAmount));
            availableCash -= toInt(customAmount);
            writeAtmFile(path, readAtmFile(path));
            return true;
        }
        return false;
    }

    private boolean checkUserHasMoney(Account account, int amount){
        if(!checkAccountHasMoney(account, amount)){
            notEnoughUserCashMsg();
            return false;
        }
        return true;
    }

    private String chooseAmount() {
        displayAmounts();
        return read();
    }

    private boolean checkAtmHasMoney(int amount) {
        if (availableCash < amount) {
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