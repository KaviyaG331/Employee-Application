import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.Date;  
//import java.text.ParseException;
import java.sql.*;
class DOJ extends DOB{
	public static String empdoj;
	public static String getDoj() {
		return empdoj;
	}
	public static void setDoj(String doj) {
		empdoj=doj;
	}
    //static ArrayList<String> Doj=new ArrayList<>();
    
  
    
    
    public static boolean dateValidation1(String date)
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
    
    //overloading
    public static boolean dateValidation1(String date ,String pass )
    {
        boolean status = false;
        currentTable(pass);
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
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now=LocalDateTime.now();
        String date1=dtf.format(now);
        String[] s1=date1.split("/");
        String[] s2=date.split("/");
        String[] s3=getDob().split("/");
        int currentdate=Integer.parseInt(s1[2]+s1[1]+s1[0]);
        int inputdate=Integer.parseInt(s2[2]+s2[1]+s2[0]);
        int birthdate=Integer.parseInt(s3[2]+s3[1]+s3[0]);
        
//        System.out.println(birthdate);
        if (date.matches(pattern) ) {
            if(inputdate>=birthdate+180000 && inputdate<=birthdate+600000 && inputdate<=currentdate)
            flag = true;
            setDoj(date);
        }
        return flag;
    }
    public static String newDoj(String olddoj) {
    	Scanner sc = new Scanner(System.in);
    	while(true) {
    		System.out.println("Enter the new Date of Joining");
    		String newdoj = sc.next();
    		if(dateValidation1(newdoj,olddoj)) {
    			return newdoj;
    		}
            System.out.println("Enter the correct date format and age between 18 to 60.");
    	}
    }
    public static void updateDoj(String olddoj) {
      	 String newdoj = newDoj(olddoj); 
      	 try {
       		Class.forName("com.mysql.cj.jdbc.Driver");
       		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/aspire_db","root","Kavi@9321$21");
       		PreparedStatement pstmt = null;
       		//Statement stmt=con.createStatement();
   			String sql2="update emp set doj =? where empid=? or emailid=?";
   			pstmt=con.prepareStatement(sql2);
   			pstmt.setString(1,newdoj);
   			pstmt.setString(2, olddoj);
   			pstmt.setString(3, olddoj);
   			pstmt.executeUpdate();
   		}
       	catch(Exception e){
       		System.out.println(e);
       	}
       }
}
