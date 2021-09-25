package uz.jurayev.spring_webflux_ms.utils;

public class SleepUtil {

    public static void sleep(int seconds){
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
