package Utility;

import java.io.File;

public class NewFileBD {
    public static void createFile(){
        try {
            // Возьмите файл
            File f = new File("well.db");
            //Создайте новый файл
            // Убедитесь, что он не существует
            if (f.createNewFile())
                System.out.println("File created");
            else
                System.out.println("File already exists");
        }
        catch (Exception e) {
            System.err.println(e);
        }
    }
}
