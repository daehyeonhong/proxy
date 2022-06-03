package hello.proxy.app.util;

public class Utils {
    public static void sleep(final int mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }
}
