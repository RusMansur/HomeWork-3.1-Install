import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static final String USERPATH = "/Users/rusimac/Games/";

    public static void main(String[] args) {
        File temp = new File(USERPATH + "temp");
        File tempFile = new File(USERPATH + "temp//temp.txt");

        List<File> directories = new ArrayList<>();
            directories.add(new File(USERPATH + "src"));
            directories.add(new File(USERPATH + "res"));
            directories.add(new File(USERPATH + "res/drawables"));
            directories.add(new File(USERPATH + "res/vectors"));
            directories.add(new File(USERPATH + "res/icons"));
            directories.add(new File(USERPATH + "src/main"));
            directories.add(new File(USERPATH + "src/test"));
            directories.add(new File(USERPATH + "savegames"));

        List<File> files = new ArrayList<>();
            files.add(new File(USERPATH + "src/main//Main.java"));
            files.add(new File(USERPATH + "src/main//Utils.java"));


        // Создаём папку temp и файл temp.txt для регистрации процесса создания папок и файлов
        if (temp.mkdir()) {
            try {
                if (tempFile.createNewFile()) {
                    writeToTempFile(temp);
                    writeToTempFile(tempFile);
                }
            } catch (IOException e) {
                System.err.println("Не удалось создать " + tempFile);
            }
        } else {
            System.err.println("Не удалось создать каталог " + temp);
        }

        //Создаём остальные папки
        for (File directory : directories) {
            if (directory.mkdir()) {
                writeToTempFile(directory);
            } else {
                System.err.println("Не удалось создать каталог " + directory);
            }
        }

        //Создаём файлы в папках
        for (File file : files) {
            try {
                if (file.createNewFile()) {
                    writeToTempFile(file);
                }
            } catch (IOException e) {
                System.err.println("Не удалось создать файл: " + file);
            }
        }
    }

    private static void writeToTempFile(File file) {
        try (FileWriter writeTempFile = new FileWriter(USERPATH + "/temp/temp.txt", true)) {
            if (file.getName().contains(".")) {
                writeTempFile.write("Создан файл: " + file);
            } else {
                writeTempFile.write("Создан каталог: " + file);
            }
            writeTempFile.append('\n');
            writeTempFile.flush();
        } catch (IOException e) {
            System.err.println("Не удалось записать в файл строку: " + file);
        }
    }
}
