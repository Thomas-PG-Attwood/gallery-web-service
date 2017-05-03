package uk.co.ticklethepanda.gallery.service.transformers;

import org.springframework.stereotype.Component;
import uk.co.ticklethepanda.gallery.service.ImageDto;
import uk.co.ticklethepanda.gallery.service.data.Image;

@Component
public class ImageTransformer implements Transformer<Image, ImageDto> {

    @Override
    public ImageDto convert(Image input) {

        return new ImageDto.Builder()
                        .withName(input.getName())
                        .withDescription(input.getDescription())
                        .withFileName(input.getFileName())
                        .build();
    }
}
