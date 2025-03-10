package co.simplon.objects.utils;

import java.io.*;
import java.util.Scanner;

public final class Parser {

    private Parser(){}

    public static String readFile(String path) {
        File file = new File(path);
        StringBuilder fileContent = new StringBuilder();

        Scanner sc;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while (sc.hasNextLine())
            fileContent.append(sc.nextLine()).append(" ");
        return fileContent.toString();
    }

}