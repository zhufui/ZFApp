package cn.com.zfapp.manager;

import cn.com.logutils.LogUtils;

/**
 * Created by zf on 17-2-16.
 * 初始化操作管理
 */
public class InitManager {

    public static void initLogUtils() {
        //设置日志是否输出
        LogUtils.setConfigAllowLog(true);
        //设置tag前缀
        LogUtils.setConfigTagPrefix("ZFApp ");
        //上面两个配置可以在application中配置
    }
    
}
