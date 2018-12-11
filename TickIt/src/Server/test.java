package Server;

import db.dbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class test {

    private static Connection c = null;

    public test() {
        c = new dbConnection().dbConnector();

    }

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = df.format(new Date());
        Date d = df.parse(date);
        Calendar gc = new GregorianCalendar();
        gc.setTime(d);
        gc.add(Calendar.HOUR, 2);
        Date d2 = gc.getTime();

        System.out.println("df:" + df);
        System.out.println("date:"+date);
        System.out.println("d:"+d);
        System.out.println("gc:"+gc);
        System.out.println("d2:"+d2);

        String sDate = date.toString();
        String email = null;

        try {
            String qu = "SELECT email FROM users " +
                    "JOIN listings l ON users.id = l.seller_id " +
                    "WHERE listing_end <= ?;";
            
            PreparedStatement pst = c.prepareStatement(qu);
            pst.setString(1, sDate);

            ResultSet rs =  pst.executeQuery();

            email = rs.getString(1);

            rs.close();
            pst.close();

        } catch (Exception e2) {
            System.out.println("unable to check password.");
        }
        System.out.println(email);
    }

}
