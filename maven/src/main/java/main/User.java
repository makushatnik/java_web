package main;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "accounts")
@NamedQueries({
	@NamedQuery(name = "User.getAll", query = "select u from User u"),
	@NamedQuery(name = "User.findById", query = "select u from User u where u.id=:id")
})
public class User {
    
    private Long id;
    
    private String name;
 
    private String lastName;
 
    private int age;
 
    public User() {
    }
 
    public User(String name, String lastName, int age) {
 
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }
    @Id
    @GeneratedValue(strategy = IDENTITY)
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "first_name", nullable = false)
	public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }
 
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @Column(name = "age", nullable = false)
    public int getAge() {
        return age;
    }
 
    public void setAge(int age) {
        this.age = age;
    }
 
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", first_name='" + name + '\'' +
                ", last_name='" + lastName + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}