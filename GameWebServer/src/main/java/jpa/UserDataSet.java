package jpa;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Ageev Evgeny on 24.01.2016.
 */
@Entity
@Table(name = "users")
public class UserDataSet implements Serializable {
    private static final long serialVersionUID = -8706689714326132798L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    public UserDataSet() {}

    public UserDataSet(String name) {
        this.id = -1;
        this.name = name;
    }

    public UserDataSet(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "UserDataSet(" +
                "id=" + id +
                ", name='" + name + "')";
    }
}
