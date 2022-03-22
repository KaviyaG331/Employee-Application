//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.util.Scanner;
import java.sql.*;
//import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Name extends EmployeeId{
	public static String name2;
	public static String getname() {
		return name2;
	}
	public static void setname(String ename) {
		name2=ename;
	}
	
     public static boolean checkName(String name){
        if(Pattern.matches("[a-zA-Z ]{3,40}",name)){
            return true;
        }
        return false;
    }
     public static String newName() {
    	 Scanner sc = new Scanner(System.in);
    	 while(true) {
    		 System.out.println("Enter the new name: ");
    		 String newname = sc.next();
    		 if(checkName(newname)) {
    			 return newname;
    		 }
             System.out.println("The name should contains only alphabets, and it should contains more than 2 repeating characters continuously and it should not contain any number or special characters.");
    	 }
     }
     public static void updateName(String oldname) {
    	 String newname = newName(); 
    	 try {
     		Class.forName("com.mysql.cj.jdbc.Driver");
     		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/aspire_db","root","Kavi@9321$21");
     		PreparedStatement pstmt = null;
     		
     		//Statement stmt=con.createStatement();
 			String sql2="update emp"+" set name =? where empid=? or emailid=?";
 			pstmt=con.prepareStatement(sql2);
 			pstmt.setString(1,newname);
 			pstmt.setString(2, oldname);
 			pstmt.setString(3, oldname);
   			pstmt.executeUpdate();
 		}
     	catch(Exception e){
     		System.out.println(e);
     	}
     }
     
    public static int choice() {
    	Scanner sc =  new Scanner(System.in);
    	int num=sc.nextInt();
    	
    	return num;
    }
    
    public static String recordDelete() {
    	Scanner sc =  new Scanner(System.in);
    	String str = sc.next();
    	return str;
    }
    
    
      
}
