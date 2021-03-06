package company.gonzapam.gastos;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.System.*;


public class HomeActivity extends ActionBarActivity {

    public final static String RUT = "company.gonzapam.gastos.RUT";
    public final static String DATE = "company.gonzapam.gastos.DATE";
    public final static String AMOUNT = "company.gonzapam.gastos.AMOUNT";
    static final int PICK_QR_REQUEST = 0;
    private SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_home, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /** Called when the user clicks the Escanear button */
    public void scannig_view(View view) {
        try {
            Intent qrDroid = new Intent("la.droid.qr.scan");
            startActivityForResult(qrDroid, PICK_QR_REQUEST);
        } catch (Exception e) {
            Uri marketUri = Uri.parse("http://market.android.com/details?id=la.droid.qr");
            Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
            startActivity(marketIntent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_QR_REQUEST) {
            if (resultCode == RESULT_OK) {
                String contents = data.getExtras().getString("la.droid.qr.result");
                out.println(contents);

                String[] separated = contents.split(",");
                String rut = separated[0].split("\\?")[1];
                String amount = separated[4];

                out.println(separated[5]);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy/MM/dd");
                Date date = new Date();
                try {
                    date= dateFormat.parse(separated[5]);
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                String fecha = dateFormat2.format(date);

                TextView fecha_tx = (TextView) findViewById(R.id.fecha_value);
                fecha_tx.setText(fecha);

                TextView rut_tx = (TextView) findViewById(R.id.rut_value);
                rut_tx.setText(rut);

                TextView mnt_tx = (TextView) findViewById(R.id.monto_value);
                mnt_tx.setText(amount);

                // Start new activity
                Intent intent = new Intent(this, DisplayMessageActivity.class);
                intent.putExtra(RUT, rut);
                intent.putExtra(AMOUNT, amount);
                intent.putExtra(DATE, fecha);
                startActivity(intent);
            }
            if(resultCode == RESULT_CANCELED){
                out.println("Cancelo la lectura");
            }
        }
    }
}
