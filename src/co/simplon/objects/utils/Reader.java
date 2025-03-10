package co.simplon.objects.utils;

import java.util.Scanner;

public class Reader {

    public static final Scanner SCANNER = new Scanner(System.in);

    private Reader(){}

    public static String read(){
        return SCANNER.nextLine();
    }
}
