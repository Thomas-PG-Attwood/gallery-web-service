package uk.co.ticklethepanda.gallery.service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by panda on 08/05/16.
 */
public class Galleries {

  private String thumbsPath;
  private String fullPath;
  private List<Gallery> galleries = new ArrayList<>();

  public boolean containsGalleryByReference(String reference) {
    return getGalleries().stream().anyMatch(g -> g.getReference().equals(reference));
  }

  public void addImageToGallery(String galleryReference, Image image) {
    this.getGalleries().stream()
        .filter(g -> g.getReference().equals(galleryReference))
        .findFirst()
        .get()
        .addImage(image);
  }

  public void addGallery(Gallery gallery) {
    getGalleries().add(gallery);
  }

  public List<Gallery> getGalleries() {
    return this.galleries;
  };

  public String getThumbsPath() {
    return thumbsPath;
  }

  public String getFullPath() {
    return fullPath;
  }

  public void setThumbsPath(String thumbsPath) {
    this.thumbsPath = thumbsPath;
  }

  public void setFullPath(String fullPath) {
    this.fullPath = fullPath;
  }
}
