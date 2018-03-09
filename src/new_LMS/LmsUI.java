package new_LMS;

import java.util.Scanner;

public class LmsUI {
	//static boolean chk=false;
private boolean valid = false;
private boolean isConnectionGot = false;
LmsProcessor lp = new LmsProcessor();
	private int loginId;
	public boolean login() throws Exception{
	System.out.println("please enter your id and password");
	Scanner getId = new Scanner(System.in);
	loginId = getId.nextInt();
	Scanner getPassword = new Scanner(System.in);
	String password = getPassword.nextLine();
	isConnectionGot = 	lp.validate(loginId,password);
	if(isConnectionGot){
	valid = true;
	}
	else{
		System.out.println("wrong credentials...");
	}
	return valid;	
	}

	public void display() throws Exception{
	boolean valid =	login();
	if(valid){
		lp.showMenu(loginId);
	}
		
	}

	public void requent_leave() {
		// TODO Auto-generated method stub
		
	}

	public void uploadfile() throws Exception{

		lp.getEmployeeArray();

	
		
}}
