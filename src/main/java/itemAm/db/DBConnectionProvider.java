package itemAm.db;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnectionProvider {
    private static final DBConnectionProvider instance = new DBConnectionProvider();
    private Connection connection;

    private String driverName;
    private String dbUrl;
    private String username;
    private String password;

    private DBConnectionProvider(){
        try{
            loadProperties();
            Class.forName(driverName);
        }catch (ClassNotFoundException | IOException e){
            e.printStackTrace();
        }
    }

    private void loadProperties() throws IOException {
        Properties properties = new Properties();
        properties.load(Files.newInputStream(Paths.get("C:\\Users\\Hovhanes Gevorgyan\\IdeaProjects\\Autho.am\\src\\main\\resources\\config.properties")));
        driverName = properties.getProperty("db.driver.name");
        dbUrl = properties.getProperty("db.url");
        username = properties.getProperty("db.username");
        password = properties.getProperty("db.password");
    }

    public static DBConnectionProvider getInstance() {
        return instance;
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(dbUrl,username,password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return connection;
    }
}