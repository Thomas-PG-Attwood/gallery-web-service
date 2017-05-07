package uk.co.ticklethepanda.gallery.service;

/**
 * Created by panda on 08/05/16.
 */
public class ImageDto {

    private final String description;
    private final String name;
    private final String fileName;
    private final boolean favourite;

    private ImageDto(String name, String description, String fileName, boolean favourite) {
        this.name = name;
        this.description = description;
        this.fileName = fileName;
        this.favourite = favourite;
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

    public boolean isFavourite() {
        return favourite;
    }

    public static class Builder {
        private String name;
        private String description;
        private String fileName;
        private boolean favourite;

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

        public Builder withFavourite(boolean favourite) {
            this.favourite = favourite;
            return this;
        }

        public ImageDto build() {
            return new ImageDto(name, description, fileName, favourite);
        }
    }
}
