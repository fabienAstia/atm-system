package co.simplon.objects.utils;

import co.simplon.objects.entities.Account;

import java.util.ArrayList;
import java.util.List;

import static co.simplon.objects.utils.Converter.toBool;
import static co.simplon.objects.utils.Converter.toInt;

public class Builder {

    public static Boolean activated;

    public static List<Account> buildUserAccounts(List<List<String>> datas){
        List<Account> accounts = new ArrayList<>();
        datas.stream()
                .skip(1)
                .forEach(record -> {
                        Account account = new Account(
                                toInt(record.get(0)),
                                record.get(1),
                                toInt(record.get(2)),
                                record.get(3),
                                activated = toBool(record.get(4)));
                        accounts.add(account);
        });
        return accounts;
    }

    public static String buildAtmCash(List<String> cashInfos){
        return cashInfos.stream()
                .skip(1)
                .toList()
                .get(0);
    }

}
