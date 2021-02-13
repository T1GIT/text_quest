package app.text_quest.models.Msg.types;

import app.text_quest.models.Msg.Msg;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "images")
public class Image extends Msg {
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private byte[] image;

    public Image() { }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Image{" +
                "image=" + Arrays.toString(image) +
                '}';
    }
}
