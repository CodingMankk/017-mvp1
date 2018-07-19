package com.antonioleiva.mvpexample.app.Login;

import android.os.Handler;
import android.text.TextUtils;

public class LoginInteractorImpl implements LoginInteractor {

    @Override
    public void login(final String username, final String password, final OnLoginFinishedListener listener) {
        // Mock login. I'm creating a handler to delay the answer a couple of seconds
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                if (TextUtils.isEmpty(username)) {
                    listener.onUsernameError();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    listener.onPasswordError();
                    return;
                }
                listener.onSuccess();  //最终的数据处理这里做了临时的处理，调用listener的方法；实际项目会对做网络请求
            }
        }, 2000);
    }
}
