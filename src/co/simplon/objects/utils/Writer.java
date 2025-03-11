package co.simplon.objects.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static co.simplon.objects.operations.WithdrawOperation.availableCash;

public final class Writer {

    private Writer(){}

    public static void writeBankFile(String filePath, List<List<String>> contentToUpdate){
        try (FileWriter writer = new FileWriter(filePath)) {
            StringBuilder newContent = new StringBuilder();
            contentToUpdate.forEach(account -> {
                List<String> row = new ArrayList<>(account);
                newContent.append(String.join(";", row))
                        .append(System.lineSeparator());
            });
             writer.write(newContent.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeAtmFile(String filePath, List<String> contentToUpdate){
        try (FileWriter writer = new FileWriter(filePath)) {
            String newContent = contentToUpdate.get(0) +
                    System.lineSeparator() +
                    availableCash.toString();
            writer.write(newContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

