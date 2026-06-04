package service;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CsvExportService {
    private static final String PATH = "./audit.csv";

    public static void scrieAudit(String numeActiune) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String line = numeActiune + "," + now.format(formatter) + "\n";

        File file = new File(PATH);

        try (FileWriter fw = new FileWriter(file, true)) {
            fw.write(line);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
