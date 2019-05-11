package amirz.aidlbridge;

interface BridgeCallback {
    oneway void onServiceConnected(in ComponentName name, in IBinder service);

    oneway void onServiceDisconnected(in ComponentName name);
}
