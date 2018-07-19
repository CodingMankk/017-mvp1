/*
 *
 *  * Copyright (C) 2014 Antonio Leiva Gordillo.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.antonioleiva.mvpexample.app.Login;

public class LoginPresenterImpl implements LoginPresenter, LoginInteractor.OnLoginFinishedListener {

    private LoginView loginView; //持有view的对象
    private LoginInteractor loginInteractor;  //持有model对象

    public LoginPresenterImpl(LoginView loginView, LoginInteractor loginInteractor) {
        this.loginView = loginView; //在构造方法中将view传递进来
        this.loginInteractor = loginInteractor;  //在构造方法中将model传递进来
    }

    /**
     * 在执行登录验证的动作：
     *  【1】触发view的动作
     *  【2】将参数传递给model
     * @param username
     * @param password
     */
    @Override public void validateCredentials(String username, String password) {
        if (loginView != null) {
            loginView.showProgress();
        }

        loginInteractor.login(username, password, this);
    }

    @Override public void onDestroy() {
        loginView = null;
    }

    @Override public void onUsernameError() {
        if (loginView != null) {
            loginView.setUsernameError();
            loginView.hideProgress();
        }
    }

    @Override public void onPasswordError() {
        if (loginView != null) {
            loginView.setPasswordError();
            loginView.hideProgress();
        }
    }

    @Override public void onSuccess() {
        if (loginView != null) {
            loginView.navigateToHome();
        }
    }
}
