import javax.swing.JLabel;

public class TaxSquare extends Square {

	private int tax;
	
	public TaxSquare(String n,String name, JLabel label, int tax) {
		super(n,name,label);
		this.tax=tax;
	}
	
	public boolean payTax(Player p)
	{
		return p.payMoney(tax);
	}
	
	public int getTax() {
		return tax;
	}

}
