import javax.swing.JLabel;

public class StationSquare extends Square{
	
	public StationSquare(String n, JLabel label,String name){
		
		super(n, label,name,200,25);
	
	}
	
	public String showInfo(){
		
		return "Name : " + super.getName() + "\nPrice : " + super.getPrice() + "\nRent : " + super.getRent()
		+ "\nIf two Railroads are owned : 50 \nIf three Railroads are owned : 100 \nIf four Railroads are owned : 200 ";
	
	}

}
