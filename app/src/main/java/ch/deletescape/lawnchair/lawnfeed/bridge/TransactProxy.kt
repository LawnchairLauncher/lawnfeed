package ch.deletescape.lawnchair.lawnfeed.bridge

import android.content.Context
import android.os.Binder
import android.os.IBinder
import android.os.Parcel

class TransactProxy(private val target: IBinder, private val context: Context) : Binder() {

    private val allowed by lazy { callingPackage in allowedPackages }
    private val callingPackage get() = context.packageManager.getNameForUid(getCallingUid())

    override fun onTransact(code: Int, data: Parcel, reply: Parcel?, flags: Int): Boolean {
        enforcePermission()
        return target.transact(code, data, reply, flags)
    }

    private fun enforcePermission() {
        if (!allowed)
            throw SecurityException("$callingPackage is not allowed to call this service")
    }

    companion object {

        @JvmStatic
        val allowedPackages = setOf("ch.deletescape.lawnchair.plah", "ch.deletescape.lawnchair")
    }
}
