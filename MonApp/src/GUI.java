import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class GUI extends JFrame {
	
	private JPanel p;
	private Board board = new Board();
	
	private JButton b[] = new JButton[40];
	private GridBagConstraints c[] = new GridBagConstraints[40];
	
	private JButton decision;
	private GridBagConstraints dc = new GridBagConstraints();
	
	private JButton order;
	private GridBagConstraints oc = new GridBagConstraints();
	
	private JTextArea info;
	private GridBagConstraints ic = new GridBagConstraints();
	
	private JButton dice_1;
	private GridBagConstraints d1c = new GridBagConstraints();
	
	private JButton dice_2;
	private GridBagConstraints d2c = new GridBagConstraints();
	
	private JButton hotel;
	private GridBagConstraints hotelc = new GridBagConstraints();
	
	private JButton house;
	private GridBagConstraints housec = new GridBagConstraints();
	
	private int rows; //button layout
	private int cols;
	
	private int i,j;
	
	JLabel choice1  = new JLabel();
	JLabel choice2  = new JLabel();
	JLabel choice3  = new JLabel();
	JLabel choice4  = new JLabel();
	JLabel l = new JLabel();
	private int hat_taken = 0; //player icons
	private int shoe_taken = 0;
	private int car_taken = 0;
	private int ship_taken = 0;
	
	GridBagConstraints hc = new GridBagConstraints();
	
	Player curr_pl;
	
	boolean flag1 = false;
	boolean flag2 = false;
	
	int counter = 0; // counts the number of equal dice
	
	rollDiceButtonListener listener = new rollDiceButtonListener();
	OrderOrDecisionButtonListener card_listener = new OrderOrDecisionButtonListener();
	
	int jail_choice = -1;
	
	public GUI(Board board)
	{
		
		this.board=board;
		
		Frame();
		
	}
	
	public void Frame()
	{
		p=new JPanel();
		this.setContentPane(p);
		p.setBackground(Color.white);
		
		
		JButton start = new JButton("START");
		start.setFont(new Font("Arial", Font.BOLD, 35 ));
		start.setBackground(new Color(222,222,222));
		
		Image welcome_label = new ImageIcon(this.getClass().getResource("/hello.png")).getImage();
		JLabel welcome = new JLabel(new ImageIcon(welcome_label));
		
		p.add(welcome);
		p.add(start);
		
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getThePlayers();		
			}});
		
		Toolkit toolkit = getToolkit();
		Dimension size = toolkit.getScreenSize();
		this.setLocation(size.width/3 - getWidth()/3, size.height/3 - getHeight()/3);
		this.setTitle("WELCOME TO MONOPOLY");
		this.setSize(400, 250);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public boolean getNumberofPlayers()
	{
        
		int n=0;
	    String answer;
	    char a;
	    
		do{
			do{
		    answer = JOptionPane.showInputDialog("Insert number of players (2-4): ");
		    if (answer==null || answer.equals(""))   
		        break; 
		    a = answer.charAt(0);
			}while (answer.length() > 1  || (a<50 || a>52) );
			
			if (answer==null || answer.equals(""))
		        break;
			n = Integer.parseInt(answer);
		}while(n<2|| n>4) ;
		if (answer!=null && !answer.equals(""))
		    {
			   board.setN(n);
			   return true;
		    }
		return false;
		
	}
	
	public boolean getPlayerName()
	{
		j=1;
		boolean flag;
		for(i=0;i<board.getN();i++)
		{
			flag=false;
			String answer = null;
			
			do{
			answer = JOptionPane.showInputDialog("Player " + j + " give your name : ");
			if (answer!= null)
			{
				if (answer.equals(""))
				    JOptionPane.showMessageDialog(null, "You can't leave this field empty." , "ERROR" , JOptionPane.ERROR_MESSAGE);
			    else if (checkNicknames(answer))
			    {
				flag=true;
				Player player = new Player(i,answer);
				board.getPlayers().add(player);
				j++;
			    }
			    else
			         JOptionPane.showMessageDialog(null,"Invalid nickname. Please choose a different nickname." ,
			    		"ERROR" ,JOptionPane.ERROR_MESSAGE );
			}
			else
			{
				int k = JOptionPane.showConfirmDialog(null, "Do you want to close the game?" , "INFO" , JOptionPane.YES_NO_OPTION);
				if (k ==0)
			    {
					Frame();
					return false;
			    }
			}
			}while (flag==false);
			
		}
		
		return true;
	}
	
	public boolean checkNicknames(String name)
	{
		for(Player p : board.getPlayers())
			if (p.getName().equals(name))
				return false;
		return true;
	}
	
	public void getPlayerIcon()
	{
		Image hat = new ImageIcon(this.getClass().getResource("/hat.png")).getImage();
		Image hat_b = new ImageIcon(this.getClass().getResource("/hat_big.png")).getImage();
		
		Image shoe = new ImageIcon(this.getClass().getResource("/shoe.png")).getImage();
		Image shoe_b = new ImageIcon(this.getClass().getResource("/shoe_big.png")).getImage();
		
		Image car = new ImageIcon(this.getClass().getResource("/car.png")).getImage();
		Image car_b = new ImageIcon(this.getClass().getResource("/car_big.png")).getImage();
		
		Image ship = new ImageIcon(this.getClass().getResource("/ship.png")).getImage();
		Image ship_b = new ImageIcon(this.getClass().getResource("/ship_big.png")).getImage();
		
		JButton l1 = new JButton(new ImageIcon(ship_b));
		JButton l2 = new JButton(new ImageIcon(car_b));
	    JButton l3 = new JButton(new ImageIcon(shoe_b));
		JButton l4 = new JButton(new ImageIcon(hat_b));
		
		l.setText("Player: " + board.getPlayers().get(0).getName() + " choose piece");
			
	    JPanel p1 = new JPanel();
	    JPanel p2 = new JPanel();
	    
	    p1.setBackground(Color.white);
	    p2.setBackground(Color.white);
	        
	    p = new JPanel();
		this.setContentPane(p);
		p.setLayout(new GridLayout(2,1));
			
		l.setFont(new Font("Arial" , Font.PLAIN , 35));
		l1.setBackground(new Color(232,232,232));
		l2.setBackground(new Color(232,232,232));
		l3.setBackground(new Color(232,232,232));
		l4.setBackground(new Color(232,232,232));
		
		p.add(p1);
		p.add(p2);
			
		p1.add(l);
		p2.add(l1);
		p2.add(l2);
		p2.add(l3);
		p2.add(l4);
		p2.setLayout(new GridLayout(1,4,10,40));
			
		this.setSize(700,225);
		this.setVisible(true);
		    
			//icon for each player
			l1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(ship_taken==0)
					{   
						choice1.setIcon(new ImageIcon(ship));
						ship_taken=1;
						choosePawn(choice1);
					}
					else
						JOptionPane.showMessageDialog(null, "ALREADY CHOSEN.");
					}});
			
			l2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(car_taken==0)
					{   
						choice2.setIcon(new ImageIcon(car));
						car_taken=1;
						choosePawn(choice2);
					}
					else
						JOptionPane.showMessageDialog(null, "ALREADY CHOSEN.");
					}});
			
			l3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(shoe_taken==0)
					{
						choice3.setIcon(new ImageIcon(shoe));
						shoe_taken=1;
						choosePawn(choice3);
		             }
					else
						JOptionPane.showMessageDialog(null, "ALREADY CHOSEN.");
					}});
			
			l4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(hat_taken==0)
					{
						choice4.setIcon(new ImageIcon(hat));
						hat_taken=1;
						choosePawn(choice4);
					}
					else
						JOptionPane.showMessageDialog(null, "ALREADY CHOSEN.");
					}});
	}
	
	public void choosePawn(JLabel label)
	{
		i=0;
		for(Player x : board.getPlayers())
		{
			i++;
			if(x.getLabel().getText().equals(" "))
			{
				x.setLabel(label);
				
				if(x.getCode()== board.getN() - 1)
					BuildBoard();
				
				else if(board.getPlayers().get(i)!= null)
					l.setText("Player: "  + board.getPlayers().get(i).getName()+  " choose piece");

				else
					l.setText("Player: "  + board.getPlayers().get(board.getN()-1).getName()+  " choose piece");
					
			    break;
			}
		}
	}
	
	public void getThePlayers()
	{
		if (getNumberofPlayers() && getPlayerName())
			getPlayerIcon(); 	
	}	
	
	public void layoutOfButtons() 
	{
		if (board.getN()==2 || board.getN()==3)
		{
			rows=1;
			cols=board.getN();
		}
		else
		{
			rows=2;
			cols=2;
		}	
	}
	
	public void BuildBoard()
	{
		p=new JPanel();
		p.setLayout(new GridBagLayout());
		this.setContentPane(p);
		
		layoutOfButtons();
	
		for(i=0;i<=39;i++)
		{
			b[i] = new JButton();
			b[i].setLayout(new GridLayout(rows,cols));
			b[i].setText("" + i);
			b[i].setPreferredSize(new Dimension(60,60));
			c[i] = new GridBagConstraints();	
		}
		
		//creating the board
		j=10;
		for(i=0;i<=10;i++)
		{  c[i].fill=GridBagConstraints.BOTH;
           c[i].weightx= 0.5;
           c[i].weighty= 0.5;
   		   c[i].gridx=j;
   		   c[i].gridy=10;
   		   c[i].ipady=50;
   		   c[i].ipadx=90;
   		   p.add(b[i],c[i]);
   		   j--;
		}
		
		j=0;
		for(i=20;i>=11;i--)
		{  
		   c[i].fill= GridBagConstraints.BOTH;
		   c[i].weightx= 0.5;
           c[i].weighty= 0.5;
   		   c[i].gridx=0;
   		   c[i].gridy=j;
   		   c[i].ipady=50;
   		   c[i].ipadx=90;
   		   p.add(b[i],c[i]);
   		   j++;
		}
		
		j=1;
		for(i=21;i<=30;i++)
		{  c[i].fill=GridBagConstraints.BOTH;
		   c[i].weightx= 0.5;
           c[i].weighty= 0.5;
   		   c[i].gridx=j;
   		   c[i].gridy=0;
   		   c[i].ipady=50;
 		   c[i].ipadx=90;
   		   p.add(b[i],c[i]);
   		   j++;
		}
		
		j=1;
		for(i=31;i<=39;i++)
		{  c[i].fill=GridBagConstraints.BOTH;
		   c[i].weightx= 0.5;
           c[i].weighty= 0.5;
   		   c[i].gridx=10;
   		   c[i].gridy=j;
   		   c[i].ipady=50;
		   c[i].ipadx=90;
   		   p.add(b[i],c[i]);
   		   j++;
		}
		
		//adding buttons
		decision = new JButton("Community Chest");
		decision.setFont(new Font("Serif",Font.BOLD + Font.ITALIC,28));
		decision.setBackground(new Color(253,157,48));
		dc.gridx=2;
	    dc.gridy=2;
	    dc.ipadx=20;
	    dc.ipady=40;
	    decision.setEnabled(false);
	    p.add(decision,dc);
	    
	    order = new JButton("Chance");
	    order.setFont(new Font("Serif",Font.BOLD + Font.ITALIC,28));
	    order.setBackground(new Color(60,203,246));
		oc.gridx=8;
	    oc.gridy=8;
	    oc.ipadx=45;
	    oc.ipady=35;
	    order.setEnabled(false);
	    p.add(order,oc);
	    
	    info = new JTextArea("PLAYER'S INFO  \n" + "Name : \n" + "Money : \n" + "Titles : \n" + "Stations : \n" + "Utilities : \n" , 10,4);
	    info.setEditable(false);
	    ic.fill = GridBagConstraints.BOTH;
		ic.gridx=5;
	    ic.gridy=4;
	    ic.gridheight=2;
	    ic.gridwidth=2;
	    p.add(info,ic);
	    
	    dice_1= new JButton("Dice 1");
	    dice_1.addActionListener(listener);
	    dice_1.setPreferredSize(new Dimension(60,20));
	    dice_1.setBackground(new Color(247,239,230));
	    dice_1.setFont(new Font("Arial",Font.BOLD,12));
	    d1c.ipadx=10;
	    d1c.ipady=50;
		d1c.gridx=5;
	    d1c.gridy=2;
	    p.add(dice_1,d1c);
	    
	    dice_2= new JButton("Dice 2");
	    dice_2.addActionListener(listener);
	    dice_2.setPreferredSize(new Dimension(60,20));
	    dice_2.setBackground(new Color(247,239,230));
	    dice_2.setFont(new Font("Arial",Font.BOLD,12));
	    d2c.ipadx=10;
	    d2c.ipady=50;
		d2c.gridx=6;
	    d2c.gridy=2;
	    p.add(dice_2,d2c);
	    
	    house = new JButton("Build \n" + "house");
	    house.setFont(new Font("Arial",Font.BOLD,14));
	    house.setBackground(new Color(250,67,67));
	    house.setEnabled(false);
		housec.gridx=2;
	    housec.gridy=5;
	    housec.ipadx=30;
	    housec.ipady=20;
	    housec.anchor= GridBagConstraints.NORTH;
	    p.add(house,housec);
	    
	    hotel = new JButton("Build \n" + "hotel");
	    hotel.setFont(new Font("Arial",Font.BOLD,14));
	    hotel.setBackground(new Color(250,255,67));
	    hotel.setEnabled(false);
	    hotelc.gridx=2;
	    hotelc.gridy=6;
	    hotelc.ipadx=40;
	    hotelc.ipady=20;
	    hotelc.anchor=GridBagConstraints.SOUTH;
	    p.add(hotel,hotelc);
	  
		this.setVisible(true);
		this.setSize(1500,900);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		makeSquares();
		createPawns();
		curr_pl = board.getCurr_pl();
		
		PlayTheGame();
	}

	public void makeSquares()
	{
	  board.makeTheBoard();
	  board.makeTheCards();
	
	  for(Square s : board.getSquares())
	  {
		  for (i=0;i<40;i++)
		      if (b[i].getText().equals(s.getN()))
			      b[i].setIcon(s.getLabel().getIcon());
	  }	
	}
	
	public void createPawns()
	{
		for(Player x : board.getPlayers())
		    b[0].add(x.getLabel());
	}
	
	class rollDiceButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
		
		if(e.getSource().equals(dice_1))
		{
			board.getDice().throwDice();
			String url1;
			if (board.getDice().getDice1()== 1)
				 url1 = "/1.png" ;
			else if (board.getDice().getDice1()== 2)
				 url1 = "/2.png" ;
			else if (board.getDice().getDice1()== 3)
				 url1 = "/3.png" ;
			else if (board.getDice().getDice1()== 4)
				 url1 = "/4.png" ;
			else if (board.getDice().getDice1()== 5)
				 url1 = "/5.png" ;
		    else 
		    	 url1 = "/6.png" ;
			
			Image im1 = new ImageIcon(this.getClass().getResource(url1)).getImage();
            dice_1.setIcon(new ImageIcon(im1));
	        dice_1.setFont(new Font("Arial",Font.PLAIN,0)); 
	        dice_1.setEnabled(false);
	        flag1 = true;    
		}
		
		else if (e.getSource().equals(dice_2))
		{
			String url2;
			if (board.getDice().getDice2()== 1)
				url2 = "/1.png" ;
			else if (board.getDice().getDice2()== 2)
				url2 = "/2.png" ;
			else if (board.getDice().getDice2()== 3)
				url2 = "/3.png" ;
			else if (board.getDice().getDice2()== 4)
				url2 = "/4.png" ;
			else if (board.getDice().getDice2()== 5)
				url2 = "/5.png" ;
		    else 
		    	url2 = "/6.png" ;
			
			Image im2 = new ImageIcon(this.getClass().getResource(url2)).getImage();
            dice_2.setIcon(new ImageIcon(im2));
	        dice_2.setFont(new Font("Arial",Font.PLAIN,0));
	        dice_2.setEnabled(false);
	        flag2 = true;  
		}
		
		if (flag1==true && flag2==true)
			calculateMove();
	      
		}
     }
	
    public void resetDice()
    {
	    flag1=false;
		flag2=false;
		dice_1.setEnabled(true);
		dice_2.setEnabled(true);
	}
    
    public void updateInfo()
	{
		info.setText("PLAYER'S INFO  \n" + "Name : " + curr_pl.getName() + 
				  "\n" + "Money : " + curr_pl.getMoney() + "\n" + "Titles : " + curr_pl.returnPlotSquares()
				  + "Stations : " + curr_pl.returnStationSquares()  
				  + "Utilities : " + curr_pl.returnUtilitySquares() );
		
		if (curr_pl.isGetOutOfJail())
			info.append("YOU HAVE A GET OUT OF JAIL CARD. \n");
		if (curr_pl.isLoan())
			info.append("YOU HAVE ALREADY TAKEN A LOAN. \n");
		
	}
    
    public void removePawn()
	{
		b[curr_pl.getPosition()].remove(curr_pl.getLabel());
		b[curr_pl.getPosition()].updateUI();
	}
	
	public void placePawn()
	{
		b[curr_pl.getPosition()].add(curr_pl.getLabel()); 
	    b[curr_pl.getPosition()].updateUI();
	}

	public void PlayTheGame()
	{
		if (checkForWinner()>1)
		 {
			 updateInfo();
			 if (!curr_pl.isInJail())
			 {
				if (flag1==true && flag2==true)
				       resetDice();
			 }
			 else
		     {
				 resetDice();
				 dealWithJail();
		     }	    	  
		 }
		 else
		 {
			 JOptionPane.showMessageDialog(null, "END OF GAME \nWINNER : " + announceWinner().getName());
			 dice_1.setEnabled(false);
			 dice_2.setEnabled(false);	 
		 }
	   
	}
	
	public int checkForWinner()
	{
		i=0;
		for (Player p : board.getPlayers())
		{
			if (p.isBankrupt()==false)
				i++;
		}
		return i;
	}
	
	public Player announceWinner()
	{
		for (Player p : board.getPlayers())
		{
			if (p.isBankrupt()==false)
				return p;
		}
	    return null;
	}
	
	public Square findTheSquare(Player player)
	{
		for (Square s : board.getSquares())
		{
			int n = Integer.parseInt(s.getN());
			if (n == player.getPosition())
				return s;
		}
		System.out.println("Error");
		return null;
	}
	
	public void makeTheMove()
	{
		 int move = board.getDice().getDice1() + board.getDice().getDice2() ;
		 removePawn();
		 curr_pl.changePosition(move);
		 placePawn();
		 
		 Square s = findTheSquare(curr_pl);	
	     playSquare(s);	
	}
	
	public void calculateMove()
	{	
		if (!curr_pl.isInJail())
		{
		   makeTheMove();
		}
		else
		{
			if (jail_choice==0)  //the player throws the dice to get out of jail
			{ 
				if (board.getDice().getDice1() == board.getDice().getDice2())
				{
					 curr_pl.jailCounter();
				     JOptionPane.showMessageDialog(null, "YOU CAN GET OUT OF JAIL(DOUBLE DICE).");
				     
				     curr_pl.setInJail(false);
				     makeTheMove();   
			    }
			    else
			    {   
				     JOptionPane.showMessageDialog(null, "YOU REMAIN IN JAIL");
				        
				     if(!curr_pl.jailCounter())
				     {
				    	 JOptionPane.showMessageDialog(null,"IT'S YOUR THIRD ROUND IN JAIL.n"
				    			 + "YOU MUST PAY THE FINE AND ROLL THE DICE.");
				    	 if (curr_pl.payMoney(100))
					     {
						     JOptionPane.showMessageDialog(null,"THE FINE HAS BEEN PAID. ROLL THE DICE");
						     curr_pl.setInJail(false);
							 updateInfo();
							 PlayTheGame();
						 }
						 else 
						 {
						     JOptionPane.showMessageDialog(null,"YOU DON'T HAVE ENOUGH MONEY. \n"
						     	 		+ "YOU ARE BANKRUPT.");
						     removePawn();
						     curr_pl.setBankrupt(true);
						     curr_pl = board.changeTurn();
						     PlayTheGame();
						  }    
		               }
				       else
				       {
				           changePlayerTurn();
				           PlayTheGame();
				       }  
			    }
		  }
	   }	
	}
	
	public void changePlayerTurn()
	{
		 if (!curr_pl.isInJail() && curr_pl.getJail_counter()!=0)
		 {
			 JOptionPane.showMessageDialog(null, "CHANGE TURN", "INFO", JOptionPane.INFORMATION_MESSAGE);
			 curr_pl = board.changeTurn();
			 curr_pl.setJail_counter(0);
			 Build();
		 }
		else if (!curr_pl.isInJail())
		 {
		     if (board.getDice().getDice1() != board.getDice().getDice2())
	         { 
		         counter=0;
		         curr_pl = board.changeTurn();
		         JOptionPane.showMessageDialog(null, "CHANGE TURN", "INFO", JOptionPane.INFORMATION_MESSAGE); 
		         Build();
	         }
		     else
		     {
			     counter++;
			     if (counter==3)
			     {
		    	    counter=0;
			        JOptionPane.showMessageDialog(null, "PLAYER : " + curr_pl.getName() + " YOU ROLLED 3 TIMES DOUBLE DICE. "
			     		+ "YOU ARE GOING TO JAIL.");
			     
			        removePawn();
			        curr_pl.setPosition(10);
			        curr_pl.setInJail(true);
			        placePawn();
			     
			        curr_pl = board.changeTurn();
			        JOptionPane.showMessageDialog(null, "CHANGE TURN", "INFO", JOptionPane.INFORMATION_MESSAGE);
			        Build();
			  }
		 }
		 }
		 else
		 {
			 JOptionPane.showMessageDialog(null, "CHANGE TURN", "INFO", JOptionPane.INFORMATION_MESSAGE);
			 curr_pl = board.changeTurn();
			 Build();
		 }
	}
	
	public void playSquare(Square s)
	{
		 
		 if (s instanceof TaxSquare)
		      playTaxSquare((TaxSquare)s);
	     else if (s instanceof CardSquare)
		      playCardSquare((CardSquare)s);
	     else if (s instanceof UtilitySquare)
	    	  playUtilitySquare((UtilitySquare) s);
	     else if (s instanceof StationSquare)
	    	  playStationSquare((StationSquare) s);
	     else if (s instanceof PlotSquare)
		    	playPlotSquare((PlotSquare) s);
	     else if (s.getN().equals("30"))
	    	  playGoToJail();	 
	     else
	     {
	    	changePlayerTurn();
			PlayTheGame();	
	     }
		 
	}
	
	public void playTaxSquare(TaxSquare s)
	{
		Object option[] = {"PAY THE TAX"};
		i = JOptionPane.showOptionDialog(null, "YOU MUST PAY THE TAX " + s.getTax() + "$", "INFO", 
				JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null , option, option[0]);
		
		if (!s.payTax(curr_pl))
		{
		     JOptionPane.showMessageDialog(null,"YOU DON'T HAVE ENOUGH MONEY. \n"
		    	 		+ "YOU ARE BANKRUPT.");
		     removePawn();
		     curr_pl.setBankrupt(true);
		}
		else
			updateInfo();	
	
	 
	   changePlayerTurn();
	   PlayTheGame();	
	}
	
	public void playCardSquare(CardSquare s)
	{
		dice_1.setEnabled(false);
		dice_2.setEnabled(false);
		
		if (s.getN().equals("2"))
	    {
			
			JOptionPane.showMessageDialog(null,"PICK A COMMUNNITY CHEST CARD");
			decision.addActionListener(card_listener);
			decision.setEnabled(true);
	    }
		else 
		{
			JOptionPane.showMessageDialog(null,"PICK AN ORDER CARD");
			order.addActionListener(card_listener);
			order.setEnabled(true);
		}
		
	}

	class OrderOrDecisionButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource().equals(decision))
			{
				pickADecisionCard();
				decision.setEnabled(false);
			}
			else if (e.getSource().equals(order))
			{
				 pickAnOrderCard();
				 order.setEnabled(false);
		    }
		}
	}
	
	public void pickADecisionCard()
	{	
		Card c = new Card();
		c = board.generateCard(curr_pl,'d');
		
		if (c!=null)
		{
			JOptionPane.showMessageDialog(null,c.getStr());
				      
			if(c.getCode()==0 || c.getCode()==1 )
			{
				c.executeTheCard(curr_pl, board);
				updateInfo();
			    changePlayerTurn();
			    PlayTheGame();
			}
		    else 
			{
				removePawn();
				c.executeTheCard(curr_pl, board);
				updateInfo();
			    placePawn();
			    
			    Square s = findTheSquare(curr_pl);
				playSquare(s);	
		   } 
		 }
	}
	
	public void pickAnOrderCard()
	{
		Card c = new Card();
	    c = board.generateCard(curr_pl,'o');
	    
		if (c!=null)
		{
		   JOptionPane.showMessageDialog(null,c.getStr());
		   if (c.getCode()==0)
		   {
			   if (!c.executeTheCard(curr_pl, board))
			   {
				   JOptionPane.showMessageDialog(null, "YOU CAN'T PAY ALL THE PLAYERS\n"
				   		+ "YOU ARE BANKRUPT.\n", "INFO", JOptionPane.ERROR_MESSAGE);
				   removePawn();
				   curr_pl.setBankrupt(true);
			   }
			   else
				   JOptionPane.showMessageDialog(null, "ALL THE PLAYERS HAVE BEEN"
				   		+ " PAID SUCCESSFULLY.", "INFO",JOptionPane.INFORMATION_MESSAGE); 
		   }
		   else if(c.getCode()==1)
		   {
			    removePawn();
				c.executeTheCard(curr_pl, board);  
			    placePawn();
		   }
		   else
		   {	
			    if (!c.executeTheCard(curr_pl, board))
			    {   
			    	JOptionPane.showMessageDialog(null, "YOU ARE BANKRUPT.", "INFO",JOptionPane.ERROR_MESSAGE);
			    	removePawn();
			    	curr_pl.setBankrupt(true);
			    }    
			    else
			        JOptionPane.showMessageDialog(null, "THE AMOUNT HAS BEEN PAID SUCCESSFULLY.", "INFO", JOptionPane.INFORMATION_MESSAGE); 
		   }	
		  updateInfo();
		  changePlayerTurn();
		  PlayTheGame();
		}
	}
	
	public void playUtilitySquare(UtilitySquare s)
	{
		if (s.getP()==null)
		{
			Object option[] = {"YES" , "NO"};
		    i = JOptionPane.showOptionDialog(null, "DO YOU WANT TO BUY THE UTILITY COMPANY : " + s.getName() + "? PRICE: "
				+ s.getPrice()  , "INFO", 
				JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null , option, option[0]);
			if(i==0)
			{
				if (curr_pl.buyUtilitySquare(s.getPrice(),s))
				{
					updateInfo();
					s.setP(curr_pl);
					JOptionPane.showMessageDialog(null, "THE PURCHASE IS COMPLETE.");
				}
			}	
		}
		else if (s.getP()!= curr_pl)
		{
			int rent = s.getP().calculateUtilitiesRent() * (board.getDice().getDice1() + board.getDice().getDice2());
			s.setRent(rent);
			payRent(s);		
		}
		
		changePlayerTurn();
		PlayTheGame();	
	}
	
	public void playStationSquare(StationSquare s)
	{
		if (s.getP()==null)
		{
			Object option[] = {"YES" , "NO"};
			i = JOptionPane.showOptionDialog(null, "DO YOU WANT TO BUY THE RAILROAD : " + s.getName() + "? PRICE: "
					+ s.getPrice() + ";", "INFO", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null , option, option[0]);
			if(i==0)
			{
				if (curr_pl.buyStationSquare(s.getPrice(),s))
				{
					updateInfo();
					s.setP(curr_pl);
					JOptionPane.showMessageDialog(null, "THE PURCHASE IS COMPLETE.");
				}
			}
			
		}
		else if (s.getP()!= curr_pl)
		{
			int rent = s.getP().calculateStationRent();
			s.setRent(rent);
			payRent(s);
		}
		
	    changePlayerTurn();
		PlayTheGame();
	}
	
	public void playGoToJail()
	{
		Object option[] = {"GO TO JAIL"};
		i = JOptionPane.showOptionDialog(null, "YOU ARE ON THE 'GO TO JAIL' SQUARE ", "INFO", 
				JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null , option, option[0]);
		
		removePawn();
		curr_pl.setPosition(5);
		curr_pl.setInJail(true);
		placePawn();
		
		curr_pl = board.changeTurn();
	    JOptionPane.showMessageDialog(null, "CHANGE TURN", "INFO", JOptionPane.INFORMATION_MESSAGE);
		PlayTheGame();
	}
	
	public void playPlotSquare(PlotSquare s)
	{
		if (s.getP()==null)
		{
			Object option[] = {"YES" , "NO"};
			i = JOptionPane.showOptionDialog(null, "DO YOU WANT TO BUY THE PLOT: " + s.getName() + "? PRICE: "
					+ s.getPrice(), "INFO", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null , option, option[0]);
			if (i==0)
			{
				if (curr_pl.buyPlotSquare(s.getPrice(), s))
                {
					updateInfo();
					s.setP(curr_pl);
					JOptionPane.showMessageDialog(null, "THE PURCHASE IS COMPLETE.");   
                }			
			}
		}
		else if (s.getP() != curr_pl)
		{
			payRent(s);
		}
		
		 changePlayerTurn();
		 PlayTheGame();
		
	}
	
	public void payRent(Square s)
	{
		int rent;
		
		PlotSquare x = null;
		if (s instanceof PlotSquare)
			x = (PlotSquare)s ;
		
		
		if (s instanceof TaxSquare || s instanceof UtilitySquare || s instanceof StationSquare || (x!= null && x.getCounter()==0))
		{
		    rent = s.getRent();
		}
		else 
		{
			if (x.isHotel_built())
				rent = x.getHotel().getHotel_rent();
			else 
			{
			    if (x.getCounter()==1)
			    	rent = x.getHouse().getHouse_rent1();
			    else if (x.getCounter()==2)
			    	rent = x.getHouse().getHouse_rent2();
			    else if (x.getCounter()==3)
			    	rent = x.getHouse().getHouse_rent3();
			    else
			    	rent = x.getHouse().getHouse_rent4(); 
			}
		}
		
		Object option[] = {"PAY"};
	    i = JOptionPane.showOptionDialog(null, "YOU MUST PAY RENT " + rent + "$", "INFO", 
			JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null , option, option[0]);
	
	   if (curr_pl.payMoney(s.getRent()))
	   {
		updateInfo();
		s.getP().earnMoney(s.getRent());
		JOptionPane.showMessageDialog(null, "RENT PAYMENT IS COMPLETE.");	
	   }
	   else
	   {
		JOptionPane.showMessageDialog(null, "RENT PAYMENT IS NOT COMPLETE.\n"
	            + "YOU DON'T HAVE ENOUGH MONEY. YOU ARE BANKRUPT.");
		removePawn();
		curr_pl.setBankrupt(true);
	   }
	}
	
	public void whereToBuild(PlotSquare s)
	{
		j = Integer.parseInt(s.getN());
		if (j<=10)
		{ 
			hc.gridx=c[j].gridx+1;
			hc.gridy=c[j].gridy;
			hc.anchor=GridBagConstraints.SOUTH;

		}
		else if (j<=20)
		{
			hc.gridx=c[j].gridx;
			hc.gridy=c[j].gridy+1;
			hc.anchor=GridBagConstraints.WEST;
			
		}
		else if (j<=30)
		{
			hc.gridx=c[j].gridx-1;
			hc.gridy=c[j].gridy;
			hc.anchor=GridBagConstraints.NORTH;
			
		}
		else
		{

			hc.gridx=c[j].gridx;
			hc.gridy=c[j].gridy-1;	
			hc.anchor=GridBagConstraints.EAST;
			
		}	
		
	}
		
	public String whichHousetoBuild(PlotSquare s)
	{
		i = s.getCounter();
		j = Integer.parseInt(s.getN());
		String image_url;
		
		if (j<=10)
			image_url="/h" + i + "d.png" ;
		else if (j<=20)
			image_url="/h" + i + "l.png" ;
		else if (j<=30)
			image_url="/h" + i + "u.png" ;
		else
			image_url="/h" + i + "r.png" ;

		return image_url;
	}
	
	public void buildAHouse(String url)
	{

		Image image =  new ImageIcon(this.getClass().getResource(url)).getImage();
		
		if (image!=null)
		{
	        JLabel house_hotel = new JLabel(new ImageIcon(image));
	        house_hotel.setOpaque(true);
	        addHouse_Hotel(house_hotel);
	    }
	   else
			System.out.println("ERROR");
    }
  
	public String whichHoteltoBuild(PlotSquare s)
	{
		j = Integer.parseInt(s.getN());
		String image_url;
		if (j<=10)
			image_url="/hd.png" ;
		else if (j<=20)
			image_url="/hl.png" ;
		else if (j<=30)
			image_url="/hu.png" ;
		else
			image_url="/hr.png" ;

		return image_url;	
	}
	
	public void buildHotel(String url)
	{
        Image image =  new ImageIcon(this.getClass().getResource(url)).getImage();
		if (image!=null)
		{
			 JLabel house_hotel = new JLabel(new ImageIcon(image));
			 house_hotel.setOpaque(true);
	         addHouse_Hotel(house_hotel);
	    }
	    else
			System.out.println("ERROR");
	}
	
	public void addHouse_Hotel(JLabel l)
	{
	   	p.add(l,hc);
		revalidate();
	}
	
	public void checkBuild()
	{	
		if (curr_pl.canBuildHouse(1, 2) || curr_pl.canBuildHouse(2, 3) || curr_pl.canBuildHouse(3, 2))
		{
			JOptionPane.showMessageDialog(null, "YOU CAN BUILD A HOUSE." +
					"\n IF YOU WISH TO BUILD A HOUSE PRESS THE BUTTON.", "INFO", JOptionPane.INFORMATION_MESSAGE);
			house.setEnabled(true);
			house.addActionListener(new ActionListener() {
			     public void actionPerformed(ActionEvent arg0) {
			    	 
			    	  String[] options;
			    	  ArrayList<String> opt = new ArrayList<>();
				      ArrayList<Integer> o = new ArrayList<>();
				      if (curr_pl.canBuildHouse(1, 2))
				         {opt.add("BLUE");
				         o.add(12);}
				      if (curr_pl.canBuildHouse(2, 3))
					     {opt.add("GREEN");
					     o.add(23);}
				      if (curr_pl.canBuildHouse(3, 2))
					     {opt.add("YELLOW");
				         o.add(32);}
				      
				      ArrayList<String> t = new ArrayList<>();
				      if (opt.size()>1)
				      {
				    	  options = new String[opt.size()];
					      options = opt.toArray(options);
					 
					      j = JOptionPane.showOptionDialog(null, "CHOOSE GROUP TO BUILT", "INFO", 
								JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null , options, options[0]); 	
					      t = curr_pl.whereToBuildHouse(o.get(j)/10 , o.get(j)%10);
				      }
				      else
				    	  t = curr_pl.whereToBuildHouse(o.get(0)/10 , o.get(0)%10);

				      options = new String[t.size()];
				      options = t.toArray(options);
				    	  
				      i = JOptionPane.showOptionDialog(null, "CHOOSE PLOT TO BUILT", "INFO", 
								JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null , options, options[0]); 
				 
				    
				      PlotSquare p = null;
				      for (PlotSquare s : curr_pl.getTitles())
				      {
				    	  if (s.getName().equals(options[i]))
			              {
				    		  p=s;
			                  break;
			              }	  
				      }
				      
				      i = JOptionPane.showConfirmDialog(null, "Do you want to build a house for " 
				      +  p.getHouse().getHouse_cost()+ "?" , "INFO" , JOptionPane.YES_NO_OPTION);
				      if (i==0)
				      {
				    	 
				    	  if (curr_pl.payMoney(p.getHouse().getHouse_cost()))
				    	  {
				    		  JOptionPane.showMessageDialog(null, "The house has been built.", "INFO", JOptionPane.INFORMATION_MESSAGE);
				    		  p.buildHouse();
				    		  whereToBuild(p);
						      buildAHouse(whichHousetoBuild(p));   
						      updateInfo();
				    	  }
				    	  else
				    		  JOptionPane.showMessageDialog(null, "You don't have enough money to built the house.","INFO", JOptionPane.ERROR_MESSAGE); 
				    	  
				      }  
			     }});
			
		}
		
		
		if (curr_pl.canBuildHotel(1, 2) || curr_pl.canBuildHotel(2, 3) || curr_pl.canBuildHotel(3, 2))
		{
			JOptionPane.showMessageDialog(null, "YOU CAN BUILD A HOTEL." +
					"\n IF YOU WISH TO BUILD A HOTEL PRESS THE BUTTON.", "INFO", JOptionPane.INFORMATION_MESSAGE);
			hotel.setEnabled(true);
			hotel.addActionListener(new ActionListener() {
			     public void actionPerformed(ActionEvent arg0) {
			    	 
			    	  String[] options;
			    	  ArrayList<String> opt = new ArrayList<>();
				      ArrayList<Integer> o = new ArrayList<>();
				      if (curr_pl.canBuildHotel(1, 2))
				         {opt.add("BLUE");
				         o.add(12);}
				      if (curr_pl.canBuildHotel(2, 3))
					     {opt.add("GREEN");
					     o.add(23);}
				      if (curr_pl.canBuildHotel(3, 2))
					     {opt.add("YELLOW");
				         o.add(32);}
				      
				      
				      ArrayList<String> t = new ArrayList<>();
				      if (opt.size()>1)
				      {
				    	  options = new String[opt.size()];
					      options = opt.toArray(options);
					 
					      j = JOptionPane.showOptionDialog(null, "CHOOSE GROUP TO BUILT", "INFO", 
								JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null , options, options[0]); 	
					      t = curr_pl.whereToBuildHotel(o.get(j)/10 , o.get(j)%10);
				      }
				      else
				    	  t = curr_pl.whereToBuildHotel(o.get(0)/10, o.get(0)%10);
				     

				      options = new String[t.size()];
				      options = t.toArray(options);
				    	  
				      i = JOptionPane.showOptionDialog(null, "CHOOSE PLOT TO BUILT", "INFO", 
								JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null , options, options[0]); 
				 
				    
				      PlotSquare p = null;
				      for (PlotSquare s : curr_pl.getTitles())
				      {
				    	  if (s.getName().equals(options[i]))
			              {
				    		  p=s;
			                  break;
			              }	  
				      }
				      
				      i = JOptionPane.showConfirmDialog(null, "Do you want to build a hotel for " 
				      +  p.getHotel().getHotel_cost()+ "?" , "INFO" , JOptionPane.YES_NO_OPTION);
				      if (i==0)
				      {
				    	 
				    	  if (curr_pl.payMoney(p.getHotel().getHotel_cost()))
				    	  {
				    		  JOptionPane.showMessageDialog(null, "The hotel has been built.", "INFO", JOptionPane.INFORMATION_MESSAGE);
				    		  p.setHotel_built(true);
				    		  whereToBuild(p);
						      buildHotel(whichHoteltoBuild(p));   
						      updateInfo();
				    	  }
				      }
				    	  else
				    		  JOptionPane.showMessageDialog(null, "You don't have enough money to built the house.","INFO", JOptionPane.ERROR_MESSAGE); 
			    	 
			     }});
		}
			
		
	}	

	public void Build()
	{
		house.setEnabled(false);
		hotel.setEnabled(false);
		checkBuild();
	}
	
	public void dealWithJail()
	{
		Object option[] = {"ROLL DICE", "PAY FINE(100$)" , "GET OUT OF JAIL CARD","BUY CARD FROM ANOTHER PLAYER"};
		jail_choice = JOptionPane.showOptionDialog(null, "PLAYER : " + curr_pl.getName() + " PICK AN OPTION TO GET OUT OF JAIL \n"   
				, "INFO", 
				JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null , option, option[0]);
	
	    if (jail_choice==1)  
		{
			 if (curr_pl.payMoney(100))
		     {
				 updateInfo();
			     JOptionPane.showMessageDialog(null,"THE FINE HAS BEEN PAID. ROLL THE DICE");
			     curr_pl.setInJail(false);	
			 }
			 else 
			 {
			     JOptionPane.showMessageDialog(null,"THE FINE HAS NOT BEEN PAID. \n"
			    		 + "YOU DON'T HAVE ENOUGH MONEY. PICK ANOTHER OPTION");
			     dealWithJail();
			 }	    	
		}else if (jail_choice==2)
	     {
	    	 if(curr_pl.isGetOutOfJail())
	    	 { 
	    		 curr_pl.setInJail(false);
	    		 curr_pl.setGetOutOfJail(false);
	    		 JOptionPane.showMessageDialog(null, "YOU USED YOUR CARD. ROLL THE DICE");
	    	 }
	    	 else
	    	 {
	    		 JOptionPane.showMessageDialog(null, "YOU DON'T HAVE A GET OUT OF JAIL CARD. PICK ANOTHER OPTION.");
	    		 dealWithJail();
	    	 }
	     }
	     
	    
	     else if (jail_choice==3)
	     {
	    	 Player p_card = null;   // we assume that only one get out of jail free card exists
	    	 
	    	 for (Player p : board.getPlayers())
	    	 {
	    		 if (p.getCode() != curr_pl.getCode() && p.isGetOutOfJail())
	    			 p_card=p;
	    	 }
	    	 
	    	 if (p_card == null)
	    	 {
	    		 JOptionPane.showMessageDialog(null, "THERE ISN'T ANOTHER PLAYER WITH A CARD.\n"
	    				 + "PICK ANOTHER OPTION.");
	    		 dealWithJail();
	    	 }
	    	 else
	    	 {
	    		 j = JOptionPane.showConfirmDialog(null,"PLAYER : " + p_card.getName() + " DO YOU WANT TO SELL YOUR CARD?",
	    				"SELL CARD",JOptionPane.YES_NO_OPTION );
	    		 
	    		 if (j==0)
	    		 {
	    			 int price = -1;
	    			 
	    			 do{
	    			    String answer = JOptionPane.showInputDialog(null, "PLAYER : " + p_card.getName() + 
	    					 " SET THE PRICE UP TO 100$.\n");
	    			    if (answer == null)
	    			    {
	    				    JOptionPane.showMessageDialog(null, "THE PLAYER HAS CANCELED SELLING HIS/HER CARD. \n"
	    						+ "PICK ANOTHER OPTION.", "INFO", JOptionPane.INFORMATION_MESSAGE);
	    				    dealWithJail();
	    				    break;
	    			    }
	    			    else
	    			    {
	    			        try{price = Integer.parseInt(answer);} 
	    			        catch (NumberFormatException e) {price = -1;}
	    			    }}
	    			 while(price==-1 || price>100 || price==0);
	    				  

	 	    		    i = JOptionPane.showConfirmDialog(null,"PLAYER : " + curr_pl.getName() + " DO YOU WANT TO BUE THE CARD FOR " + price + "$ ?",
	    		    				"BUY CARD",JOptionPane.YES_NO_OPTION );
	    			    if (i==0)
	    			    {
	    					JOptionPane.showMessageDialog(null,"THE CARD HAS BEEN BOUGHT. YOU ARE FREE. \n" + "ROLL THE DICE.");
	    					curr_pl.setInJail(false);
	    					p_card.setGetOutOfJail(false);
	    					curr_pl.payMoney(price);
	    					p_card.earnMoney(price); 
	    					
	    					PlayTheGame();
	    			    }
	    			    else
	    			    {
	    				   JOptionPane.showMessageDialog(null, "YOU DON'T WANT TO BUY THE CARD. \n" + 
	    				    		"PICK ANOTHER OPTION.");
	    			       dealWithJail();
	    			    }
	    			 }
	    		 else
	    		 {
	    			 JOptionPane.showMessageDialog(null, "THE PLAYER HAS DECLINED TO SELL HIS/HER CARD. \n"
	    					 + "PICK ANOTHER OPTION.");
	    			 dealWithJail();
	    		 }
	    	 }
	     }
		
	}
	
}
	
	


