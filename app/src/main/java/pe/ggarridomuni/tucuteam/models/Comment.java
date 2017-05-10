package pe.ggarridomuni.tucuteam.models;

import com.google.firebase.database.IgnoreExtraProperties;


@IgnoreExtraProperties
public class Comment {

    public String uid;
    public String author;
    public String text;

    public Comment() {

    }


    public Comment(String uid, String author, String text) {
        this.uid = uid;
        this.author = author;
        this.text = text;
    }

    public String getUid() {
        return uid;
    }

    public String getAuthor() {
        return author;
    }

    public String getText(){
        return text;
    }

}
