package co.simplon.objects.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public final class Writer {

    private Writer(){}

    public static void write(String filePath, List<List<String>> contentToUpdate){
        try (FileWriter writer = new FileWriter(filePath)) {
             writer.write(contentToUpdate.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

