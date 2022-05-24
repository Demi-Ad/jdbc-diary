package bootstrap;

import bootstrap.initializer.Initializer;
import dao.DiaryDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    private final String sqliteFilePath;

    public DatabaseConnector() {
        this.sqliteFilePath = Initializer.getDatabaseFilePath();
    }

    public Connection getConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:" + sqliteFilePath);
        } catch (ClassNotFoundException e) {
            System.out.println("클래스를 찾을 수 없습니다");
        } catch (SQLException e) {
            System.out.println("파일을 찾을 수 없습니다");
        }
        throw new RuntimeException();
    }

}
