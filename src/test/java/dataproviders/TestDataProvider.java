package dataproviders;

import models.Developers;
import org.testng.annotations.DataProvider;


import static utils.CsvReader.getListObjectsFromCsv;
import static utils.DBReaderAllQueries.getDevelopersFromDB;


public class TestDataProvider {

    private static final String PATH = "src\\test\\resources\\data.csv";
    public static Developers developers;


    @DataProvider(name = "developersDB")
    public static Object[] @NotNull [] DevelopersFromDB() {



        return getListObjectsFromCsv(PATH, DevelopersFromDB() developers.class).stream().map(developers -> new Object[]{developers.getName(),
                developers.getPosition(), developers.getSalary()}).toArray(Object[][]::new);

    }

    public static void main(String[] args) {
        getDevelopersFromDB();
    }
}