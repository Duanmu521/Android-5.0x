package com.strivestay.viewdemo;

import android.util.Log;

/**
 * @author StriveStay
 * @date 2017/12/8
 */
public class L {
    private static final boolean debug = true;

    public static void e(String msg){
        if(debug){
            Log.e("RecyclerviewDemo",msg);
        }
    }
}
