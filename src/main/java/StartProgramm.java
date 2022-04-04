import Services.WellService;

import java.nio.ByteBuffer;
import java.sql.SQLException;
import java.util.Locale;
import java.util.UUID;

public class StartProgramm {

    public static String shortUUID() {
        UUID uuid = UUID.randomUUID();
        long l = ByteBuffer.wrap(uuid.toString().getBytes()).getLong();
        return Long.toString(l, Character.MAX_RADIX).toUpperCase(Locale.ROOT);
    }

    public static void main(String[] args) {
        try {
            WellService wellService=WellService.getInstance();
            wellService.createWell("wwww");
            wellService.createWell("aaaa");
            wellService.soutWell();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
