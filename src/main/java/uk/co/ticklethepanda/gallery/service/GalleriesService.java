package uk.co.ticklethepanda.gallery.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.ConfigurationException;
import javax.naming.ServiceUnavailableException;
import java.sql.SQLException;

/**
 * Created by panda on 08/05/16.
 */
public class GalleriesService {

  public static final Logger log = LogManager.getLogger();

  GalleriesDao galleriesDao;
  ConfigurationDao configurationDao;

  public GalleriesService(DatabaseConnector connector) throws ServiceUnavailableException {
    galleriesDao = new GalleriesDao(connector);
    configurationDao = new ConfigurationDao(connector);
  }

  public Galleries getAllGalleries() throws SQLException, ConfigurationException, ServiceUnavailableException {

    log.trace("starting getAllGalleries()");
    String thumbsPath = configurationDao.getParameter("THUMBS_PATH");

    String fullPath = configurationDao.getParameter("FULL_PATH");

    Galleries galleries = galleriesDao.getAllGalleries();
    galleries.setThumbsPath(thumbsPath);
    galleries.setFullPath(fullPath);

    log.trace("exiting getAllGalleries()");

    return galleries;

  }


}
