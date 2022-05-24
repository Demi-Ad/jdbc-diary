package bootstrap;

import bootstrap.initializer.Initializer;

import java.sql.Connection;
import java.sql.SQLException;

public class Bootstrap {

    private final DatabaseConnector databaseConnector;


    public Bootstrap(DatabaseConnector databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

    public void onLoad() {
        Connection connection = databaseConnector.getConnection();
        try (connection) {
            CreateSQL createSQL = new CreateSQL(connection);
            createSQL.generateTable();
        } catch (SQLException e) {
            System.out.println("테이블을 생성 할 수 없습니다");
        }
    }
}
