package yanat.destosampleapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class MyCallReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		
		// initialise the Telephony Manger object
		TelephonyManager telephonyManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		
		// listen for calls
		telephonyManager.listen(new MyPhoneStateListenner(context),
				PhoneStateListener.LISTEN_CALL_STATE);
	}

}
