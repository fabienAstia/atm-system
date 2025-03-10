package co.simplon.objects.operations;

import java.util.Scanner;

public class InputReader {
    public static final Scanner SCANNER = new Scanner(System.in);
    public static String input;

    public static String read(){
        return input = SCANNER.nextLine();
    }
}
