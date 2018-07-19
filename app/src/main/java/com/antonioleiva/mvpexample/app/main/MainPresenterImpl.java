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

package com.antonioleiva.mvpexample.app.main;

import java.util.List;

public class MainPresenterImpl implements MainPresenter, FindItemsInteractor.OnFinishedListener {

    private MainView mainView; //持有view对象
    private FindItemsInteractor findItemsInteractor;  //持有model对象

    /**
     * 在构造方法中将view和presenter作为参数传递进来
     * @param mainView  view对象
     * @param findItemsInteractor  model对象
     */
    public MainPresenterImpl(MainView mainView, FindItemsInteractor findItemsInteractor) {
        this.mainView = mainView;
        this.findItemsInteractor = findItemsInteractor;
    }

    @Override public void onResume() {
        if (mainView != null) {
            mainView.showProgress();
        }
        //对model中的
        findItemsInteractor.findItems(this);
    }

    /**
     * 代理对view的点击事件的响应
     * @param position
     */
    @Override public void onItemClicked(int position) {
        if (mainView != null) {
            mainView.showMessage(String.format("Position %d clicked", position + 1));
        }
    }

    @Override public void onDestroy() {
        mainView = null;
    }

    @Override public void onFinished(List<String> items) {
        if (mainView != null) {
            mainView.setItems(items);  //将nodel返回的数据在View中进行了设置
            mainView.hideProgress();
        }
    }

    public MainView getMainView() {
        return mainView;
    }
}
