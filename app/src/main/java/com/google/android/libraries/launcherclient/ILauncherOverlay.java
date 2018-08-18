/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: C:\\Users\\Amir\\AndroidStudioProjects\\AIDLBridge\\app\\src\\main\\aidl\\com\\google\\android\\libraries\\launcherclient\\ILauncherOverlay.aidl
 */
package com.google.android.libraries.launcherclient;

import android.os.IBinder;

public interface ILauncherOverlay extends android.os.IInterface {
    /**
     * Local-side IPC implementation stub class.
     */
    public static abstract class Stub extends android.os.Binder implements ILauncherOverlay {
        private static final java.lang.String DESCRIPTOR = "com.google.android.libraries.launcherclient.ILauncherOverlay";

        /**
         * Construct the stub at attach it to the interface.
         */
        public Stub() {
            this.attachInterface(this, DESCRIPTOR);
        }

        /**
         * Cast an IBinder object into an com.google.android.libraries.launcherclient.ILauncherOverlay interface,
         * generating a proxy if needed.
         */
        public static ILauncherOverlay asInterface(android.os.IBinder obj) {
            if ((obj == null)) {
                return null;
            }
            android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (((iin != null) && (iin instanceof ILauncherOverlay))) {
                return ((ILauncherOverlay) iin);
            }
            return new ILauncherOverlay.Stub.Proxy(obj);
        }

        @Override
        public android.os.IBinder asBinder() {
            return this;
        }

        @Override
        public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException {
            switch (code) {
                case INTERFACE_TRANSACTION: {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                case TRANSACTION_startScroll: {
                    data.enforceInterface(DESCRIPTOR);
                    this.startScroll();
                    return true;
                }
                case TRANSACTION_onScroll: {
                    data.enforceInterface(DESCRIPTOR);
                    float _arg0;
                    _arg0 = data.readFloat();
                    this.onScroll(_arg0);
                    return true;
                }
                case TRANSACTION_endScroll: {
                    data.enforceInterface(DESCRIPTOR);
                    this.endScroll();
                    return true;
                }
                case TRANSACTION_windowAttached: {
                    data.enforceInterface(DESCRIPTOR);
                    android.view.WindowManager.LayoutParams _arg0;
                    if ((0 != data.readInt())) {
                        _arg0 = android.view.WindowManager.LayoutParams.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    IBinder _arg1;
                    _arg1 = data.readStrongBinder();
                    int _arg2;
                    _arg2 = data.readInt();
                    this.windowAttached(_arg0, _arg1, _arg2);
                    return true;
                }
                case TRANSACTION_windowDetached: {
                    data.enforceInterface(DESCRIPTOR);
                    boolean _arg0;
                    _arg0 = (0 != data.readInt());
                    this.windowDetached(_arg0);
                    return true;
                }
                case TRANSACTION_closeOverlay: {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0;
                    _arg0 = data.readInt();
                    this.closeOverlay(_arg0);
                    return true;
                }
                case TRANSACTION_onPause: {
                    data.enforceInterface(DESCRIPTOR);
                    this.onPause();
                    return true;
                }
                case TRANSACTION_onResume: {
                    data.enforceInterface(DESCRIPTOR);
                    this.onResume();
                    return true;
                }
                case TRANSACTION_openOverlay: {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0;
                    _arg0 = data.readInt();
                    this.openOverlay(_arg0);
                    return true;
                }
                case TRANSACTION_requestVoiceDetection: {
                    data.enforceInterface(DESCRIPTOR);
                    boolean _arg0;
                    _arg0 = (0 != data.readInt());
                    this.requestVoiceDetection(_arg0);
                    return true;
                }
                case TRANSACTION_getVoiceSearchLanguage: {
                    data.enforceInterface(DESCRIPTOR);
                    java.lang.String _result = this.getVoiceSearchLanguage();
                    reply.writeNoException();
                    reply.writeString(_result);
                    return true;
                }
                case TRANSACTION_isVoiceDetectionRunning: {
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result = this.isVoiceDetectionRunning();
                    reply.writeNoException();
                    reply.writeInt(((_result) ? (1) : (0)));
                    return true;
                }
                case TRANSACTION_hasOverlayContent: {
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result = this.hasOverlayContent();
                    reply.writeNoException();
                    reply.writeInt(((_result) ? (1) : (0)));
                    return true;
                }
                case TRANSACTION_windowAttached2: {
                    data.enforceInterface(DESCRIPTOR);
                    android.os.Bundle _arg0;
                    if ((0 != data.readInt())) {
                        _arg0 = android.os.Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    IBinder _arg1;
                    _arg1 = data.readStrongBinder();
                    this.windowAttached2(_arg0, _arg1);
                    return true;
                }
                case TRANSACTION_unusedMethod: {
                    data.enforceInterface(DESCRIPTOR);
                    this.unusedMethod();
                    return true;
                }
                case TRANSACTION_setActivityState: {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0;
                    _arg0 = data.readInt();
                    this.setActivityState(_arg0);
                    return true;
                }
                case TRANSACTION_startSearch: {
                    data.enforceInterface(DESCRIPTOR);
                    byte[] _arg0;
                    _arg0 = data.createByteArray();
                    android.os.Bundle _arg1;
                    if ((0 != data.readInt())) {
                        _arg1 = android.os.Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg1 = null;
                    }
                    boolean _result = this.startSearch(_arg0, _arg1);
                    reply.writeNoException();
                    reply.writeInt(((_result) ? (1) : (0)));
                    return true;
                }
            }
            return super.onTransact(code, data, reply, flags);
        }

        public static class Proxy implements ILauncherOverlay {
            private android.os.IBinder mRemote;

            Proxy(android.os.IBinder remote) {
                mRemote = remote;
            }

            @Override
            public android.os.IBinder asBinder() {
                return mRemote;
            }

            public java.lang.String getInterfaceDescriptor() {
                return DESCRIPTOR;
            }

            @Override
            public void startScroll() throws android.os.RemoteException {
                android.os.Parcel _data = android.os.Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    mRemote.transact(Stub.TRANSACTION_startScroll, _data, null, android.os.IBinder.FLAG_ONEWAY);
                } finally {
                    _data.recycle();
                }
            }

            @Override
            public void onScroll(float progress) throws android.os.RemoteException {
                android.os.Parcel _data = android.os.Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeFloat(progress);
                    mRemote.transact(Stub.TRANSACTION_onScroll, _data, null, android.os.IBinder.FLAG_ONEWAY);
                } finally {
                    _data.recycle();
                }
            }

            @Override
            public void endScroll() throws android.os.RemoteException {
                android.os.Parcel _data = android.os.Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    mRemote.transact(Stub.TRANSACTION_endScroll, _data, null, android.os.IBinder.FLAG_ONEWAY);
                } finally {
                    _data.recycle();
                }
            }

            @Override
            public void windowAttached(android.view.WindowManager.LayoutParams lp, IBinder cb, int flags) throws android.os.RemoteException {
                android.os.Parcel _data = android.os.Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    if ((lp != null)) {
                        _data.writeInt(1);
                        lp.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(cb);
                    _data.writeInt(flags);
                    mRemote.transact(Stub.TRANSACTION_windowAttached, _data, null, android.os.IBinder.FLAG_ONEWAY);
                } finally {
                    _data.recycle();
                }
            }

            @Override
            public void windowDetached(boolean isChangingConfigurations) throws android.os.RemoteException {
                android.os.Parcel _data = android.os.Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(((isChangingConfigurations) ? (1) : (0)));
                    mRemote.transact(Stub.TRANSACTION_windowDetached, _data, null, android.os.IBinder.FLAG_ONEWAY);
                } finally {
                    _data.recycle();
                }
            }

            @Override
            public void closeOverlay(int flags) throws android.os.RemoteException {
                android.os.Parcel _data = android.os.Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(flags);
                    mRemote.transact(Stub.TRANSACTION_closeOverlay, _data, null, android.os.IBinder.FLAG_ONEWAY);
                } finally {
                    _data.recycle();
                }
            }

            @Override
            public void onPause() throws android.os.RemoteException {
                android.os.Parcel _data = android.os.Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    mRemote.transact(Stub.TRANSACTION_onPause, _data, null, android.os.IBinder.FLAG_ONEWAY);
                } finally {
                    _data.recycle();
                }
            }

            @Override
            public void onResume() throws android.os.RemoteException {
                android.os.Parcel _data = android.os.Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    mRemote.transact(Stub.TRANSACTION_onResume, _data, null, android.os.IBinder.FLAG_ONEWAY);
                } finally {
                    _data.recycle();
                }
            }

            @Override
            public void openOverlay(int flags) throws android.os.RemoteException {
                android.os.Parcel _data = android.os.Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(flags);
                    mRemote.transact(Stub.TRANSACTION_openOverlay, _data, null, android.os.IBinder.FLAG_ONEWAY);
                } finally {
                    _data.recycle();
                }
            }

            @Override
            public void requestVoiceDetection(boolean start) throws android.os.RemoteException {
                android.os.Parcel _data = android.os.Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(((start) ? (1) : (0)));
                    mRemote.transact(Stub.TRANSACTION_requestVoiceDetection, _data, null, android.os.IBinder.FLAG_ONEWAY);
                } finally {
                    _data.recycle();
                }
            }

            @Override
            public java.lang.String getVoiceSearchLanguage() throws android.os.RemoteException {
                android.os.Parcel _data = android.os.Parcel.obtain();
                android.os.Parcel _reply = android.os.Parcel.obtain();
                java.lang.String _result;
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    mRemote.transact(Stub.TRANSACTION_getVoiceSearchLanguage, _data, _reply, 0);
                    _reply.readException();
                    _result = _reply.readString();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
                return _result;
            }

            @Override
            public boolean isVoiceDetectionRunning() throws android.os.RemoteException {
                android.os.Parcel _data = android.os.Parcel.obtain();
                android.os.Parcel _reply = android.os.Parcel.obtain();
                boolean _result;
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    mRemote.transact(Stub.TRANSACTION_isVoiceDetectionRunning, _data, _reply, 0);
                    _reply.readException();
                    _result = (0 != _reply.readInt());
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
                return _result;
            }

            @Override
            public boolean hasOverlayContent() throws android.os.RemoteException {
                android.os.Parcel _data = android.os.Parcel.obtain();
                android.os.Parcel _reply = android.os.Parcel.obtain();
                boolean _result;
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    mRemote.transact(Stub.TRANSACTION_hasOverlayContent, _data, _reply, 0);
                    _reply.readException();
                    _result = (0 != _reply.readInt());
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
                return _result;
            }

            @Override
            public void windowAttached2(android.os.Bundle bundle, IBinder cb) throws android.os.RemoteException {
                android.os.Parcel _data = android.os.Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    if ((bundle != null)) {
                        _data.writeInt(1);
                        bundle.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(cb);
                    mRemote.transact(Stub.TRANSACTION_windowAttached2, _data, null, android.os.IBinder.FLAG_ONEWAY);
                } finally {
                    _data.recycle();
                }
            }

            @Override
            public void unusedMethod() throws android.os.RemoteException {
                android.os.Parcel _data = android.os.Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    mRemote.transact(Stub.TRANSACTION_unusedMethod, _data, null, android.os.IBinder.FLAG_ONEWAY);
                } finally {
                    _data.recycle();
                }
            }

            @Override
            public void setActivityState(int flags) throws android.os.RemoteException {
                android.os.Parcel _data = android.os.Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(flags);
                    mRemote.transact(Stub.TRANSACTION_setActivityState, _data, null, android.os.IBinder.FLAG_ONEWAY);
                } finally {
                    _data.recycle();
                }
            }

            @Override
            public boolean startSearch(byte[] data, android.os.Bundle bundle) throws android.os.RemoteException {
                android.os.Parcel _data = android.os.Parcel.obtain();
                android.os.Parcel _reply = android.os.Parcel.obtain();
                boolean _result;
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeByteArray(data);
                    if ((bundle != null)) {
                        _data.writeInt(1);
                        bundle.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    mRemote.transact(Stub.TRANSACTION_startSearch, _data, _reply, 0);
                    _reply.readException();
                    _result = (0 != _reply.readInt());
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
                return _result;
            }
        }

        static final int TRANSACTION_startScroll = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
        static final int TRANSACTION_onScroll = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
        static final int TRANSACTION_endScroll = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
        static final int TRANSACTION_windowAttached = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
        static final int TRANSACTION_windowDetached = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
        static final int TRANSACTION_closeOverlay = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
        static final int TRANSACTION_onPause = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
        static final int TRANSACTION_onResume = (android.os.IBinder.FIRST_CALL_TRANSACTION + 7);
        static final int TRANSACTION_openOverlay = (android.os.IBinder.FIRST_CALL_TRANSACTION + 8);
        static final int TRANSACTION_requestVoiceDetection = (android.os.IBinder.FIRST_CALL_TRANSACTION + 9);
        static final int TRANSACTION_getVoiceSearchLanguage = (android.os.IBinder.FIRST_CALL_TRANSACTION + 10);
        static final int TRANSACTION_isVoiceDetectionRunning = (android.os.IBinder.FIRST_CALL_TRANSACTION + 11);
        static final int TRANSACTION_hasOverlayContent = (android.os.IBinder.FIRST_CALL_TRANSACTION + 12);
        static final int TRANSACTION_windowAttached2 = (android.os.IBinder.FIRST_CALL_TRANSACTION + 13);
        static final int TRANSACTION_unusedMethod = (android.os.IBinder.FIRST_CALL_TRANSACTION + 14);
        static final int TRANSACTION_setActivityState = (android.os.IBinder.FIRST_CALL_TRANSACTION + 15);
        static final int TRANSACTION_startSearch = (android.os.IBinder.FIRST_CALL_TRANSACTION + 16);
    }

    public void startScroll() throws android.os.RemoteException;

    public void onScroll(float progress) throws android.os.RemoteException;

    public void endScroll() throws android.os.RemoteException;

    public void windowAttached(android.view.WindowManager.LayoutParams lp, IBinder cb, int flags) throws android.os.RemoteException;

    public void windowDetached(boolean isChangingConfigurations) throws android.os.RemoteException;

    public void closeOverlay(int flags) throws android.os.RemoteException;

    public void onPause() throws android.os.RemoteException;

    public void onResume() throws android.os.RemoteException;

    public void openOverlay(int flags) throws android.os.RemoteException;

    public void requestVoiceDetection(boolean start) throws android.os.RemoteException;

    public java.lang.String getVoiceSearchLanguage() throws android.os.RemoteException;

    public boolean isVoiceDetectionRunning() throws android.os.RemoteException;

    public boolean hasOverlayContent() throws android.os.RemoteException;

    public void windowAttached2(android.os.Bundle bundle, IBinder cb) throws android.os.RemoteException;

    public void unusedMethod() throws android.os.RemoteException;

    public void setActivityState(int flags) throws android.os.RemoteException;

    public boolean startSearch(byte[] data, android.os.Bundle bundle) throws android.os.RemoteException;
}
