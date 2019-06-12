import javax.swing.JLabel;

public class UtilitySquare extends Square{

	public UtilitySquare(String n, JLabel label,String name){
		super(n, label,name,150);
	}
	
	public String showInfo(){
		
		return "Name : " + super.getName() + "\nPrice : " + super.getPrice() + "\nRent : (Defined by the dice)" 
				+ "\n\nIf one utility is owned,\nrent is 4 times amount \nshown on dice." + "\n\n"
				+ "If both utilities are owned,\nrent is 10 times amount \nshown on dice.";
	
	}
	
}
