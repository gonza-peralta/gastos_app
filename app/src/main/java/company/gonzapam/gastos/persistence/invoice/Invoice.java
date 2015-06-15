package company.gonzapam.gastos.persistence.invoice;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by gonza on 11/06/15.
 */

@DatabaseTable(tableName="invoices")
public class Invoice {

    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String DATE = "date";
    public static final String AMOUNT = "amount";
    private final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ");

    @DatabaseField(generatedId = true, columnName = ID)
    private long id;
    @DatabaseField(columnName = NAME)
    private String name;
    @DatabaseField(columnName = DATE)
    private Date date;
    @DatabaseField(columnName = AMOUNT)
    private Float amount;

    public Invoice() {
        // ORMLite needs a no-arg constructor
    }
    public Invoice(long id, String name, String date, Float amount) throws ParseException {
        this.id = id;
        this.name = name;
        this.date = df.parse(date);
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(String date) throws ParseException {
        this.date = df.parse(date);
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }
}