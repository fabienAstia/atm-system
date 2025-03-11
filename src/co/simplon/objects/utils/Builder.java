package co.simplon.objects.utils;

import co.simplon.objects.entities.UserAccount;

import java.util.ArrayList;
import java.util.List;

import static co.simplon.objects.utils.Converter.toBool;
import static co.simplon.objects.utils.Converter.toInt;

public class Builder {

    public static Boolean activated;

    public static List<UserAccount> buildUserAccounts(List<List<String>> datas){
        List<UserAccount> accounts = new ArrayList<>();
        datas.stream()
                .skip(1)
                .forEach(record -> {
                        UserAccount userAccount = new UserAccount(
                                toInt(record.get(0)),
                                toInt(record.get(1)),
                                record.get(2),
                                activated = toBool(record.get(3)));
                        accounts.add(userAccount);
        });
        return accounts;
    }

}
