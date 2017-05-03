package uk.co.ticklethepanda.gallery.service;

import java.util.ArrayList;
import java.util.List;

public class GalleriesDto {

  private String thumbsPath;
  private String fullPath;
  private List<GalleryDto> galleries = new ArrayList<>();

  public boolean containsGalleryByReference(String reference) {
    return getGalleries().stream().anyMatch(g -> g.getReference().equals(reference));
  }

  public void addImageToGallery(String galleryReference, ImageDto imageDto) {
    this.getGalleries().stream()
        .filter(g -> g.getReference().equals(galleryReference))
        .findFirst()
        .get()
        .addImage(imageDto);
  }

  public void addGallery(GalleryDto galleryDto) {
    getGalleries().add(galleryDto);
  }

  public List<GalleryDto> getGalleries() {
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

  public void addGalleries(List<GalleryDto> galleriesDtos) {
    this.galleries.addAll(galleriesDtos);
  }
}
