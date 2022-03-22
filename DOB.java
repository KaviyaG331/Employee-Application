//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
//import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.time.LocalDateTime;
//import java.util.ArrayList;
import java.sql.*;

abstract class DOB extends EmailId{
	public static String empDob;
	public static String getDob() {
		return empDob;
	}
	public static void setDob(String dob) {
		empDob=dob;
//			System.out.println(empDob);
	}
    //static ArrayList<String> Date=new ArrayList<>();
    
	public static void currentTable(String pass) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
       		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/aspire_db","root","Kavi@9321$21");
       		PreparedStatement pstmt = null;
       		//Statement stmt=con.createStatement();
   			String sql2="select dob from emp where empid=? or emailid=?";
   			pstmt=con.prepareStatement(sql2);
   			pstmt.setString(1, pass);
   			pstmt.setString(2, pass);
   			ResultSet rs = pstmt.executeQuery();
   			while(rs.next()) {
   				setDob(rs.getString(1));
   			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
    
    public static boolean dateValidation(String date)
    {
        boolean status = false;
        if (checkDate(date)){
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            dateFormat.setLenient(false);
            try {
                dateFormat.parse(date);
                status = true;
            }
            catch (Exception excp) {
                status = false;
            }
        }
        return status;
    }
    
    static boolean checkDate(String date) {
        String pattern = "(0?[1-9]|[12][0-9]|3[01])\\/(0?[1-9]|1[0-2])\\/([0-9]{4})";
        boolean flag = false;
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy");//format.
        LocalDateTime now=LocalDateTime.now();//system's current date.
        int currenyear=Integer.parseInt(dtf.format(now));
        if (date.matches(pattern)) {
            int length=date.length();
            int year=Integer.parseInt(date.substring(length-4,length));
            if(year<=currenyear-18 && year>=currenyear-60){
            	setDob(date);
                flag = true;
            }
        }
        return flag;
    }
    public static String newDob() {
    	Scanner sc = new Scanner(System.in);
    	while(true) {
    		System.out.println("Enter the new Date of Birth");
    		String newdob = sc.next();
    		if(dateValidation(newdob)) {
    			return newdob;
    		}
            System.out.println("Enter the correct date format and age between 18 to 60.");
    	}
    }
    public static void updateDob(String olddob) {
      	 String newdob = newDob(); 
      	 try {
       		Class.forName("com.mysql.cj.jdbc.Driver");
       		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/aspire_db","root","Kavi@9321$21");
       		PreparedStatement pstmt = null;
       		//Statement stmt=con.createStatement();
   			String sql2="update emp set dob =? where empid=? or emailid=?";
   			pstmt=con.prepareStatement(sql2);
   			pstmt.setString(1,newdob);
   			pstmt.setString(2, olddob);
   			pstmt.setString(3, olddob);
   			pstmt.executeUpdate();
   		}
       	catch(Exception e){
       		System.out.println(e);
       	}
       }
}
