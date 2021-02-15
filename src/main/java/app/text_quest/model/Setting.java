package app.text_quest.model;

import app.text_quest.util.AbstractModel;
import app.text_quest.util.settings.Color;
import app.text_quest.util.settings.Font;
import app.text_quest.util.settings.MsgType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
@Entity
@Table(name = "settings")
public class Setting extends AbstractModel {
    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private MsgType msgType;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Color color;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Font font;

    @Column(nullable = false)
    private int size;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @MapsId
    private User user;

    public Setting() { }

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
