package co.simplon.objects.utils;

import co.simplon.objects.entities.UserInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Builder {

    public static Boolean activated;

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
