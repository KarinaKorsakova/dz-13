package utils;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CsvReader {

    public static <T> List<T> getListObjectsFromCsv(String path, Class<? extends T> type) {
        try {
            BufferedReader reader = Files.newBufferedReader(Paths.get(path));
            CsvToBean<T> csvObjectBuilder = new CsvToBeanBuilder<T>(reader)
                    .withType(type)
                    .build();
            return csvObjectBuilder.parse();
        } catch (IOException ex) {
            throw new RuntimeException("The file could not be found" + path + ex);
        }
    }

}
