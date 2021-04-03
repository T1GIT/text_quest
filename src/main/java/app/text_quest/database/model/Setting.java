package app.text_quest.database.model;

import app.text_quest.database.model.user.User;
import app.text_quest.database.util.AuditModel;
import app.text_quest.database.util.enums.Color;
import app.text_quest.database.util.enums.Font;
import app.text_quest.database.util.enums.MsgType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * It's object-oriented representation for table <u>settings</u>
 *
 * <b>Storages:</b>
 * User's settings
 */
@Entity
@Table(name = "settings")
public class Setting extends AuditModel {

    /**
     * Type of the message view
     * <p>
     * <b>Constraints:</b>
     * <ul>
     * <li> required
     * </ul>
     */
    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private MsgType msgType = MsgType.SQUARE;

    /**
     * Primary theme color
     * <p>
     * <b> Constraints: </b>
     * <ul>
     * <li> required
     * </ul>
     */
    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Color color = Color.BLUE;

    /**
     * Interface font
     * <p>
     * <b>Constraints:</b>
     * <ul>
     * <li> required
     * </ul>
     */
    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Font font = Font.ARIAL;

    /**
     * Text size
     * <p>
     * <b> Constraints: </b>
     * <ul>
     * <li> required
     * </ul>
     */
    @Column(nullable = false)
    private int size = 20;

    /**
     * Settings owner.
     * Has id common with user model.
     * <p>
     * <b> Constraints: </b>
     * <ul>
     * <li> required
     * <li> constant
     * </ul>
     */
    @JsonIgnore
    @MapsId
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    private User user;

    public MsgType getMsgType() {
        return msgType;
    }

    public void setMsgType(MsgType msgType) {
        this.msgType = msgType;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
