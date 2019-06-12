import javax.swing.JOptionPane;

public class Card {
	
	private int type; //community chest = 0   && chance = 1
	private String str;
	private int number;
	private int code;
	
	public Card(int type,String str,int number,int code){
		
		this.type=type;
		this.str=str;
		this.number=number;
		this.code=code;
		
	}
	
	public Card() {
		
	}

	//According to the type of card, the necessary actions are taken using this method.
	public boolean executeTheCard(Player p,Board b){
	
		if (type == 0){
			
			if(code==0)
				p.setGetOutOfJail(true);
			else if(code==1)
				p.earnMoney(number);
			else if(code==2 || code==3)
				p.changePosition(number);	
			else{	
				p.setPosition(0);
			    p.earnMoney(200); 
			}
		}else{ 
			if (code==0){
				
				int m = p.getMoney();  
				int n = 0 ;
				
				for (Player x : b.getPlayers()){
					if (!x.isBankrupt())
						n++;
				}
				n*=50;
				
				if (!p.isLoan())
					m = m + 1000;
				
				if (m >= n){
					
				   for(Player x : b.getPlayers()){
					   
					   if (!p.getName().equals(x.getName())){
						   
						   p.payMoney(number);
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
			}else if (code==1){
				
			    p.setPosition(10);
			    p.setInJail(true);
			
			}else
				return p.payMoney(number);	
			
		}
		return true;
		
	}

	//getters
	public int getCode() {
		return code;
	}
	
	public int getType() {
		return type;
	}

	public String getStr() {
		return str;
	}

	public int getNumber() {
		return number;
	}

}
