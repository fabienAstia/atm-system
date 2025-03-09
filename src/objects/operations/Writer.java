package objects.operations;

import objects.UserInfo;

import java.io.*;

import static objects.AtmService.path;
import static objects.operations.Parser.*;

public final class Writer {

    private static final String oldContent = readFile(path);

    public static void update(String filePath, UserInfo userInfo) {
        try (FileWriter writer = new FileWriter(filePath)) {
            String withUpdateBalance = oldContent.replaceFirst(String.valueOf(initialBalance), userInfo.getBalance().toString());
            String newContent = withUpdateBalance.replaceAll(String.valueOf(activated), String.valueOf(userInfo.isActivated()));
            writer.write(newContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

