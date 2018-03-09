package new_LMS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class DAO {
	static Statement st;
	static ResultSet rs;
	static ResultSet rs1;
	static Connection  con;
	static int check_id;
	private int leaveCount;
	public Statement connection() throws Exception{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
//		Class.forName("com.mysql.jdbc.Driver");
//		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
		st = con.createStatement();
		return st;
	}
	public boolean isValid(int id,String passWord) throws Exception{
		boolean flag = false;
		Statement st1 = connection();
		rs=st1.executeQuery("select * from EmpLMS where id="+id+" and password ="+"'"+passWord+"'");
		while(rs.next()){
			check_id = rs.getInt(1);
			if(check_id == id){
				flag = true;
			}
		}
		return flag;
	}
	public void displayLeaveDetails(int id) throws Exception{
		Statement st2 = connection();
		rs1 = st2.executeQuery("select leave_balance from EmpLMS where id = "+id);
		while(rs1.next()){
			// 4th field in DB should be of leave balance
			System.out.println("your balance leaves are : "+rs1.getInt(1));
		}
		
	}
	public boolean checkLeaveAvailability(int id) throws Exception{
		boolean isAvailable = false;
		//rs1 has leave count so we can use it directly
		leaveCount = rs1.getInt(4);
		if(leaveCount>0)
		{
			isAvailable = true;
		}
		else{
			System.out.println("you dont have leaves....");
		}
		return isAvailable;
	}
	public void grantLeave(int id, int applied_leaves) throws Exception{
		if(applied_leaves>leaveCount){
			System.out.println("you dont have enough leaves....\nYou have only "+leaveCount+" leaves");
		}
		else{
			System.out.println("your leave is approved");
			updateLeaveCount(id,applied_leaves);
		}
		
	}
	private void updateLeaveCount(int id, int applied_leaves) throws Exception{
		int leave_balance = leaveCount - applied_leaves;
		Statement st4 = connection();
		ResultSet rs4 = st4.executeQuery("UPDATE EmpLMS SET leave_balance = "+leave_balance+" WHERE id = "+id);
		
	}
	public void enterDataInTable(List<Employee> employee) throws Exception{
		//Statement st2 = connection();
		boolean flag = false;
		if(!flag){
			createTable();
			flag = true;
		}
		if(flag){
		for (int i = 0; i < employee.size(); i++) {
			PreparedStatement stmt = con.prepareStatement("insert into EmpLMS values(?,?,?,?)");
			 stmt.setInt(1, employee.get(i).getId()); 
			 stmt.setString(2, employee.get(i).getName());
			 stmt.setString(3, employee.get(i).getPassword());
			 stmt.setInt(4, employee.get(i).getLeaveBalance());
			 stmt.executeUpdate();
			}
	}
	}
	private void createTable() throws Exception{
		Statement st5 = connection();
//		st5.executeQuery("CREATE TABLE EmpKrtk( id NUMBER(10) PRIMARY KEY, name  VARCHAR2(15) NOT NULL, password  VARCHAR2(10),leave_balance  NUMBER(9))");
		st5.executeUpdate("CREATE TABLE EmpLMS( id NUMBER(10) PRIMARY KEY, name  VARCHAR2(15) NOT NULL, password  VARCHAR2(10),leave_balance NUMBER(10))");
	}

}
