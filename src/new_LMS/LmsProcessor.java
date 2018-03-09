package new_LMS;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LmsProcessor {
	FileReader fr;
	BufferedReader br;
	DAO dao = new DAO();
	List<Employee> employee = new ArrayList<>();
	
private boolean Status = false;
	public boolean validate(int loginId, String password) throws Exception{
		Status = dao.isValid(loginId,password);
		return Status;
	}
	public void showMenu(int loginId) throws Exception{
		System.out.println("1.display leave details");
		System.out.println("2.request leave");
		System.out.println("3.exit");
		Scanner choice = new Scanner(System.in);
		int ch = choice.nextInt();
		performOperation(ch,loginId);
	}
	
	private void performOperation(int ch,int id) throws Exception{
		switch(ch){
		case 1:    
			    dao.displayLeaveDetails(id);
	    break;  
		case 2:    
		    request_leave(id);
		 break;
		case 3:
			
			break;
		}
		
	}
	private void request_leave(int id) throws Exception{
		boolean isLeaveAvailable = dao.checkLeaveAvailability(id);
		if(isLeaveAvailable){
			System.out.println("how many leaves you want...???");
			Scanner sc = new Scanner(System.in);
			int applied_leaves = sc.nextInt();
			dao.grantLeave(id,applied_leaves);
		}
	}
	public void getEmployeeArray() throws Exception{
		String str=null;
		fr = new FileReader("D:\\kartik\\LMSbeta\\new_LMS\\src\\employeeDetails.csv");
		br = new BufferedReader(fr);
		
		while ((str = br.readLine()) != null) {
			String[] data = str.split(",");
			Employee emp = new Employee();
			emp.setId(Integer.valueOf(data[0]));
			emp.setName(data[1]);
			emp.setPassword(data[2]);
			emp.setLeaveBalance(Integer.valueOf(data[3]));
			employee.add(emp);
		}
		//return employee;
		dao.enterDataInTable(employee);
		
	}

}
