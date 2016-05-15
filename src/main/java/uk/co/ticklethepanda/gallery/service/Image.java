package uk.co.ticklethepanda.gallery.service;

/**
 * Created by panda on 08/05/16.
 */
public class Image {

  private final String description;
  private final String name;
  private final String fileName;

  private Image(String name, String description, String fileName) {
    this.name = name;
    this.description = description;
    this.fileName = fileName;
  }

  public String getDescription() {
    return description;
  }

  public String getName() {
    return name;
  }

  public String getFileName() {
    return fileName;
  }

  public static class Builder {
    private String name;
    private String description;
    private String fileName;

    public Builder withName(String name) {
      this.name = name;
      return this;
    }

    public Builder withDescription(String description) {
      this.description = description;
      return this;
    }

    public Builder withFileName(String fileName) {
      this.fileName = fileName;
      return this;
    }

    public Image build() {
      return new Image(name, description, fileName);
    }
  }
}
