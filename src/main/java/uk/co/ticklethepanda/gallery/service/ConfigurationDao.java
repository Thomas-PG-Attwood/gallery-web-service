package uk.co.ticklethepanda.gallery.service;

import javax.naming.ConfigurationException;
import javax.naming.ServiceUnavailableException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by panda on 08/05/16.
 */
public class ConfigurationDao {

  private final DatabaseConnector connector;

  public ConfigurationDao(DatabaseConnector connector) throws ServiceUnavailableException {
    this.connector = connector;
  }
  
  public String getParameter(String parameter) throws SQLException, ConfigurationException, ServiceUnavailableException {

    try(Connection connection = connector.getDatabaseConnection()) {

      ResultSet results = getResultSetForParameter(parameter, connection);

      if (results.next()) {
        return results.getString("VALUE");
      } else {
        throw new ConfigurationException(
            "The {0} in the config table of the database was not set."
                .replace("{0}", parameter));
      }
    }
  }

  private ResultSet getResultSetForParameter(String parameter, Connection connection) throws SQLException {
    PreparedStatement statement = connection.prepareStatement(
        "select * from config where parameter = ?");

    statement.setString(1, parameter);
    statement.execute();

    return statement.executeQuery();
  }
}
