package bootstrap.initializer;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class Initializer {

    private static final String rootPath = "/jdbc-diary/";
    private static final String databaseName = "diary.sqlite";

    private Initializer() {
    }

    public static String getDatabaseFilePath() {
        String homePath = System.getProperty("user.home") + rootPath;

        File homeFolder = new File(homePath);

        if (!homeFolder.exists()) {
            homeFolder.mkdir();
        }

        File sqliteFile = Arrays.stream(Objects.requireNonNull(homeFolder.listFiles()))
                .filter(file -> file.getName().equals(databaseName))
                .findFirst()
                .orElseGet(() -> {
                    File sqlite = new File(homeFolder.getPath() + "/" + databaseName);
                    try {
                        sqlite.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return sqlite;
                });

        return sqliteFile.getPath();
    }
}
