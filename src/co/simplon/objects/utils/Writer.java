package co.simplon.objects.utils;

import co.simplon.objects.entities.UserAccount;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static co.simplon.objects.entities.AtmService.PATH;

public final class Writer {

    private static final List<List<String>> oldContent = Parser.readFile(PATH);

    private Writer(){}

    public static void write(String filePath, UserAccount userAccount){
        try (FileWriter writer = new FileWriter(filePath)) {
            List<UserAccount> accounts = new ArrayList<>();
            List<String> matchingAccount = oldContent.stream()
                    .filter(row -> String.valueOf(row.get(2)).equals(userAccount.getPincode()))
                    .findAny()
                    .orElseThrow(() -> new IllegalArgumentException("Aucun compte trouvé avec ce PIN."));
            matchingAccount.set(1, userAccount.getBalance().toString());
            matchingAccount.set(3, userAccount.isActivated().toString());

            writer.write(oldContent.toString());

            //writer.write(String.join(";\r", accounts));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public static void updateAccountInfos(String filePath, UserAccount userAccount) {
//        try (FileWriter writer = new FileWriter(filePath)) {
//            List<String> accounts = new ArrayList<>(List.of(oldContent.split("; ")));
//            String account = accounts.stream()
//                    .filter(a -> a.split(", ")[0].split("= ")[1].equals(userAccount.getPincode())).findAny().orElseThrow(FileSystemNotFoundException::new);
//            List<String> details = new ArrayList<>(Arrays.asList(account.split(", ")));
//            String updateBalance = details.get(1).replaceFirst(details.get(1).split("= ")[1], userAccount.getBalance().toString());
//            String updateStatus = details.get(2).replaceFirst(details.get(2).split("= ")[1], String.valueOf(userAccount.isActivated()));
//            details.set(1, updateBalance);
//            details.set(2, updateStatus);
//            accounts.set(1, String.join(", ", details));
//            writer.write(String.join(";\r", accounts));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}

