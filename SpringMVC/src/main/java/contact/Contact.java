package contact;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Size;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Ageev Evgeny on 25.01.2016.
 */
@Entity
@Table(name = "contact")
public class Contact implements Serializable {
    private Long id;
    private int version;
    private String firstName;
    private String lastName;
    private DateTime birthDate;
    private String description;
    private byte[] photo;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Version
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
    @NotEmpty(message = "{validation.firstname.NotEmpty.message}")
    @Size(min = 3, max = 30, message = "{validation.firstname.Size.message}")
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @NotEmpty(message = "{validation.lastname.NotEmpty.message}")
    @Size(min = 1, max = 30, message = "{validation.lastname.Size.message}")
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @Column(name = "birth_date")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    public DateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(DateTime birthDate) {
        this.birthDate = birthDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Basic(fetch = FetchType.LAZY)
    @Lob
    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
    @Transient
    public String getBirthDateString() {
        String tmp = "";
        if (birthDate != null)
            tmp = org.joda.time.format.DateTimeFormat.forPattern("yyyy-MM-dd").print(birthDate);
        return tmp;
    }
    @Override
    public String toString() {
        return "Contact - Id: " + id + ", First name: " + firstName + ", Last name: " + lastName +
                ", Birthday: " + birthDate + ", Description: " + description;
    }
}
