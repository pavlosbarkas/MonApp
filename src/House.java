
public class House {
	
	private int house_rent1;
	private int house_rent2;
	private int house_rent3;
	private int house_rent4;
	private int house_cost;
	
	public House(int house_rent1,int house_rent2,int house_rent3,int house_rent4,int house_cost){
		
		this.house_rent1=house_rent1;
		this.house_rent2=house_rent2;
		this.house_rent3=house_rent3;
		this.house_rent4=house_rent4;
		this.house_cost=house_cost;
	
	}

	public int getHouse_rent1() {
		return house_rent1;
	}

	public int getHouse_rent2() {
		return house_rent2;
	}

	public int getHouse_rent3() {
		return house_rent3;
	}

	public int getHouse_rent4() {
		return house_rent4;
	}

	public int getHouse_cost() {
		return house_cost;
	}

}
