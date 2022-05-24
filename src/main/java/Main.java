import bootstrap.Bootstrap;
import bootstrap.DatabaseConnector;
import bootstrap.initializer.Initializer;
import dao.DiaryDao;
import parser.ArgParser;

public class Main {
    public static void main(String[] args) {
        DatabaseConnector databaseConnector = new DatabaseConnector();
        Bootstrap bootstrap = new Bootstrap(databaseConnector);
        bootstrap.onLoad();
        DiaryDao diaryDao = new DiaryDao(databaseConnector);

        ArgParser parser = new ArgParser(diaryDao);
        parser.parse(args);
    }
}
