package new_LMS;

public class Employee {
int id = 0;
String name = null;
String password = null;
int leave_balance = 0;

public Employee() {
	super();
}

public Employee(int id, String name, String password, int leave_balance) {
	
	this.id = id;
	this.name = name;
	this.password = password;
	this.leave_balance = leave_balance;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public int getLeaveBalance() {
	return leave_balance;
}
public void setLeaveBalance(int leaveBalance) {
	this.leave_balance = leave_balance;
}


}
