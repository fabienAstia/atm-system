package co.simplon.objects.utils;

import co.simplon.objects.entities.UserInfo;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public final class Parser {

    public static Boolean activated;

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

    public static List<UserInfo> buildUsersInfo(String fileContent){
        List<String> accountInfos = new ArrayList<>(Arrays.asList(fileContent.split("; ")));
        List<UserInfo> userInfoList = new ArrayList<>();
        accountInfos.forEach(account -> {
            String[]infos = account.split(",");
            String pincodeInfo = infos[0].trim();
            String pincode = pincodeInfo.split("= ")[1].trim();
            String balanceInfo = infos[1].trim();
            Integer initialBalance = Integer.parseInt(balanceInfo.split("= ")[1].trim());
            String activatedInfo = infos[2].trim();
            activated = Boolean.parseBoolean(activatedInfo.split("= ")[1].trim());
            userInfoList.add(new UserInfo(pincode, initialBalance, activated));
        });
        return userInfoList;
    }
}