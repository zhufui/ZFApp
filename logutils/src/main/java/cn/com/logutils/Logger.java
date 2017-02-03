package cn.com.logutils;

import android.text.TextUtils;
import android.util.Log;

import java.lang.reflect.Field;

/**
 * Created by zf on 17-2-3.
 */
public final class Logger implements LogImpl {

    /**
     * 基本数据类型
     */
    private final static String[] types = {"int", "java.lang.String", "boolean", "char", "float", "double", "long", "short", "byte"};

    /**
     * 将对象转化为String
     */
    public static <T> String objectToString(T object) {
        if (object == null)
            return "Null{object is null}";

        if (object.toString().startsWith(object.getClass().getName() + "@")) {
            StringBuilder builder = new StringBuilder(object.getClass().getSimpleName() + "{");
            Field[] fields = object.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                boolean flag = false;
                for (String type : types) {
                    if (field.getType().getName().equalsIgnoreCase(type)) {
                        flag = true;
                        Object value = null;
                        try {
                            value = field.get(object);
                        } catch (IllegalAccessException e) {
                            value = e;
                        } finally {
                            builder.append(String.format("%s=%s, ", field.getName(),
                                    value == null ? "null" : value.toString()));
                            break;
                        }
                    }
                }
                if (!flag) {
                    builder.append(String.format("%s=%s, ", field.getName(), "Object"));
                }
            }
            return builder.replace(builder.length() - 2, builder.length() - 1, "}").toString();
        } else {
            return object.toString();
        }
    }

    private void logString(LogType type, StackTraceElement element, String msg, Object... args) {
        if (!LogUtils.getConfigAllowLog())
            return;

        String tag = generateTag(element);
        msg = String.format(msg, args);
        switch (type) {
            case Verbose:
                Log.v(tag, msg);
                break;
            case Debug:
                Log.d(tag, msg);
                break;
            case Info:
                Log.i(tag, msg);
                break;
            case Warn:
                Log.w(tag, msg);
                break;
            case Error:
                Log.e(tag, msg);
                break;
            case Wtf:
                Log.wtf(tag, msg);
                break;
            default:
                break;
        }
    }

    /**
     * 打印对象
     */
    private void logObject(LogType type, StackTraceElement element, Object object) {
        if (object != null) {
            if (object instanceof Throwable) {
                String tag = generateTag(element);
                String msg = object.toString();
                Exception exception = (Exception) object;
                switch (type) {
                    case Verbose:
                        Log.v(tag, msg, exception);
                        break;
                    case Debug:
                        Log.d(tag, msg, exception);
                        break;
                    case Info:
                        Log.i(tag, msg, exception);
                        break;
                    case Warn:
                        Log.w(tag, msg, exception);
                        break;
                    case Error:
                        Log.e(tag, msg, exception);
                        break;
                    case Wtf:
                        Log.wtf(tag, msg, exception);
                        break;
                    default:
                        break;
                }
            } else if (object instanceof String) {
                logString(type, element, (String) object);
            } else {
                logString(type, element, objectToString(object));
            }
        } else {
            logString(type, element, "Null{object is null}");
        }
    }

    private String generateTag(StackTraceElement caller) {
        String stackTrace = caller.toString();
        stackTrace = stackTrace.substring(stackTrace.lastIndexOf('('), stackTrace.length());
        String tag = "%s%s.%s%s";
        String callerClazzName = caller.getClassName();
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        String Prefix = TextUtils.isEmpty(LogUtils.getConfigTagPrefix()) ? "" : LogUtils.getConfigTagPrefix();
        tag = String.format(tag, Prefix, callerClazzName, caller.getMethodName(), stackTrace);
        return tag;
    }

    @Override
    public void d(StackTraceElement element, String message, Object... args) {
        logString(LogType.Debug, element, message, args);
    }

    @Override
    public void d(StackTraceElement element, Object object) {
        logObject(LogType.Debug, element, object);
    }

    @Override
    public void e(StackTraceElement element, String message, Object... args) {
        logString(LogType.Error, element, message, args);
    }

    @Override
    public void e(StackTraceElement element, Object object) {
        logObject(LogType.Error, element, object);
    }

    @Override
    public void w(StackTraceElement element, String message, Object... args) {
        logString(LogType.Warn, element, message, args);
    }

    @Override
    public void w(StackTraceElement element, Object object) {
        logObject(LogType.Warn, element, object);
    }

    @Override
    public void i(StackTraceElement element, String message, Object... args) {
        logString(LogType.Info, element, message, args);
    }

    @Override
    public void i(StackTraceElement element, Object object) {
        logObject(LogType.Info, element, object);
    }

    @Override
    public void v(StackTraceElement element, String message, Object... args) {
        logString(LogType.Verbose, element, message, args);
    }

    @Override
    public void v(StackTraceElement element, Object object) {
        logObject(LogType.Verbose, element, object);
    }

    @Override
    public void wtf(StackTraceElement element, String message, Object... args) {
        logString(LogType.Wtf, element, message, args);
    }

    @Override
    public void wtf(StackTraceElement element, Object object) {
        logObject(LogType.Wtf, element, object);
    }

}
