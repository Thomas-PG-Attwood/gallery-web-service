package uk.co.ticklethepanda.gallery.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.ConfigurationException;
import javax.naming.ServiceUnavailableException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by panda on 08/05/16.
 */
public class GalleriesDao {
  public static final Logger log = LogManager.getLogger();

  private final DatabaseConnector connector;

  public GalleriesDao(DatabaseConnector connector) {
    this.connector = connector;
  }

  public Galleries getAllGalleries() throws ServiceUnavailableException, SQLException, ConfigurationException {

    log.trace("Starting getAllGalleries()");

    try (Connection connection = connector.getDatabaseConnection()) {

      ResultSet results = getResultsForAllGalleries(connection);

      Galleries galleries = new Galleries();

      while (results.next()) {

        log.trace("getting a result");

        if (!galleries.containsGalleryByReference(results.getString("gallery.gallery_ref"))) {
          galleries.addGallery(new Gallery.Builder()
              .withReference(results.getString("gallery.gallery_ref"))
              .withName(results.getString("gallery.name"))
              .withDescription(results.getString("gallery.description"))
              .withDateTaken(results.getDate("gallery.date_taken").toLocalDate())
              .build());
        }
        if (results.getString("image.gallery_ref") != null && !results.getString("image.gallery_ref").isEmpty()) {
          galleries.addImageToGallery(results.getString("image.gallery_ref"),
              new Image.Builder()
                  .withName(results.getString("image.name"))
                  .withDescription(results.getString("image.description"))
                  .withFileName(results.getString("image.file_name"))
                  .build());
        }
      }

      log.trace("Finishing getAllGalleries()");

      return galleries;
    }

  }

  private ResultSet getResultsForAllGalleries(Connection connection) throws SQLException {
    PreparedStatement statement = connection.prepareStatement(
        "select * from gallery left join " +
            "image on gallery.gallery_ref = image.gallery_ref");

    statement.execute();

    return statement.getResultSet();
  }
}
