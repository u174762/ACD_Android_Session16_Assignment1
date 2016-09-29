package psalms.batterypercentage;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {
    private int mProgressStatus = 0;

    private BroadcastReceiver br1=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int pct=intent.getIntExtra(BatteryManager.EXTRA_SCALE,-1);
            int level=intent.getIntExtra(BatteryManager.EXTRA_LEVEL,-1);


            float percentage = pct/ (float) level;

            mProgressStatus=(int)((percentage)*100);

            ProgressBar pb=(ProgressBar)findViewById(R.id.pb1);
            TextView txt=(TextView)findViewById(R.id.txt1);



            txt.setText("battery level Remaining" + mProgressStatus + "%");

            // Show the battery charged percentage in TextView



            pb.setProgress(mProgressStatus);


        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



       Context Context=getApplicationContext();

        IntentFilter intentFilter=new IntentFilter(Intent.ACTION_BATTERY_CHANGED);

        Context.registerReceiver(br1,intentFilter);

        TextView txt=(TextView)findViewById(R.id.txt1);

        ProgressBar pb=(ProgressBar)findViewById(R.id.pb1);


        }
}
