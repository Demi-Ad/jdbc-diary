package dao;

import bootstrap.DatabaseConnector;
import vo.DiaryVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class DiaryDao {

    private final String insertSQL = "insert into diary('title','content','createAt') values (?, ?, ?)";
    private final String findAllSQL = "select d.id as id, d.title as title, d.content as content, d.createAt as createAt from diary as d";

    private final String findOneSQL = "select d.id as id, d.title as title, d.content as content, d.createAt as createAt from diary as d where d.id = ?";

    private final String deleteOne = "delete from diary where id = ?";

    private final DatabaseConnector databaseConnector;

    public DiaryDao(DatabaseConnector databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

    public void save(DiaryVO diaryVO) {
        Connection connection = databaseConnector.getConnection();
        try(connection) {
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setString(1, diaryVO.getTitle());
            preparedStatement.setString(2, diaryVO.getContent());
            preparedStatement.setString(3, diaryVO.getCreatedAt());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<DiaryVO> findAll() {
        Connection connection = databaseConnector.getConnection();
        try(connection) {
            PreparedStatement preparedStatement = connection.prepareStatement(findAllSQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<DiaryVO> diaryList = new ArrayList<>();
            while (resultSet.next()) {
                DiaryVO diaryVO = getDiaryVO(resultSet);
                diaryList.add(diaryVO);
            }
            return diaryList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public DiaryVO findOne(Integer id) {
        Connection connection = databaseConnector.getConnection();
        try(connection) {
            PreparedStatement preparedStatement = connection.prepareStatement(findOneSQL);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return getDiaryVO(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("존재하지 않는 일기");
        }
    }

    public void deleteOne(Integer id) {
        Connection connection = databaseConnector.getConnection();
        try(connection) {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteOne);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private DiaryVO getDiaryVO(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        String content = resultSet.getString("content");
        String createAt = resultSet.getString("createAt");
        return new DiaryVO(id, title,content,createAt);
    }
}
