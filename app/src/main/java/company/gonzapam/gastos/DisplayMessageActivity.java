package company.gonzapam.gastos;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;

import company.gonzapam.gastos.persistence.invoice.Invoice;
import company.gonzapam.gastos.persistence.invoice.InvoicesHelper;


public class DisplayMessageActivity extends ActionBarActivity {

    private InvoicesHelper myhelper;
    private String invoice_name;
    private String invoice_date;
    private Float invoice_amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        // Get the message from the intent
        Intent intent = getIntent();
        String name = intent.getStringExtra(HomeActivity.RUT);
        String amount = intent.getStringExtra(HomeActivity.AMOUNT);
        invoice_date = intent.getStringExtra(HomeActivity.DATE);

        TextView fecha_tx = (TextView) findViewById(R.id.fecha_value);
        fecha_tx.setText(invoice_date);

        EditText rut_tx = (EditText) findViewById(R.id.rut_value);
        rut_tx.setText(name);

        TextView mnt_tx = (TextView) findViewById(R.id.monto_value);
        mnt_tx.setText(amount);
        invoice_amount = Float.parseFloat(amount);

        Context context = getApplicationContext();
        CharSequence text = "Factura escaneada exitosamente!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
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

    private InvoicesHelper getHelper() {
        if (myhelper == null) {
            myhelper = OpenHelperManager.getHelper(this, InvoicesHelper.class);
        }
        return myhelper;
    }

    public void add_invoice(View view) {
        EditText name_value = (EditText) findViewById(R.id.rut_value);
        invoice_name = name_value.getText().toString();
        InvoicesHelper helper = getHelper();
        Dao dao = helper.getInvoiceDao();
        try {
            Invoice invoice = new Invoice(invoice_name, invoice_date, invoice_amount);
            dao.create(invoice);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void print_invoices(View view){
        InvoicesHelper helper = getHelper();
        Dao dao = helper.getInvoiceDao();
        try {
            List invoices = dao.queryForAll();
            if (invoices.isEmpty()){
                System.out.println("No se encontraron facturas");
            }else{
                Iterator it = invoices.iterator();
                while (it.hasNext()){
                    Invoice invoice = (Invoice) it.next();
                    System.out.print("Id : ");
                    System.out.println(invoice.getId());
                    System.out.print("Name : ");
                    System.out.println(invoice.getName());
                    System.out.print("Amount : ");
                    System.out.println(invoice.getAmount());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (myhelper != null) {
            OpenHelperManager.releaseHelper();
            myhelper = null;
        }
    }
}
