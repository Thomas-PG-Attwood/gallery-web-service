package uk.co.ticklethepanda.gallery.service;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import javax.naming.ServiceUnavailableException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.MissingResourceException;
import java.util.Properties;

/**
 * Created by panda on 08/05/16.
 */
public class DatabaseConnector {
  private static final Logger log = LogManager.getLogger();

  private BasicDataSource connectionPool;

  public static final String DATABASE_USERNAME_KEY = "database.username";
  public static final String DATABASE_PASSWORD_KEY = "database.password";

  private final String password;
  private final String username;

  public Connection getDatabaseConnection() throws ServiceUnavailableException {
    try {
      return connectionPool.getConnection();
    } catch (SQLException e) {
      log.error("Could not connect to the database.", e);
      throw new ServiceUnavailableException("Could not connect to the database.");
    }
  }

  public DatabaseConnector() {
    Resource resource = new ClassPathResource("/application.properties");

    try {
      Properties props = PropertiesLoaderUtils.loadProperties(resource);

      username = props.getProperty(DATABASE_USERNAME_KEY);
      password = props.getProperty(DATABASE_PASSWORD_KEY);

      if(username == null || password == null) {
        String missingKeys =
            (username == null ? DATABASE_USERNAME_KEY : "")
            + (username == null && password == null ? ", " : "")
            + (password == null ? DATABASE_PASSWORD_KEY : "");
        throw new MissingResourceException("Could not get property from properties file.",
            "application.properties",
            missingKeys);
      }

    } catch (IOException e) {
      log.error("Could not get properties for database username and password.", e);
      throw new MissingResourceException(
          "Could not get properties for database username and password.",
          "application.properties",
          DATABASE_USERNAME_KEY + ", " + DATABASE_PASSWORD_KEY);
    }

    String dbUrl = "jdbc:mariadb://localhost:3306/galleries?"
        + "user=" + username
        + "&password=" + password;

    connectionPool = new BasicDataSource();

    connectionPool.setDriverClassName("org.mariadb.jdbc.Driver");
    connectionPool.setUrl(dbUrl);
    connectionPool.setInitialSize(10);
  }
}
