package hiber.model;

import javax.persistence.*;

@Entity
@Table(name = "cars", uniqueConstraints= @UniqueConstraint(columnNames={"model", "series"}) )
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "model")
    private String model;

    @Column(name = "series")
    private int series;

    public Car() {}

    public Car(String model, int series) {
        this.model = model;
        this.series = series;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getModel() { return model; }

    public void setModel(String model) { this.model = model; }

    public int getSeries() { return series; }

    public void setSeries(int series) { this.series = series; }
}
