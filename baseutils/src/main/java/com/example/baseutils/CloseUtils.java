package com.example.baseutils;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by zf on 17-2-22.
 * 1.关闭cursor
 * 2.关闭数据库
 * 3.关闭IO
 * 4.安静关闭IO
 */
public class CloseUtils {
    /**
     * 关闭cursor
     *
     * @param cursors
     */
    public static void closeCursor(Cursor... cursors) {
        if (cursors == null) return;
        for (Cursor cursor : cursors) {
            if (cursor != null) {
                cursor.close();
                cursor = null;
            }
        }
    }

    /**
     * 关闭数据库
     *
     * @param dbs
     */
    public static void closeDb(SQLiteDatabase... dbs) {
        if (dbs == null) return;
        for (SQLiteDatabase db : dbs) {
            if (db != null) {
                db.close();
                db = null;
            }
        }
    }

    /**
     * 关闭IO
     *
     * @param closeables closeable
     */
    public static void closeIO(Closeable... closeables) {
        if (closeables == null) return;
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 安静关闭IO
     *
     * @param closeables closeable
     */
    public static void closeIOQuietly(Closeable... closeables) {
        if (closeables == null) return;
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException ignored) {
                }
            }
        }
    }
}
