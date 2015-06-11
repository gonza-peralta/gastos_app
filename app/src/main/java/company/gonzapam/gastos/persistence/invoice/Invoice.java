package company.gonzapam.gastos.persistence.invoice;

import java.util.Date;

/**
 * Created by gonza on 11/06/15.
 */
import java.util.Formatter;

public class Invoice {
    private long id;
    private String name;
    private Date date;
    private Float amount;

    public long setId(long id) {
        return this.id = id;
    }

    public long getId() {
        return id;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        Formatter formatter = new Formatter();
        Formatter format = formatter.format("<%d, %s, %s, %s>", id, name, date, amount);
        return format.toString();
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public Float getAmount() {
        return amount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }
}