import javax.swing.JLabel;

public class Square {
	
	private String n;
	private String name;
	private JLabel label;

	private int price;
	private int rent;
	
	Player p;
	
	public Square(String n,String name,JLabel label) //special squares + tax square + card square
	{
		this.n=n;
		this.name=name;
		this.label=label;	
	}
	
	public Square(String n,JLabel label, String name,int price,int rent) //plot + station
	{
		this.n=n;
		this.label=label;
		this.name=name;
		this.price=price;
		this.rent=rent;
		this.p=null;
		
	}

	public Square(String n,JLabel label, String name,int price) //utility
	{
		this.n=n;
		this.label=label;
		this.name=name;
		this.price=price;
		this.p=null;
	}
	
	public void squareBought(Player x)
	{
		p=x;	
	}
	
	//getters and setters
	public String getN() {
		return n;
	}

	public JLabel getLabel() {
		return label;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}

	public int getRent() {
		return rent;
	}

	public Player getP() {
		return p;
	}

	public void setP(Player p) {
		this.p = p;
	}

	public void setRent(int rent) {
		this.rent = rent;
	}

}
