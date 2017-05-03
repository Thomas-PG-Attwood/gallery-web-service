package uk.co.ticklethepanda.gallery.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import uk.co.ticklethepanda.gallery.service.data.Gallery;
import uk.co.ticklethepanda.gallery.service.data.GalleryRepo;
import uk.co.ticklethepanda.gallery.service.transformers.GalleryTransformer;

import javax.naming.ConfigurationException;
import javax.naming.ServiceUnavailableException;
import java.sql.SQLException;
import java.util.List;

@Service
public class GalleriesService {

  public static final Logger log = LogManager.getLogger();

  private final String thumbsPath;
  private final String fullPath;

  private final GalleryRepo galleryRepo;
  private final GalleryTransformer galleryTransformer;

  @Autowired
  public GalleriesService(
          GalleryRepo repo,
          GalleryTransformer galleryTransformer,
          @Value("${gallery.service.thumbs.path}") String thumbsPath,
          @Value("${gallery.service.full.path}") String fullPath
  ) {
    this.galleryRepo = repo;
    this.thumbsPath = thumbsPath;
    this.fullPath = fullPath;
    this.galleryTransformer = galleryTransformer;
  }

  public GalleriesDto getAllGalleries() throws SQLException, ConfigurationException, ServiceUnavailableException {

    log.trace("starting getAllGalleries()");

    List<Gallery> galleries = galleryRepo.findAll();

    GalleriesDto galleriesDto = new GalleriesDto();

    galleriesDto.addGalleries(galleryTransformer.convertList(galleries));
    galleriesDto.setThumbsPath(thumbsPath);
    galleriesDto.setFullPath(fullPath);

    log.trace("exiting getAllGalleries()");

    return galleriesDto;

  }


}
