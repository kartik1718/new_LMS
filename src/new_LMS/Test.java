package new_LMS;

public class Test {
	//static boolean chk=false;
public static void main(String[] args) throws Exception{
	LmsUI ui= new LmsUI();
//	ui.login();
	
	
	//if(chk==false){
	ui.uploadfile();
	//chk=true;}
	ui.display();
	ui.requent_leave();
}
}
