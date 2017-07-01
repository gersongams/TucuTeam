package pe.ggarridomuni.tucuteam.models;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Usuarios {

    public String username;
    public String email;
    public String description;

    public Usuarios(String username, String email, String description) {
        this.username = username;
        this.email = email;
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getDescription() { return description;}

}
