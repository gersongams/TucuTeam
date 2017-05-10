package pe.ggarridomuni.tucuteam.models;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Usuarios {

    public String username;
    public String email;

    public Usuarios(){}

    public Usuarios(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

}
