package finalGUI;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author 
 */
public class Query
{

    private static Connection con = null;
    private static Statement state = null;
    private static ResultSet rs = null;

    private static void Queryinit()
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=SampleDB;integratedSecurity=true");
            con.prepareStatement("", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        }
        catch (Exception ex)
        {
            System.err.println(ex.getMessage());
        }
    }

    public static ResultSet QueryExecute(String Query)
    {
        Queryinit();

        try
        {
            state = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = state.executeQuery(Query);
            return rs;
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

    }

    public static void QueryUpdate(String Query) throws SQLException
    {
        Queryinit();
        state = con.createStatement();
        state.executeUpdate(Query);
    }


}
