package objects.operations;

import objects.UserInfo;

import java.io.*;
import java.nio.file.FileSystemNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static objects.AtmService.PATH;
import static objects.operations.Parser.*;

public final class Writer {

    private static final String oldContent = readFile(PATH);

    public static void updateAccountInfos(String filePath, UserInfo userInfo) {
        try (FileWriter writer = new FileWriter(filePath)) {
            List<String> accounts = new ArrayList<>(List.of(oldContent.split("; ")));
            String account = accounts.stream()
                    .filter(a -> a.split(", ")[0].split("= ")[1].equals(userInfo.getPincode())).findAny().orElseThrow(FileSystemNotFoundException::new);
            List<String> details = new ArrayList<>(Arrays.asList(account.split(", ")));
            String updateBalance = details.get(1).replaceFirst(details.get(1).split("= ")[1], userInfo.getBalance().toString());
            String updateStatus = details.get(2).replaceFirst(details.get(2).split("= ")[1], String.valueOf(userInfo.isActivated()));
            details.set(1, updateBalance);
            details.set(2, updateStatus);
            accounts.set(1, String.join(", ", details));
            writer.write(String.join(";\r", accounts));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

