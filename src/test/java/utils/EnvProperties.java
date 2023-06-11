package utils;

public class EnvProperties {

    private static final String PATH = "src\\test\\resources\\test.properties";
    private static final String URL = "url";

    public static String getUrl(){
        String url = System.getProperty(URL);
        return url == null ? PropertiesReader.getProperty(PATH, URL) : url;
    }

}
