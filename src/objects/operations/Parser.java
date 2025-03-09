package objects.operations;

import objects.UserInfo;

import java.io.*;
import java.util.Scanner;

public final class Parser {

    public static Integer initialBalance;
    public static Boolean activated;

    public static String readFile(String path) {
        File file = new File(path);
        StringBuilder fileContent = new StringBuilder();

        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while (sc.hasNextLine())
            fileContent.append(sc.nextLine()).append(" ");
        return fileContent.toString();
    }

    public static UserInfo buildUserInfo(String fileContent){
        String[] array = fileContent.split(",");
        String pincodeString = array[0].trim();
        String pinCode = pincodeString.split("= ")[1].trim();
        String balanceString = array[1].trim();
        initialBalance = Integer.parseInt(balanceString.split("= ")[1].trim());
        String activatedString = array[2].trim();
        activated = Boolean.parseBoolean(activatedString.split("= ")[1].trim());
        return new UserInfo(pinCode, initialBalance, activated);
    }
}