import javax.swing.JLabel;

public class PlotSquare extends Square{
	
	private House house;
	private Hotel hotel;
	private boolean hotel_built;
	private int counter; //counts number of houses
	private int group; //the group the plot belongs to
	private int num; //how many plots are in the group
	
	public PlotSquare(String n,JLabel label,String name,int price,int rent,House house,Hotel hotel,int group,int num){
		
		super(n,label,name,price,rent);
		
		this.hotel=hotel;
		this.house=house;
		this.group=group;
		this.num=num;
		hotel_built=false;
		counter=0;
	
	}

	//Adds a house in the specified plotsquare.
	public void buildHouse(){
		
		counter++;
	
	}

	public String showInfo(){
		
		return "Name : " + super.getName() + "\nPrice : " + super.getPrice() + "\nRent : " + super.getRent()
		+ "\nWith 1 House : " + house.getHouse_rent1() + "\nWith 2 Houses : " + house.getHouse_rent2()
		+  "\nWith 3 Houses : " + house.getHouse_rent3() +  "\nWith 4 Houses : " + house.getHouse_rent4()
		+ "\nWith Hotel : " + hotel.getHotel_rent() + "\nHouses cost " + house.getHouse_cost() + " each"
		+ "\nHotel costs : " + hotel.getHotel_cost() + "\nNumber of houses build : " + counter 
		+ "\n\nIf a player owns ALL the lots \nof any color group the rent \nis doubled on unimproved lots \nin that group.";
	
	}
	
    //getters and setters
	public House getHouse() {
		return house;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public int getGroup() {
		return group;
	}

	public int getCounter() {
		return counter;
	}

	public boolean isHotel_built() {
		return hotel_built;
	}

	public void setHotel_built(boolean hotel_built) {
		this.hotel_built = hotel_built;
	}

}
