import java.awt.Font;
import java.util.ArrayList;
import javax.swing.*;

public class Player {
	
	private int code;
	private String name;
	private int money;
	
	private ArrayList<PlotSquare> titles = new ArrayList<>();
	private ArrayList<StationSquare> stations = new ArrayList<>();
	private ArrayList<UtilitySquare> utilities = new ArrayList<>();
	
	private int position;
	private boolean getOutOfJail; //if the player has a get out of jail free card
	private boolean InJail;   //if the player is currently in jail
	private int jail_counter; //number of rounds a player is in jail
	private boolean loan; //if the player has used the loan option
	private boolean bankrupt;
	private JLabel label = new JLabel();
	
	public Player(int code,String name){  
		
		this.code=code;
		this.name=name;
		
		money=2000;
		position=0;
		getOutOfJail=false;
		loan=false;
		bankrupt=false;
		InJail = false;
		jail_counter = 0;
		label.setText(" ");	
		
	}
	
	public void earnMoney(int amount){
		
		money+=amount;
	
	}
	
	//Player pays the amount of money and in case his balance is not enough
	//he can use the loan option, in which case he earns 1000.
	public boolean payMoney(int amount){
		
		if (amount<=money){
			
			money-=amount;
			return true;
		}else if (amount>money){
			
			if(!loan){
				
				int i = JOptionPane.showConfirmDialog(null, "YOU DON'T HAVE ENOUGH MONEY. (" + money + ")\n"
						+ "DO YOU WANT TO TAKE A LOAN?", "UPDATE" , JOptionPane.YES_NO_OPTION);
				if (i==0){
					
					loan=true;
					earnMoney(1000);  
					JOptionPane.showMessageDialog(null, "AFTER THE LOAN YOUR MONEY IS " + money + "$.");
					return payMoney(amount);
				}else
					return false;
			}else{
				
				JOptionPane.showMessageDialog(null, "YOU DON'T HAVE ENOUGH MONEY" 
						+"\n AND YOU HAVE ALREADY TAKEN THE LOAN.");
				return false;
			
			}
		
		}
		
		return false;	
	
	}
	
	public boolean buyPlotSquare(int amount, PlotSquare s){
		
		if (payMoney(amount)){
			
			titles.add(s);
			return true;
		
		}
		
		return false;
	
	}
	
	public boolean buyStationSquare(int amount, StationSquare s){
		
		if (payMoney(amount)){
			
			stations.add(s);
			return true;
		
		}
		
		return false;
	
	}
	
	public boolean buyUtilitySquare(int amount, UtilitySquare s){
		
		if (payMoney(amount)){
			
			utilities.add(s);
			return true;
		
		}
		
		return false;
	
	}
	
	public boolean canBuildHouse(int gr, int num){
		
		int i=0;
		for (PlotSquare s : titles){
			
			if (s.getGroup() == gr)
				i++;
			if (i == num && !canBuildHotel(gr,num))
				return true;
		
		}
		
		return false;
			
	}
	
	public ArrayList<String> whereToBuildHouse(int gr,int num){
		
		ArrayList<PlotSquare> h = new ArrayList<>();
		for (PlotSquare s : titles){
			
			if (s.getGroup() == gr)
				h.add(s);
			if (h.size()==num)
				break;
		
		}
		
	    int min = 4;
		for (PlotSquare s : h){
			
			if (s.getCounter()<=min)
				min = s.getCounter();
		
		}
		
		ArrayList<String> str = new ArrayList<>();
		for (PlotSquare s : h){
			
			if (s.getCounter() == min)
				str.add(s.getName());
		}
		
		return str;
		
	}
	
	public boolean canBuildHotel(int gr,int num){
		
		int i=0;
		for (PlotSquare s : titles){
			
			if (s.getGroup() == gr && s.getCounter() == 4)
				i++;
			if (i == num)
				return true;
		
		}
		
		return false;
	
	}
	
	public ArrayList<String> whereToBuildHotel(int gr,int num){
		
		ArrayList<String> str = new ArrayList<>();
		
		for(PlotSquare s : titles){
			
			if (s.getGroup() == gr && !s.isHotel_built())
				str.add(s.getName());
		
		}
		
		return str;
	
	}
	
	public int calculateStationRent(){
		
		return (int) (25 * (Math.pow(2,stations.size()-1 )));
	
	}
	
	public int calculateUtilitiesRent(){
		
		if(utilities.size()==1)
			return 4;
		return 10;
	
	}
	
	//Changes the position of the player. If he passes go then he receives 200$.
	public void changePosition(int p){
		
		if(InJail == false){
			
			int move = position + p;
		    if(move > 39){
		    	
		    	position = move % 40;
		    	if (position==0)
		    		JOptionPane.showMessageDialog(null, "YOU ARE ON GO(200$)", "UPDATE", JOptionPane.INFORMATION_MESSAGE);
		    	else
		    		JOptionPane.showMessageDialog(null ,"YOU PASSED GO.\n" + "YOU EARNED 200$." , "UPDATE" ,  JOptionPane.INFORMATION_MESSAGE);
		    	earnMoney(200);
		    }else if (move < 0)
		    	position = 40 + move;
		    else
		    	position = move;
		}			
	}
	
	public String returnPlotSquares(){
		
		String t = "\n";
		for(PlotSquare plot : titles){
			
			 t = t + "     " + plot.getName() + "\n";
		
		}
		
		return t;
	
	}
	
	public String returnStationSquares(){
		
		String s = "\n";
		for(StationSquare station : stations){
			
			s = s + "     " + station.getName() + "\n";
		}
		
		return s;
	
	}
	
	public String returnUtilitySquares(){
		
		String u = "\n";
		for(UtilitySquare util : utilities){
			
			u = u + "     " + util.getName() + "\n";
		}
		
		
		return u;
	
	}
	
	public boolean jailCounter(){
		
		jail_counter++;
		if (jail_counter<=2)
			return true;
		return false;
	}
	
	//getters and setters
	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public int getMoney() {
		return money;
	}
	
	public boolean isGetOutOfJail() {
		return getOutOfJail;
	}

	public void setGetOutOfJail(boolean getOutOfJail) {
		this.getOutOfJail = getOutOfJail;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getPosition() {
		return position;
	}
	
	public JLabel getLabel() {
		return label;
	}
	
	public void setLabel(JLabel label) {
	
		this.label = label;
		this.label.setFont(new Font("Arial",Font.BOLD,15));
		this.label.setText(name);
		this.label.setHorizontalTextPosition(JLabel.CENTER);
		this.label.setVerticalTextPosition(JLabel.BOTTOM);
		
	}
	
	public ArrayList<PlotSquare> getTitles() {
		return titles;
	}
	
	public ArrayList<StationSquare> getStations() {
		return stations;
	}
	
	public ArrayList<UtilitySquare> getUtilities() {
		return utilities;
	}
	
	public boolean isLoan() {
		return loan;
	}
	
	public boolean isInJail() {
		return InJail;
	}
	
	public void setInJail(boolean inJail) {
		InJail = inJail;
	}
	
	public int getJail_counter() {
		return jail_counter;
	}
	
	public void setJail_counter(int jail_counter) {
		this.jail_counter = jail_counter;
	}
	
	public boolean isBankrupt() {
		return bankrupt;
	}
	
	public void setBankrupt(boolean bankrupt) {
		this.bankrupt = bankrupt;
	}

}
