package pl.bpacocha.rest.storage;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "DATA_MODEL")
public class DataModel {

    @Column(name = "PRIMARY_KEY")
    @Id
    private String PRIMARY_KEY;

    @Column(name = "NAME")
    private String NAME;

    @Column(name = "DESCRIPTION")
    private String DESCRIPTION;

    @Column(name = "TIMESTAMP_DATE")
    private Timestamp TIMESTAMP;

    public DataModel() {

    }

    public DataModel(String PRIMARY_KEY, String NAME, String DESCRIPTION, Timestamp TIMESTAMP) {
        this.PRIMARY_KEY = PRIMARY_KEY;
        this.NAME = NAME;
        this.DESCRIPTION = DESCRIPTION;
        this.TIMESTAMP = TIMESTAMP;
    }

    public DataModel(String PRIMARY_KEY, String NAME, String DESCRIPTION, String timestamp) {
        this.PRIMARY_KEY = PRIMARY_KEY;
        this.NAME = NAME;
        this.DESCRIPTION = DESCRIPTION;

        this.TIMESTAMP = Utils.ConvertStringToTimestamp(timestamp, null);

    }

    public DataModel(String[] lines) {

        this(lines[0], lines[1], lines[2], lines[3]);

    }


    public void setPRIMARY_KEY(String PRIMARY_KEY) {
        this.PRIMARY_KEY = PRIMARY_KEY;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getPRIMARY_KEY() {
        return PRIMARY_KEY;
    }

    public String getNAME() {
        return NAME;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public Timestamp getTIMESTAMP() {
        return TIMESTAMP;
    }

    public void setTIMESTAMP(Timestamp TIMESTAMP) {
        this.TIMESTAMP = TIMESTAMP;
    }

    @Override
    public String toString() {
        return "DataModel{" +
                "PRIMARY_KEY='" + PRIMARY_KEY + '\'' +
                ", NAME='" + NAME + '\'' +
                ", DESCRIPTION='" + DESCRIPTION + '\'' +
                ", TIMESTAMP=" + TIMESTAMP +
                '}';
    }
}
