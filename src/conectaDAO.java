import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.JOptionPane;

public class conectaDAO{
public Connection connectDB() {
Connection conn = null;
Properties props = new Properties();

try {

FileInputStream fs = new FileInputStream("src/database.properties");
props.load(fs);

String use = props.getProperty("db.user");
String password = props.getProperty("db.password");
String url = "jdbc:mysql://localhost/uc11?user=" + user + "&password=" + password;

conn = DriverManager.getConnection(url);
} catch(IOException | SQLException erro) {
  JOptionPane.showMessageDialog(null, "Erro ConectaDAO: " + erro.getMessage());
}

return conn;
}
}
