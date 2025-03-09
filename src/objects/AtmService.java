package objects;

import objects.operations.BalanceOperation;
import objects.operations.WithdrawOperation;

import java.util.Scanner;

import static objects.operations.Parser.buildUserInfo;
import static objects.operations.Parser.readFile;
import static objects.operations.UtilsOperation.*;
import static objects.operations.Writer.update;

public class AtmService {

    private static final Scanner scanner = new Scanner(System.in);
    public static final String path = "src/userInfo.txt";

    public AtmService() {}

    public void doOperations() {
        UserInfo userInfo = buildUserInfo(readFile(path));
        Card card = new Card(userInfo);

        System.out.println("Veuillez insérer votre carte (Taper 'X')");
        if(scanner.nextLine().equals("X")){
            do{
                chooseOperation();
                if(choice.equals("1")){
                    //Operation 1
                    BalanceOperation balanceOperation = new BalanceOperation();
                    balanceOperation.getBalance(scanner, userInfo, card);
                }
                if(choice.equals("2")){
                    //Operation 2
                    WithdrawOperation withdrawOperation = new WithdrawOperation();
                    withdrawOperation.doWithdraw(scanner, userInfo, card);
                }
            }while (!choice.equals("X"));
            System.out.println("bye bye");
            update(path, userInfo);
        }
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