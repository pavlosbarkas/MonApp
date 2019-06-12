import javax.swing.JOptionPane;

public class Card {
	
	private char type;//d:decision or o:order  && decision=community chest  && order=chance
	private String str;
	private int number;
	private int code;
	
	public Card(char type,String str,int number,int code)
	{
		this.type=type;
		this.str=str;
		this.number=number;
		this.code=code;
	}

	
	public Card() {
		// TODO Auto-generated constructor stub
	}


	public boolean executeTheCard(Player p,Board b)
	{
	
		if (type=='d')
		{
			if(code==0)
				p.setGetOutOfJail(true);
			else if(code==1)
				p.earnMoney(number);
			else if(code==2 || code==3)
			     p.changePosition(number);	
			else
			{	
				p.setPosition(0);
			    p.earnMoney(400); // double
			}
		}
		else
		{ 
			if (code==0)
			{
				int m = p.getMoney();  //player's money
				int n = 50 * (b.getPlayers().size() - 1);  //payment
				
				if (!p.isLoan())
					m = m + 1000;
				
				if (m >= n)
				{
				   for(Player x : b.getPlayers())
				   {
					   if (!p.getName().equals(x.getName()))
					   {
						   if(p.payMoney(number))
						        x.earnMoney(number);	   
					   }	
				   }
				   return true;
				}
				
				if (p.isLoan())
				    JOptionPane.showMessageDialog(null, "MONEY REQUIRED "
						+ n + "\nWHILE YOU HAVE " + m + ".",
                       "UPDATE", JOptionPane.ERROR_MESSAGE);
				else
					 JOptionPane.showMessageDialog(null, "MONEY REQUIRED "
								+ n + "\nWHILE YOU HAVE " + m 
								+ "\n(INCLUDING LOAN).",
		                       "UPDATE", JOptionPane.ERROR_MESSAGE);
				return false;
		
			}
			else if (code==1)
			{   
			    p.setPosition(10);
			    p.setInJail(true);
			}
			else
			{
				return p.payMoney(number);	
			}
		}
		return true;
		
	}

	public int getCode() {
		return code;
	}
	
	public char getType() {
		return type;
	}

	public String getStr() {
		return str;
	}


	public int getNumber() {
		return number;
	}
	
  

	
}
