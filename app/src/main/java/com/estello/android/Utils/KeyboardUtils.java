package com.estello.android.Utils;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;



 public class KeyboardUtils {

     Context context;

    public KeyboardUtils(Context context){

        this.context = context;
    }

    public void showKeyboard() {
        InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
    }

}
