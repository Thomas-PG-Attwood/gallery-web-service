package uk.co.ticklethepanda.gallery.service.transformers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.ticklethepanda.gallery.service.GalleryDto;
import uk.co.ticklethepanda.gallery.service.data.Gallery;

@Component
public class GalleryTransformer implements Transformer<Gallery, GalleryDto> {

    private ImageTransformer imageTransformer;

    @Autowired
    public GalleryTransformer(
            ImageTransformer transformer) {
        this.imageTransformer = transformer;
    }

    @Override
    public GalleryDto convert(Gallery input) {
        GalleryDto.Builder galleryDtoBuilder =
                new GalleryDto.Builder();

        galleryDtoBuilder.withName(input.getName());
        galleryDtoBuilder.withDateTaken(input.getDateTaken());
        galleryDtoBuilder.withDescription(input.getDescription());
        galleryDtoBuilder.withReference(input.getRef());

        GalleryDto dto = galleryDtoBuilder.build();

        dto.addImages(imageTransformer.convertList(input.getImages()));

        return dto;
    }
}
