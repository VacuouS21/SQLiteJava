package Entity;

public class Equipment {
        // Поля класса
        public int id;
        public String name;
        public int well_id;

        // Конструктор
        public Equipment(int id, String name,int well_id) {
            this.id = id;
            this.name=name;
            this.well_id=well_id;
        }


}
