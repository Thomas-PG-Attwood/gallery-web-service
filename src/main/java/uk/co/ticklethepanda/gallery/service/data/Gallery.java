package uk.co.ticklethepanda.gallery.service.data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "GALLERY")
public class Gallery implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="GALLERY_ID")
    private long id;

    @Column(
            name = "GALLERY_REF",
            nullable = false,
            unique = true)
    private String ref;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "DATE_TAKEN")
    private LocalDate dateTaken;

    @OneToMany(targetEntity = Image.class)
    @JoinColumn(
            name = "GALLERY_REF",
            referencedColumnName = "GALLERY_REF")
    private List<Image> images;

    public Gallery() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateTaken() {
        return dateTaken;
    }

    public void setDateTaken(LocalDate dateTaken) {
        this.dateTaken = dateTaken;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
