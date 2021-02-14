package app.text_quest.model.msg.types;

import app.text_quest.model.msg.Msg;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "images")
public class Image extends Msg {

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private byte[] file;

    public Image() { }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] image) {
        this.file = image;
    }

    @Override
    public String toString() {
        return "Image{" +
                "file=" + Arrays.toString(file) +
                '}';
    }
}
