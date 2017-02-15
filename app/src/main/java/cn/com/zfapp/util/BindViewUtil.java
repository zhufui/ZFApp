package cn.com.zfapp.util;

import android.util.Log;
import android.view.View;

/**
 * 绑定视图的工具类
 */
public class BindViewUtil {

    /**
     * 通过id找到对应的视图
     * 使用方式是:
     * ImageView img = findView(rootView,id);
     * 如果直接写在方法中就需要强转,例如:methodName((ImageView)findView(rootView,id))
     *
     * @param view 父视图
     * @param id   id
     * @param <E>  返回的控件
     * @return
     */
    public static <E extends View> E findView(View view, int id) {
        try {
            return (E) view.findViewById(id);
        } catch (ClassCastException e) {
            Log.e("BindViewUtil", "Could not cast View to concrete class.", e);
            throw e;
        }
    }

}
