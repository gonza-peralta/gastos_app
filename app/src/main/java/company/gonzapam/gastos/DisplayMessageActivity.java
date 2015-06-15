package company.gonzapam.gastos;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;


public class DisplayMessageActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        // Get the message from the intent
        Intent intent = getIntent();
        String rut = intent.getStringExtra(HomeActivity.RUT);
        String amount = intent.getStringExtra(HomeActivity.AMOUNT);
        String date = intent.getStringExtra(HomeActivity.DATE);

        TextView fecha_tx = (TextView) findViewById(R.id.fecha_value);
        fecha_tx.setText(date);

        TextView rut_tx = (TextView) findViewById(R.id.rut_value);
        rut_tx.setText(rut);

        TextView mnt_tx = (TextView) findViewById(R.id.monto_value);
        mnt_tx.setText(amount);
    }

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
}
