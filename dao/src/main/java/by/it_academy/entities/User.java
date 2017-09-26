package by.it_academy.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Setter
@Getter
public class User {
    private long id;
    private String name;
    private String login;
    private String password;
    private boolean active;
    private String address;
    private String email;
    private String type;


    public User(String name, String login, String password, String address, String email) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.active = true;
        this.address = address;
        this.email = email;
        this.type = "USER";
    }

    public User(long id, String name, String login, String password,
                boolean active, String address, String email, String type) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.active = active;
        this.address = address;
        this.email = email;
        this.type = type;
    }

}
