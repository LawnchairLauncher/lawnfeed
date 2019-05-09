package amirz.aidlbridge;

import amirz.aidlbridge.BridgeCallback;

interface Bridge {
    oneway void connect(in BridgeCallback cb, in int flags);
}
