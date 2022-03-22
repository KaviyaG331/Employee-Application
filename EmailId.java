//import java.util.regex.Matcher;
import java.sql.*;
import java.util.Scanner;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.*;
import java.util.regex.Pattern;
//import java.util.Scanner;


class EmailId {
	public static String empemailid;
	public static String getEmailid() {
		return empemailid;
	}
	public static void setEmailid(String Emailid) {
		empemailid=Emailid;
	}
	
	public static boolean isValid(String email){
			String emailRegex = "^[a-z0-9]+(?:\\."+"[a-z0-9]+)*@" +"(?:[g][m][a][i][l]+\\.)+[c][o][" +"m]";
			Pattern pat = Pattern.compile(emailRegex);
			if (email == null)
				return false;
			else if(pat.matcher(email).matches()){
				if(!present(email)) {
					setEmailid(email);
					return true;
				}
			}
            System.out.println("Duplicate Id , please enter new id.");
			return false;
	}
	public static boolean present(String delete) {
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/aspire_db","root","Kavi@9321$21");
    		PreparedStatement pstmt = null;
    		//Statement stmt=con.createStatement();
			String sql2="Select empid from emp where empid=(?) or emailid=(?)";
			pstmt=con.prepareStatement(sql2);
			pstmt.setString(1,delete);
			pstmt.setString(2, delete);
			ResultSet rs=pstmt.executeQuery();
		    return rs.isBeforeFirst();
    	}
    	catch(Exception e){
    		System.out.println(e);
    	}
    	return true;
    	
    }
	
	public static String newMailId() {
    	Scanner sc = new Scanner(System.in);
    	while(true) {
    		System.out.println("Enter the new Email Id: ");
    		String newid = sc.next();
    		if(isValid(newid)) {
    			return newid;
    		}
            System.out.println("The Mail Id Should have proper Domain name.");
    	}
    }
    public static void updateEmailId(String oldid) {
      	 String newid = newMailId(); 
      	 try {
       		Class.forName("com.mysql.cj.jdbc.Driver");
       		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/aspire_db","root","Kavi@9321$21");
       		PreparedStatement pstmt = null;
       		//Statement stmt=con.createStatement();
   			String sql2="update emp set emailid =? where empid=? or emailid=?";
   			pstmt=con.prepareStatement(sql2);
   			pstmt.setString(1,newid);
   			pstmt.setString(2, oldid);
   			pstmt.setString(3, oldid);
   			pstmt.executeUpdate();
   		}
       	catch(Exception e){
       		System.out.println(e);
       	}
       }
}
