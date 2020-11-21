package com.cz.launcher.overlay.sample.search.service;

import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.view.WindowManager;

import com.cz.launcher.overlay.library.ILauncherFixedOverlay;
import com.cz.launcher.overlay.library.ILauncherFixedOverlayCallback;
import com.cz.launcher.overlay.sample.fixed.service.LauncherOverlayCallback;
import com.cz.launcher.overlay.sample.fixed.service.LauncherOverlayComponent;

public class LauncherOverlaySearchBinder extends ILauncherFixedOverlay.Stub{
    private final Handler dispatcher;
    final String clientPackage;

    public LauncherOverlaySearchBinder(LauncherSearchOverlayComponent overlayComponent, String clientPackage) {
        this.clientPackage = clientPackage;
        this.dispatcher  = new Handler(Looper.getMainLooper(),new LauncherSearchOverlayCallback(overlayComponent));
    }
    @Override
    public IBinder asBinder() {
        return this;
    }

    @Override
    public void onPause() {
        dispatchMessage(Message.obtain(dispatcher,LauncherSearchOverlayCallback.MSG_ON_PAUSE));
    }

    @Override
    public void onStop() {
        dispatchMessage(Message.obtain(dispatcher,LauncherSearchOverlayCallback.MSG_CLOSE_OVERLAY));
    }

    @Override
    public void onResume() {
        dispatchMessage(Message.obtain(dispatcher,LauncherSearchOverlayCallback.MSG_ON_RESUME));
    }

    public void onDestroy(){
        dispatchMessage(Message.obtain(dispatcher,LauncherSearchOverlayCallback.MSG_ON_DESTROY));
    }

    @Override
    public void openOverlay(int options) {
        dispatchMessage(Message.obtain(dispatcher,LauncherSearchOverlayCallback.MSG_OPEN_OVERLAY));
    }

    @Override
    public void closeOverlay(int options) {
        dispatchMessage(Message.obtain(dispatcher,LauncherSearchOverlayCallback.MSG_CLOSE_OVERLAY));
    }

    @Override
    public void onStart() {
        dispatchMessage(Message.obtain(dispatcher,LauncherSearchOverlayCallback.MSG_CLOSE_OVERLAY));
    }

    @Override
    public void onWindowAttached(WindowManager.LayoutParams attrs, ILauncherFixedOverlayCallback callbacks, int options) throws RemoteException {
        Message newMessage = Message.obtain(dispatcher, LauncherOverlayCallback.MSG_ATTACH_TO_WINDOW);
        Bundle data=new Bundle();
        data.putParcelable("attrs", attrs);
        newMessage.setData(data);
        newMessage.obj = callbacks;
        dispatchMessage(newMessage);
    }

    @Override
    public void onWindowDetached(boolean isChangingConfigurations) {
        dispatchMessage(Message.obtain(dispatcher, LauncherOverlayCallback.MSG_DETACH_FROM_WINDOW));
    }

    private void dispatchMessage(Message message){
        dispatcher.sendMessage(message);
    }
}
