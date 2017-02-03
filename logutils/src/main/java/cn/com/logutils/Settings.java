package cn.com.logutils;

/**
 * Created by zf on 17-2-3.
 * 设置类
 */
public class Settings {
    //允许日志输出
    public boolean configAllowLog = true;

    //配置前缀
    public String configTagPrefix = "";

    public Settings setConfigAllowLog(boolean configAllowLog) {
        this.configAllowLog = configAllowLog;
        return this;
    }

    public Settings setConfigTagPrefix(String configTagPrefix) {
        this.configTagPrefix = configTagPrefix;
        return this;
    }

    public boolean getConfigAllowLog(){
        return configAllowLog;
    }

    public String getConfigTagPrefix(){
        return configTagPrefix;
    }
}
