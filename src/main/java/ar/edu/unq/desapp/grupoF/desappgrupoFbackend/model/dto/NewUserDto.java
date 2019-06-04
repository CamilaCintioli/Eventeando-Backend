package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.dto;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
//import java.time.LocalDateTime;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.User;

public class NewUserDto {

    private String name;

    private String lastName;

    @Column(unique = true)
    @Email(message = "Email should be valid")
    private String email;

    /*
    @Size(min = 4, max = 10,
            message = " password must be between 4 and 10 characters")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[A-Za-z])[A-Za-z0-9]{1,10}$")
    */
    private String password;

    @Column(unique = true)
    private String username;

    //private LocalDateTime birthDate;

    public NewUserDto() {}

    public NewUserDto(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.name = user.getName();
        this.lastName = user.getLastName();
        this.username = user.getUsername();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String toString() {
        return "email " + this.email + " | name " + this.name + " | lastname " + this.lastName + " | username " + this.username + " | password " + this.password;
    }

    /*
    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }
    */

}
