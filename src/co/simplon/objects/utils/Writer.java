package co.simplon.objects.utils;

import co.simplon.objects.entities.UserAccount;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static co.simplon.objects.entities.AtmService.PATH;

public final class Writer {

    private static final List<List<String>> oldContent = Parser.readFile(PATH);

    private Writer(){}

    public static void write(String filePath, UserAccount userAccount){
        try (FileWriter writer = new FileWriter(filePath)) {
            List<String> matchingAccount = oldContent.stream()
                    .filter(row -> String.valueOf(row.get(2)).equals(userAccount.getPincode()))
                    .findAny()
                    .orElseThrow(() -> new IllegalArgumentException("Aucun compte trouvé avec ce PIN."));
            matchingAccount.set(1, userAccount.getBalance().toString());
            matchingAccount.set(3, userAccount.isActivated().toString());
            writer.write(oldContent.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

