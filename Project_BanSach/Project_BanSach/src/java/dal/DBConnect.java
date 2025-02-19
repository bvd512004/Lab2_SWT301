package dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

    protected Connection conn = null;

    public DBConnect(String url, String userName, String pass) {
        try {
            DriverManager.getConnection(url, userName, pass);
        } catch (SQLException e) {
            ExceptionHandlers.handleException(e);
        }
    }

    public DBConnect() {
        this("jdbc:sqlserver://localhost:1433;databaseName=bansach", "sa", "123");
    }

    public ResultSet getData(String sql) {
        ResultSet rs = null;

        try (Statement state = conn.createStatement(
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE
        )) {
            rs = state.executeQuery(sql);
        } catch (SQLException e) {
            ExceptionHandlers.handleException(e);
        }
        return rs;
    }
}
