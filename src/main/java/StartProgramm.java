import Services.EquipmentService;

public class StartProgramm {

    private static final String CON_STR = "jdbc:sqlite:well.db";

    private static EquipmentService equipmentService = EquipmentService.getInstance(CON_STR);

    public static void main(String[] args) {



        System.out.println("Выберите функцию:");
        System.out.println("1-Создание N кол-ва оборудования на скважине.");
        System.out.println("2-Вывод общей информации об оборудовании на скважинах.");
        System.out.println("3-Экспорт всех данных в xml файл.");

        int choose = 2;

        switch (choose) {
            //В процессе
            case 1:
                createNEquipment();
                break;
            //Сделано
            case 2:
                outputAllInformationAboutWell();
                break;
            //TODO Треш
            case 3:
                exportXMLFile();
                break;
        }
      /*  NewFileBD.createFile();

        NewDatabase.createDatabase(CON_STR);*/


    }

    private static void createNEquipment() {
        System.out.println("Введите имя скважины");
        String wellName = "bb";
        System.out.println("Введите количество оборудования");
        int countEquipment = 3;

        equipmentService.createEquipment(wellName, countEquipment);
    }


    private static void outputAllInformationAboutWell() {
        System.out.println("Введите имена скважин через ','");
        String strName = "aa,bb,cc";
        String[] arrName = strName.split(",");
        for (String wellName : arrName) {
            equipmentService.countEquipment(wellName);
        }

    }

    private static void exportXMLFile() {
        System.out.println("Введите имя файла для экспорта в XML");
        String nameFile = "myXML";


    }
}
