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

  private final Connection connection;

  public ConfigurationDao(DatabaseConnector connector) throws ServiceUnavailableException {
    this.connection = connector.getDatabaseConnection();
  }
  
  public String getParameter(String parameter) throws SQLException, ConfigurationException {
    ResultSet results = getConfigResults(parameter);

    if(results.next()) {
      return results.getString("VALUE");
    } else {
      throw new
          ConfigurationException("The " + parameter +" in the config table of the database was not set.");
    }
  }

  private ResultSet getConfigResults(String parameter) throws SQLException {
    PreparedStatement statement = connection.prepareStatement(
        "select * from config where parameter = ?");

    statement.setString(1, parameter);
    statement.execute();

    return statement.getResultSet();
  }
}
