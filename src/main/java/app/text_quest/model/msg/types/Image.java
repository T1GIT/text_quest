package app.text_quest.model.msg.types;

import app.text_quest.model.msg.Msg;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Arrays;

@Entity
@Table(name = "images")
public class Image extends Msg {
    @NotNull
    @Lob
    @Basic(fetch = FetchType.LAZY)
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
