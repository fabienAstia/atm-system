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
                                record.get(1),
                                toInt(record.get(2)),
                                record.get(3),
                                activated = toBool(record.get(4)));
                        accounts.add(userAccount);
        });
        return accounts;
    }

}
