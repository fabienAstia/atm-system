package objects;

import objects.operations.BalanceOperation;
import objects.operations.WithdrawOperation;

import java.util.List;
import java.util.Scanner;

import static objects.operations.Parser.buildUsersInfo;
import static objects.operations.Parser.readFile;
import static objects.operations.UtilsOperation.*;
import static objects.operations.Writer.updateAccountInfos;

public class AtmService {

    private static final Scanner scanner = new Scanner(System.in);
    public static final String PATH = "src/userInfo.txt";
    private static String input;

    public AtmService() {}

    public void doOperations() {
        List<UserInfo> usersInfo = buildUsersInfo(readFile(PATH));
        UserInfo userInfo = usersInfo.get(1);
        Card card = new Card(userInfo);

        do{
            System.out.println("Veuillez insérer votre carte (Taper 'X')");
            read();
        } while (!input.equals("X"));
        do {
            chooseOperation();
            if (choice.equals("1")) {
                BalanceOperation balanceOperation = new BalanceOperation();
                balanceOperation.getBalance(scanner, userInfo, card);
            }
            if (choice.equals("2")) {
                WithdrawOperation withdrawOperation = new WithdrawOperation();
                withdrawOperation.doWithdraw(scanner, userInfo, card);
            }
        } while (!choice.equals("X"));
        System.out.println("bye bye");
        updateAccountInfos(PATH, userInfo);
    }

    private void read(){
        input = scanner.nextLine();
    }

    public static void chooseOperation(){
        displayOperations();
        choice = scanner.nextLine();
    }

    public static void displayOperations() {
        System.out.println("Veuillez choisir une opération : ");
        System.out.println("Taper 1 - Consulter le solde de votre compte");
        System.out.println("Taper 2 - Effectuer un retrait");
        System.out.println("Taper X - Quitter le programme");
    }
}