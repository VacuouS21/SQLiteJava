import Services.EquipmentService;
import Utility.NewDatabase;
import Utility.NewFile;
import Utility.ParseSQLForXML;

import java.util.Scanner;

public class StartProgramm {

    private static final String CON_STR = "jdbc:sqlite:test.db";

    private final static EquipmentService equipmentService = EquipmentService.getInstance(CON_STR);

    public static void main(String[] args) {

        NewFile.createFile("test.db");

        NewDatabase.createDatabase(CON_STR);

        System.out.println("Выберите функцию:");
        System.out.println("1-Создание N кол-ва оборудования на скважине.");
        System.out.println("2-Вывод общей информации об оборудовании на скважинах.");
        System.out.println("3-Экспорт всех данных в xml файл.");

        Scanner sc = new Scanner(System.in);
        int choose = sc.nextInt();
        switch (choose) {
            //В процессе
            case 1:
                createNEquipment(sc);
                break;
            //Сделано
            case 2:
                outputAllInformationAboutWell(sc);
                break;
            case 3:
                exportXMLFile(sc);
                break;
        }

        sc.close();
    }

    private static void createNEquipment(Scanner sc) {

        System.out.println("Введите имя скважины");
        String wellName = sc.next();
        System.out.println("Введите количество оборудования");
        int countEquipment = sc.nextInt();
        equipmentService.createEquipment(wellName, countEquipment);
    }


    private static void outputAllInformationAboutWell(Scanner sc) {

        System.out.println("Введите имена скважин через \",\"");
        String strName = sc.next();

        String[] arrName = strName.split(",");
        for (String wellName : arrName) {
            if(wellName!=null || !wellName.equals(""))
            equipmentService.countEquipment(wellName);
            else System.out.println("Имя скважины не может быть пустым");
        }

    }

    private static void exportXMLFile(Scanner sc) {

        System.out.println("Введите имя файла для экспорта в XML");
        String pathName=sc.next();
        pathName+=".xml";
        ParseSQLForXML.inputInfoInFile(pathName,CON_STR);
    }
}
