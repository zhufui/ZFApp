package cn.com.logutils;

/**
 * Created by zf on 17-2-3.
 */
public final class LogUtils {
    private static Logger logger = new Logger();
    private static Settings settings = new Settings();

    /**
     * 设置日志是否输出
     */
    public static Settings setConfigAllowLog(boolean configAllowLog) {
        settings.setConfigAllowLog(configAllowLog);
        return settings;
    }

    /**
     * 设置tag前缀
     */
    public static Settings setConfigTagPrefix(String configTagPrefix){
        settings.setConfigTagPrefix(configTagPrefix);
        return settings;
    }

    public static boolean getConfigAllowLog(){
        return settings.getConfigAllowLog();
    }

    public static String getConfigTagPrefix(){
        return settings.getConfigTagPrefix();
    }

    private static StackTraceElement getStackTrace() {
        return Thread.currentThread().getStackTrace()[4];
    }

    /**
     * verbose输出
     *
     * @param msg
     * @param args
     */
    public static void v(String msg, Object... args) {
        logger.v(getStackTrace(), msg, args);
    }

    public static void v(Object object) {
        logger.v(getStackTrace(), object);
    }

    /**
     * debug输出
     *
     * @param msg
     * @param args
     */
    public static void d(String msg, Object... args) {
        logger.d(getStackTrace(), msg, args);
    }

    public static void d(Object object) {
        logger.d(getStackTrace(), object);
    }

    /**
     * info输出
     *
     * @param msg
     * @param args
     */
    public static void i(String msg, Object... args) {
        logger.i(getStackTrace(), msg, args);
    }

    public static void i(Object object) {
        logger.i(getStackTrace(), object);
    }

    /**
     * warn输出
     *
     * @param msg
     * @param args
     */
    public static void w(String msg, Object... args) {
        logger.w(getStackTrace(), msg, args);
    }

    public static void w(Object object) {
        logger.w(getStackTrace(), object);
    }

    /**
     * error输出
     *
     * @param msg
     * @param args
     */
    public static void e(String msg, Object... args) {
        logger.e(getStackTrace(), msg, args);
    }

    public static void e(Object object) {
        logger.e(getStackTrace(), object);
    }

    /**
     * assert输出
     *
     * @param msg
     * @param args
     */
    public static void wtf(String msg, Object... args) {
        logger.wtf(getStackTrace(), msg, args);
    }

    public static void wtf(Object object) {
        logger.wtf(getStackTrace(), object);
    }
}
