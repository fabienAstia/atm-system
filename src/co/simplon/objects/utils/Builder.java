package co.simplon.objects.utils;

import co.simplon.objects.entities.UserAccount;

import java.util.ArrayList;
import java.util.List;

public class Builder {

    public static Boolean activated;

    public static List<UserAccount> buildUserAccounts(List<List<String>> datas){
        List<UserAccount> accounts = new ArrayList<>();
        datas.stream()
                .skip(1)
                .forEach(record -> {
                        UserAccount userAccount = new UserAccount(
                                Integer.parseInt(record.get(0)),
                                Integer.parseInt(record.get(1)),
                                record.get(2),
                                activated = Boolean.parseBoolean(record.get(3)));
                        accounts.add(userAccount);
        });
        return accounts;
    }

}
