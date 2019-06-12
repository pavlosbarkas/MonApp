import javax.swing.JLabel;

public class PlotSquare extends Square{
	
	private House house;
	private Hotel hotel;
	private boolean hotel_built;
	private int counter; //counts number of house
	private int group; //which group belongs to
	private int num; //how many are in a group

	
	public PlotSquare(String n,JLabel label,String name,int price,int rent,House house,Hotel hotel,int group,int num)
	{
		super(n,label,name,price,rent);
		
		counter=0;
		
		this.hotel=hotel;
		this.house=house;
		this.group=group;
		this.num=num;
		hotel_built=false;
		
	}

	public void buildHouse()
	{
		counter++;
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
