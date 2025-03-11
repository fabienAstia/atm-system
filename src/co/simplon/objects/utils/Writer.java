package co.simplon.objects.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class Writer {

    private Writer(){}

    public static void write(String filePath, List<List<String>> contentToUpdate){
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
}

