package com.bupt.mountwutai.util;

import android.support.annotation.NonNull;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by joycezhao on 17/1/25.
 */

public class PermissionHelper implements EasyPermissions.PermissionCallbacks {

    private static final int RC_CALL_PERM = 121;
    private static final int RC_WRITE_EXTERNAL_PERM = 122;
    private static final int RC_CAMERA_PERM = 123;
    private static final int RC_SETTINGS = 124;
    private static PermissionHelper instance;

    public static PermissionHelper getHelper() {
        if (instance == null)
            instance = new PermissionHelper();
        return instance;
    }

    public void requestWriteExternalPerm() {
//EasyPermissions.requestPermissions();
    }

    public void requestCallPerm() {

    }

    public void requestCameraPerm() {

    }


    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

    }
}
