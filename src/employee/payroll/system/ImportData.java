package employee.payroll.system;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import org.apache.poi.hssf.usermodel.HSSFRow;


public class ImportData {

    /**
     * @param args
     */
    public void pass(String p1){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/smartmenu","root","");
            con.setAutoCommit(false);
            PreparedStatement pstm = null ;
            FileInputStream input = new FileInputStream(p1);
            POIFSFileSystem fs = new POIFSFileSystem( input );
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb.getSheetAt(0);
            Row row;
            for(int i=1; i<=sheet.getLastRowNum(); i++){
                row =  (Row) sheet.getRow(i);
                int id = (int) row.getCell(0).getNumericCellValue();
                String name = row.getCell(1).getStringCellValue();
                String address = row.getCell(2).getStringCellValue();
                String sql = "INSERT INTO emp_attendance VALUES('NULL','"+id+"','"+name+"','"+address+"')";
                pstm = (PreparedStatement) con.prepareStatement(sql);
                pstm.execute();
                System.out.println("Import rows "+i);
            }
            con.commit();
            pstm.close();
            con.close();
            input.close();
            System.out.println("Success import excel to mysql table");
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }catch(SQLException ex){
            System.out.println(ex);
        }catch(IOException ioe){
            System.out.println(ioe);
        }
    }
    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/smartmenu","root","");
            con.setAutoCommit(false);
            PreparedStatement pstm = null ;
            FileInputStream input = new FileInputStream("C://Users//Dhananjana//Desktop//tes//test.xls");
            POIFSFileSystem fs = new POIFSFileSystem( input );
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb.getSheetAt(0);
            Row row;
            for(int i=1; i<=sheet.getLastRowNum(); i++){
                row =  (Row) sheet.getRow(i);
                int id = (int) row.getCell(0).getNumericCellValue();
                String name = row.getCell(1).getStringCellValue();
                String address = row.getCell(2).getStringCellValue();
                String sql = "INSERT INTO test VALUES('"+id+"','"+name+"','"+address+"')";
                pstm = (PreparedStatement) con.prepareStatement(sql);
                pstm.execute();
                System.out.println("Import rows "+i);
            }
            con.commit();
            pstm.close();
            con.close();
            input.close();
            System.out.println("Success import excel to mysql table");
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }catch(SQLException ex){
            System.out.println(ex);
        }catch(IOException ioe){
            System.out.println(ioe);
        }

    }
    
}