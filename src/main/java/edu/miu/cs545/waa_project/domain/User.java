package edu.miu.cs545.waa_project.domain;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="USER")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="user_type", discriminatorType = DiscriminatorType.STRING)
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="FIRST_NAME")
    @NotEmpty(message = "{User.FirstName.validation}")
    private String firstName;


    @Column(name="LAST_NAME")
    @NotEmpty(message = "{User.LastName.validation}")
    private String lastName;

    @Column(unique = true)
    @Email(message = "{User.Email.validation}")
    @NotBlank(message = "{User.Email.validation}")
    private String email;

    @NotBlank(message = "{User.Pass.validation}")
    @Size(min = 4, message = "{User.PassSize.validation}")
    private String password;

    public User(){}
    public User(String firstName, String lastName, String email, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
