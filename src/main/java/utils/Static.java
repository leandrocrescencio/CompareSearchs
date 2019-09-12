package utils;

public class Static {

    private Static() {
        throw new IllegalStateException("Utility class");
    }

    public static final String PATH_PROJECT = System.getProperty("user.dir");

    public static final String OS = System.getProperty("os.name");

    public static final int TIMEOUT = 30;

    protected static String testname = "test";

    public static String getTestName() {
        return testname;
    }

    public static void setTestName(String className) {
        testname = className;
    }

}