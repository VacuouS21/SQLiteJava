package Utility;

import org.sqlite.JDBC;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class ParseSQLForXML {
    public static void createFile(String pathName){

    }
    public static void inputInfoInFile(String pathName,String pathSQLDatabase){

        NewFile.createFile(pathName);

        String sqlForNameWell = "SELECT * FROM well;";
        String sqlForEquipment="Select * FROM equipment WHERE well_id=(?);";
        try {

            DriverManager.registerDriver(new JDBC());
            Connection connection = DriverManager.getConnection(pathSQLDatabase);
            PreparedStatement statementWell = connection.prepareStatement(sqlForNameWell);
            PreparedStatement statementEquipment = connection.prepareStatement(sqlForEquipment);

            ResultSet rs = statementWell.executeQuery();

            FileWriter writer = new FileWriter(pathName, false);

            writer.append("<dbinfo>\n");
            while (rs.next()) {
                writer.append("<well name=\""+rs.getString("name")+"\" id=\""+rs.getInt("id")+"\">");
                writer.append('\n');
                //System.out.println("< well name=\""+rs.getString("name")+"\" id=\""+rs.getInt("id")+"\">");
                statementEquipment.setString(1, rs.getString("id"));
                ResultSet rsEquipment = statementEquipment.executeQuery();
                while (rsEquipment.next()){
                    writer.append("<equipment name=\""+rsEquipment.getString("name")+"\" id=\""+rsEquipment.getInt("id")+"\"/>");
                    writer.append('\n');
                    //System.out.println("< equipment name=\""+rsEquipment.getString("name")+"\" id=\""+rsEquipment.getInt("id")+"\"/>");
                }
                writer.append("</well>");
                writer.append('\n');

            }
            writer.append("</dbinfo>");

                writer.flush();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
/*
* <dbinfo>
<well name="АААА"  id="123">
<equipment name=”EQ0033" id="12"/>
<equipment name=”EQ0034" id="13"/>
</well>
<well name="BBBB"  id="124">
<equipment name=”EQ0038" id="11"/>
<equipment name=”EQ0039" id="14"/>
</well>
</dbinfo >
*/