package yanat.destosampleapp;

import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class MyPhoneStateListenner extends PhoneStateListener {

	// used to store the context from the reciever
	Context context;

	public MyPhoneStateListenner(Context context) {
		this.context = context;
	}
	
	@Override
	public void onCallStateChanged(int state, String incomingNumber) {
		
		switch (state)
		{
		case TelephonyManager.CALL_STATE_IDLE:
			Intent intent = new Intent(context, StoreActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.putExtra("incomingNumber", incomingNumber);
			context.startActivity(intent);
			break;
		}
	}
}
