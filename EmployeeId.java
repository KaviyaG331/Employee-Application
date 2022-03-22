//import java.util.Scanner;
//import java.util.regex.Matcher;
import java.sql.*;
import java.util.Scanner;
import java.util.regex.Pattern;
public class EmployeeId extends DOJ {
	public static String empId1;
	public static String getid() {
		return empId1;
	}
	public static void setid(String id) {
		empId1=id;
	}
    public static boolean check(String empId){
        try{
            //System.out.println("1");
            if(empId.length()!=7){
                throw new Exception();
            }
            try{
                if(!(Pattern.matches("[aA][cC][eE]",empId.substring(0,3)))){
                    throw new Exception();
                }
                try{
                    for(int index=3;index<7;index++){
                        if(!Character.isDigit(empId.charAt(index))){
                            throw new Exception();
                        }
                    }//System.out.println("1");//
                    if(!present(empId)){
                    	setid(empId);
                    	return true;
                    }
                    System.out.println("Duplicate Id , please enter new id.");
                    return false;
                }
                catch(Exception excp){
                    System.out.println("First 3 charcters should start with ACE and next four should be digits.");
					return false;
                }
            }
            catch(Exception excp){
                System.out.println("First 3 charcters should start with ACE and next four should be digits.");
				return false;
            }
        }
        catch(Exception excp){
            System.out.println("First 3 charcters should start with ACE and next four should be digits.");
			return false;
        }
    }
    public static String newEmployeeId() {
    	Scanner sc = new Scanner(System.in);
    	while(true) {
    		System.out.println("Enter the new Employee Id");
    		String newid = sc.next();
    		if(check(newid)) {
    			return newid;
    		}
            System.out.println("First 3 charcters should start with ACE and next four should be digits.");
    	}
    }
    public static void updateEmployeeId(String oldid) {
      	 String newid = newEmployeeId(); 
      	 try {
       		Class.forName("com.mysql.cj.jdbc.Driver");
       		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/aspire_db","root","Kavi@9321$21");
       		PreparedStatement pstmt = null;
       		//Statement stmt=con.createStatement();
   			String sql2="update emp set empid =? where empid=? or emailid=?";
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
