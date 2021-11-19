package com.example.permissions.bean;

import java.util.List;

/**
 * Created by zouxipu on 2021/4/15.
 * zouxipu@haohaozhu.com
 * Traveling Light
 */
public class Reject {
    private int requestCode;
    private List<String> rejectList;
//    private Context context;

    public int getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    public List<String> getRejectList() {
        return rejectList;
    }

    public void setRejectList(List<String> rejectList) {
        this.rejectList = rejectList;
    }

//    public Context getContext() {
//        return context;
//    }

//    public void setContext(Context context) {
//        this.context = context;
//    }
}
