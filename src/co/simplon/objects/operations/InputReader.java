package co.simplon.objects.operations;

import java.util.Scanner;

public class InputReader {

    public static final Scanner SCANNER = new Scanner(System.in);

    private InputReader(){}

    public static String read(){
        return SCANNER.nextLine();
    }
}
