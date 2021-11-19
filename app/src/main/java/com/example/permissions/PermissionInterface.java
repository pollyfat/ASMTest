package com.example.permissions;

import java.util.List;

/**
 * Created by zouxipu on 2021/3/16.
 * zouxipu@haohaozhu.com
 * Traveling Light
 */
public interface PermissionInterface {
    //同意
    void PermissionGranted();

    //取消
    void PermissionCanceled(int requestCode);

    //拒绝
    void PermissionReject(int requestCode, List<String> strings);
}
