package uk.co.ticklethepanda.gallery.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by panda on 08/05/16.
 */
public class GalleryDto {

  private final String reference;
  private final String name;
  private final String description;
  private final LocalDate dateTaken;
  private final List<ImageDto> images;

  public GalleryDto(String reference, String name, String description, LocalDate dateTaken) {
    this.reference = reference;
    this.name = name;
    this.description = description;
    this.dateTaken = dateTaken;
    this.images = new ArrayList<>();
  }

  public String getReference() {
    return reference;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public LocalDate getDateTaken() {
    return dateTaken;
  }

  public void addImage(ImageDto imageDto) {
    this.getImages().add(imageDto);
  }

  public List<ImageDto> getImages() {
    return images;
  }

  public void addImages(List<ImageDto> imageDtos) {
    images.addAll(imageDtos);
  }

  public static class Builder {
    private LocalDate dateTaken;
    private String description;
    private String reference;
    private String name;

    public Builder withReference(String reference) {
      this.reference = reference;
      return this;
    }

    public Builder withName(String name) {
      this.name = name;
      return this;
    }

    public Builder withDescription(String description) {
      this.description = description;
      return this;
    }

    public Builder withDateTaken(LocalDate dateTaken) {
      this.dateTaken = dateTaken;
      return this;
    }

    public GalleryDto build() {
      return new GalleryDto(reference, name, description, dateTaken);
    }
  }
}
