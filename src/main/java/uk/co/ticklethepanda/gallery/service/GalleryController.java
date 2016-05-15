package uk.co.ticklethepanda.gallery.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.ConfigurationException;
import javax.naming.ServiceUnavailableException;
import java.sql.SQLException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by panda on 08/05/16.
 */
@RestController
public class GalleryController {

  public static final Logger log = LogManager.getLogger();

  GalleriesService service;

  public GalleryController() throws ServiceUnavailableException {
    this.service = new GalleriesService(new DatabaseConnector());
  }

  @RequestMapping(value="/gallery", method=GET)
  public Galleries gallery() throws SQLException, ServiceUnavailableException, ConfigurationException {
    log.trace("starting gallery");
    return service.getAllGalleries();
  }
}
