package bootstrap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateSQL {

    private static final String checkTableSQL = "select count(*) as table_count from sqlite_master where type = 'table'";
    private static final String diaryTableCreateSQL = "create table diary(id integer constraint table_name_pk primary key autoincrement, title TEXT, content blob, createAt text)";
    private final Connection connection;

    public void generateTable() throws SQLException {
            if (isFirstExecute()) {
                connection.prepareStatement(diaryTableCreateSQL).execute();
        }
    }

    public CreateSQL(Connection connection) {
        this.connection = connection;
    }

    private boolean isFirstExecute() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(checkTableSQL);
        ResultSet resultSet = preparedStatement.executeQuery();
        int tableCount = resultSet.getInt("table_count");
        return tableCount == 0;
    }
}
