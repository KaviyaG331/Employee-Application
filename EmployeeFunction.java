import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
//import java.sql.*;
public class EmployeeFunction extends Name {

	public static boolean isEmpty() {
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
       		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/aspire_db","root","Kavi@9321$21");
       		PreparedStatement pstmt = null;
       		Statement stmt = con.createStatement();
       	    ResultSet rs = stmt.executeQuery("SELECT * from emp");
   			if(rs.next()==false) {
   				return false;
//   				setDob(rs.getString(1));
   			}
   			else {
   				return true;
   			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
    	return true;
    }
	
	public static void add() {
    	Scanner sc = new Scanner(System.in);
    	while(true){
            System.out.println("Enter the Name");
            String name1=sc.nextLine();
            if(checkName(name1)){
                //Statement stmt=con.createStatement();
            	setname(name1);
                break;
            }
            else{
                System.out.println("The name should contains only alphabets, and it should contains more than 2 repeating characters continuously and it should not contain any number or special characters.");
            }
        }
    	
    	while(true){
            System.out.println("Employee Id: ");
            String empId2 = sc.next();
            if(check(empId2)) {
            	break;
            }
        }
    	
    	while(true) {
        	System.out.println("Enter a date of Birth in dd/mm/yyyy format");
        	String date = sc.next();
        	if(dateValidation(date)==true){
        		break;
        	}
        	else{
        		System.out.println("Date format should be in the form of dd/mm/yyyy and the age needs to be between 18 & 60.");
        	}
        }
    	
    	while(true) {
        	System.out.println("Enter a date of join in dd/mm/yyyy format");
        	String date = sc.next();
        	if(dateValidation1(date)==true){
        		break;
        	}
        	else{
        		System.out.println("Date format should be in the form of dd/mm/yyyy, and age should be greater than 20, and future dates are not considered for date of joining.");
        		
        	}
        }
    	
    	while(true){
			System.out.println("Enter the email Id ");
			String email =sc.next();
			if (isValid(email)){
//					String sql = "INSERT INTO emp(emailid) VALUES (?)";
//					pstmt = con.prepareStatement(sql);
//					pstmt.setString(1, email);
					break;
				
			}
			else
			System.out.println("The mail id should have proper domain name & it should not contain any special characters except @ and all characters must be in lowercase.");
		}	
    	
    }
    
    public static void updateall(String index){
        Scanner scan=new Scanner(System.in);
        try {
            System.out.println("choose any one to update");
            Thread.sleep(1000);
            System.out.println("1.Name:");
            Thread.sleep(1000);
            System.out.println("2.EmployeeId:");
            Thread.sleep(1000);
            System.out.println("3.Date of birth:");
            Thread.sleep(1000);
            System.out.println("4.Date of join:");
            Thread.sleep(1000);
            System.out.println("5.EmailId:");
            Thread.sleep(1000);
        }
        catch(InterruptedException excp){
            System.out.println("My Thread interrupted");
        }
        while(true){
                int value=scan.nextInt();
                switch (value){
                    case 1:
                        updateName(index);
                        return;
                    case 2:
                        updateEmployeeId(index);
                        return;
                    case 3:
                        updateDob(index);
                        return;
                    case 4:
                        updateDoj(index);
                        return;
                    case 5:
                        updateEmailId(index);
                        return;
                    default:
                        System.out.println("Entered number should be between 1 to 5.");
                }
            }
    }
    
    public static void update(){
        Scanner scan=new Scanner(System.in);
//            if(size()!=0){
                while(true){
                    System.out.println("1.Update through EmployeeId");
                    System.out.println("2.Update through EmailId");
                    int choose=scan.nextInt();
                    switch(choose){
                        case 1:
                            while(true){
                                System.out.println("Enter EmployeeId you want to change ");
                                String oldeid=scan.next();
                                if(present(oldeid)) {
                                	updateall(oldeid);
                                    break;
                                }
                                else{
                                    System.out.println("There is no such Id in the Employee details & First 3 charcters should start with ACE and next four should be digits.");
                                }
                            }
                            return;
                        case 2:
                            while(true){
                                System.out.println("Enter EmailId you want to change ");
                                String oldemid=scan.next();
                                //updatemid();
                                if(present(oldemid)) {
                                	updateall(oldemid);
                                    break;
                                }
                                else{
                                    System.out.println("There is no such mail id and it should have proper domain name & it should not contain any special characters except @.");
                                }
                            }
                            return;
                        default:
                            System.out.println("The input should either be 1 or 2.");
                        }
                    }
//                }
//            else{
//                System.out.println("There is no records in the Employee Details, please enter the Details first:");
//            }
        
    }
    
        
    public static void start() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/aspire_db","root","Kavi@9321$21");
		PreparedStatement pstmt = null;
		Statement stmt=con.createStatement();
		while(true) {
			try {
				System.out.println("Enter your choice:");
				Thread.sleep(1000);
				System.out.println("1.Add");
				Thread.sleep(1000);
				System.out.println("2.Delete");
				Thread.sleep(1000);
				System.out.println("3.Display");
				Thread.sleep(1000);
				System.out.println("4.Update");
				Thread.sleep(1000);
				System.out.println("5.Exit");
				Thread.sleep(1000);
			}
			catch(InterruptedException excp){
	            System.out.println("My Thread interrupted");
	        }
//		System.out.println("Hi");
		int choice = choice();
		
//		System.out.println("Hello");
		switch(choice){
		case 1:
			add();
			String sql = "INSERT INTO emp(empid,name,dob,doj,emailid) VALUES (?,?,?,?,?)";
//			stmt.executeUpdate(sql);
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, getid());
            pstmt.setString(2, getname());
            pstmt.setString(3, getDob());
            pstmt.setString(4, getDoj());
            pstmt.setString(5, getEmailid());
            pstmt.executeUpdate();
			break;
		case 2:
			if(isEmpty()) {
				String delete;
				while(true) {
				System.out.println("Enter delete details:");
				delete=recordDelete();
				if(present(delete)){
					break;
				}
				System.out.println("No such element exits in the database");
			}
			String sql1="DELETE FROM emp WHERE empid=(?)";
			//PreparedStatement pstmts = null;
             pstmt = con.prepareStatement(sql1);
             pstmt.setString(1, delete);
             pstmt.executeUpdate();
			}
			else {
				System.out.println("There is no record in the database, so please enter the data");
			}
			break;
		case 3://display
			if(isEmpty()) {
				String query="SELECT * FROM emp";
				ResultSet rs1=stmt.executeQuery(query);
				System.out.println("Employee Full Details:");
				while(rs1.next()) {
					System.out.println(rs1.getString(1));
					System.out.println(rs1.getString(2));
					System.out.println(rs1.getString(3));
					System.out.println(rs1.getString(4));
					System.out.println(rs1.getString(5));
					System.out.println("----------------");
				}
			}
			else {
				System.out.println("Table is empty, so please add the values first.");
			}
			break;
		case 4:
			if(isEmpty()) {
				update();
			}
			else {
				System.out.println("Table is empty, so please add the values first.");
			}
			break;
		case 5:
			System.out.println("Thank you for using this application.");
			return;
		default:
			System.out.println("Enter values between 1-5.");
		}
		}
	}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
