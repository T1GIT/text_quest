package app.text_quest.database.model;

import app.text_quest.database.model.user.User;
import app.text_quest.database.util.AuditModel;
import app.text_quest.database.util.enums.Color;
import app.text_quest.database.util.enums.Font;
import app.text_quest.database.util.enums.MsgType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 <h2> A class {@link Setting}

 <p> It's object-oriented representation for table <u>settings</u>

 <p> <b> Storages: </b>
 <p> User's settings
 */
@Entity
@Table(name = "settings")
public class Setting extends AuditModel {

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private MsgType msgType = MsgType.SQUARE;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Color color = Color.BLUE;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Font font = Font.ARIAL;

    @Column(nullable = false)
    private int size = 20;

    @JsonIgnore
    @MapsId
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;

    public MsgType getMsgType() {
        return msgType;
    }

    public Color getColor() {
        return color;
    }

    public Font getFont() {
        return font;
    }

    public int getSize() {
        return size;
    }

    public User getUser() {
        return user;
    }

    public void setMsgType(MsgType msgType) {
        this.msgType = msgType;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
