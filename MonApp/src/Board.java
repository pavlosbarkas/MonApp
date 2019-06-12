import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;


public class Board extends JFrame{
	
	private Dice dice = new Dice();
	
	private ArrayList<Player> players = new ArrayList<>();
	
	private ArrayList<Square> squares = new ArrayList<>();
	//private ArrayList<Square> card_squares = new ArrayList<>();
	private ArrayList<PlotSquare> plot_squares = new ArrayList<>();
	//private ArrayList<TaxSquare> tax_squares = new ArrayList<>();
	//private ArrayList<UtilitySquare> utility_squares = new ArrayList<>();
	//private ArrayList<StationSquare> station_squares = new ArrayList<>();
	
	private ArrayList<Card> des_cards = new ArrayList<>();
	private ArrayList<Card> ord_cards = new ArrayList<>();
	private int n; //number of players
	private Player curr_pl;
	
	

	public void makeTheCards()
	{
		Card c1 = new Card('d', "GET OUT OF JAIL CARD",1,0);
		Card c2 = new Card('d', "EARN 500$",500,1);
		Card c3 = new Card('d', "MOVE 2 SQUARES AHEAD",2,2);
		Card c4 = new Card('d', "GO BACK 3 SQUARES",-3,3);
		Card c5 = new Card('d', "GO TO 'GO'",0,4);
		
		Card c6 = new Card('o', "GIVE TO EVERY PLAYER 50$",50,0);
		Card c7 = new Card('o', "GO TO JAIL",0,1);
		Card c8 = new Card('o', "PAY 125$",125,2);
		Card c9 = new Card('o', "DOCTOR'S EXPENSES. PAY 100$",100,3);
		Card c10 = new Card('o', "CONTRIBUTE TO CHARITY. PAY 12$",12,4);
	
		des_cards.add(c1);
		des_cards.add(c2);
		des_cards.add(c3);
		des_cards.add(c4);
		des_cards.add(c5);
		
		ord_cards.add(c6);
		ord_cards.add(c7);
		ord_cards.add(c8);
		ord_cards.add(c9);
		ord_cards.add(c10);
		
	}
	
	 public Card generateCard(Player p,char t)
	 {  
		 Card return_card = new Card();
		 
		 if (t=='d')
		 {
			 return_card = des_cards.get(0);
			 des_cards.remove(0);
			 des_cards.add(return_card);
		 }
		 else
		 {
			 return_card = ord_cards.get(0);
			 ord_cards.remove(0);
			 ord_cards.add(return_card); 
		 }
		 return return_card;
	        
	   }
	 
	 public void makeSpecialSquares()
	 {
		JLabel l1 = new JLabel();
		JLabel l2 = new JLabel();
		JLabel l3 = new JLabel();
		JLabel l4 = new JLabel();
		
		//start
		Image image1 = new ImageIcon(this.getClass().getResource("/go.png")).getImage();
		l1.setIcon(new ImageIcon(image1));
		Square s1 = new Square("0" ,"Start",l1);
		squares.add(s1);
		
		//jail
		Image image2 = new ImageIcon(this.getClass().getResource("/jail.png")).getImage();
		l2.setIcon(new ImageIcon(image2));
		Square s2 = new Square("5","Jail",l2);
		squares.add(s2);
		
		//free parking
		Image image3 = new ImageIcon(this.getClass().getResource("/parking.png")).getImage();
		l3.setIcon(new ImageIcon(image3));
		Square s3 = new Square("10","Free parking",l3);
		squares.add(s3);
		
		//go to jail
		Image image4 = new ImageIcon(this.getClass().getResource("/go_jail.png")).getImage();
		l4.setIcon(new ImageIcon(image4));
		Square s4 = new Square("15","Go to Jail",l4);
		squares.add(s4);

		 
	 }
	 
	 
	 public void makeCardSquares()
	 {
		JLabel l1 = new JLabel();
		JLabel l2 = new JLabel();
		
		//community chest cards
		Image image1 = new ImageIcon(this.getClass().getResource("/chest.png")).getImage();
		l1.setIcon(new ImageIcon(image1));
		CardSquare s1 = new CardSquare("2","Community chest",l1);
		squares.add(s1);
		
		//chance cards
		Image image2 = new ImageIcon(this.getClass().getResource("/chance.png")).getImage();
		l2.setIcon(new ImageIcon(image2));
		CardSquare s2 = new CardSquare("11","Chance",l2);
		squares.add(s2);

			
		 
	 }
	 
	 public void makeTaxSquares()
	 {
		JLabel l1 = new JLabel();
		JLabel l2 = new JLabel();
		
		//luxury tax
		Image image1 = new ImageIcon(this.getClass().getResource("/luxury.png")).getImage();
		l1.setIcon(new ImageIcon(image1));
		TaxSquare s1 = new TaxSquare("7","Luxury Tax",l1, 75);
		squares.add(s1);
		
		//income tax
		Image image2 = new ImageIcon(this.getClass().getResource("/income.png")).getImage();
		l2.setIcon(new ImageIcon(image2));
		TaxSquare s2 = new TaxSquare("16","Income Tax",l2,200);
		squares.add(s2);
		
				
	 }
	 
	 public void makeStationSquares()
	 {
		JLabel l1 = new JLabel();
		JLabel l2 = new JLabel();
		JLabel l3 = new JLabel();
			
		//first station
		Image image1 = new ImageIcon(this.getClass().getResource("/st1.png")).getImage();
		l1.setIcon(new ImageIcon(image1));
		StationSquare s1 = new StationSquare("4",l1, "Reading Railroad");
		squares.add(s1);
		
			
		//second station
		Image image2 = new ImageIcon(this.getClass().getResource("/st2.png")).getImage();
		l2.setIcon(new ImageIcon(image2));
		StationSquare s2 = new StationSquare("8",l2,"Pennsylvania Railroad");
		squares.add(s2);
		
			
		//third station
		Image image3 = new ImageIcon(this.getClass().getResource("/st3.png")).getImage();
		l3.setIcon(new ImageIcon(image3));
		StationSquare s3 = new StationSquare("14",l3,"B. & O. Railroad");
		squares.add(s3);
		
		 
	 }
	 
	 public void makeUtilitySquares()
	 {
		 JLabel l1 = new JLabel();
		 JLabel l2 = new JLabel();
		 
		//water works
		Image image1 = new ImageIcon(this.getClass().getResource("/water.png")).getImage();
		l1.setIcon(new ImageIcon(image1));
		UtilitySquare s1 = new UtilitySquare("6",l1,"Water Works");
		squares.add(s1);
		
			
		//electric company
		Image image2 = new ImageIcon(this.getClass().getResource("/electric.png")).getImage();
		l2.setIcon(new ImageIcon(image2));
		UtilitySquare s2 = new UtilitySquare("19",l2,"Electric Company");
		squares.add(s2);
		
		 
	 }
	 
	 
	 public void makePlotSquaresGroup1()
	 {
		JLabel l1 = new JLabel();
		JLabel l2 = new JLabel();
		
				
		//first square
	    Image image1 = new ImageIcon(this.getClass().getResource("/im1g1.png")).getImage();
		l1.setIcon(new ImageIcon(image1));
		
		House house1 = new House(40,100,300,450,50);
		Hotel hotel1 = new Hotel(600,50);
		
		PlotSquare s1 = new PlotSquare("1",l1, "Connecticut Avenue",120,8,house1,hotel1,1,2);
		squares.add(s1);
		plot_squares.add(s1);
	
		
		//second square
		Image image2 = new ImageIcon(this.getClass().getResource("/im2g1.png")).getImage();
		l2.setIcon(new ImageIcon(image2));
		
		House house2 = new House(30,90,270,400,50);
		Hotel hotel2 = new Hotel(550,50);
		
		PlotSquare s2 = new PlotSquare("3",l2,"Vermont Avenue",100,6,house2,hotel2,1,2);
		squares.add(s2);
		plot_squares.add(s2);
				
	 }
	 
	 public void makePlotSquaresGroup2()
	 {
		 JLabel l1 = new JLabel();
		 JLabel l2 = new JLabel();
		 JLabel l3 = new JLabel();
			
					
		//first square
		Image image1 = new ImageIcon(this.getClass().getResource("/im1g2.png")).getImage();
		l1.setIcon(new ImageIcon(image1));
			
		House house1 = new House(130,390,900,1100,150);
		Hotel hotel1 = new Hotel(1275,150);
			
		PlotSquare s1 = new PlotSquare("9",l1, "Oxford Street",300,26,house1,hotel1,2,3);
		squares.add(s1);
		plot_squares.add(s1);
		
					
			
		//second square
		Image image2 = new ImageIcon(this.getClass().getResource("/im2g2.png")).getImage();
		l2.setIcon(new ImageIcon(image2));
		
		House house2 = new House(130,390,900,1100,150);
		Hotel hotel2 = new Hotel(1275,150);
			
		PlotSquare s2 = new PlotSquare("12",l2,"Regent Street",300,26,house2,hotel2,2,3);
		squares.add(s2);
		plot_squares.add(s2);
		
		
		//third square
		Image image3 = new ImageIcon(this.getClass().getResource("/im3g2.png")).getImage();
		l3.setIcon(new ImageIcon(image3));
				
		House house3 = new House(150,400,1000,1200,160);
		Hotel hotel3 = new Hotel(1400,160);
					
		PlotSquare s3 = new PlotSquare("13",l3,"Bond Street",320,28,house3,hotel3,2,3);
		squares.add(s3);
		plot_squares.add(s3);
	 }
	 
	 public void makePlotSquaresGroup3()
	 {
		JLabel l1 = new JLabel();
		JLabel l2 = new JLabel();
			
					
		//first square
		Image image1 = new ImageIcon(this.getClass().getResource("/im1g3.png")).getImage();
		l1.setIcon(new ImageIcon(image1));
			
		House house1 = new House(120,360,850,1050,140);
		Hotel hotel1 = new Hotel(1200,140);
			
		PlotSquare s1 = new PlotSquare("17",l1, "Piccadilly",280,22,house1,hotel1,3,3);
		squares.add(s1);
		plot_squares.add(s1);
		
					
			
		//second square
		Image image2 = new ImageIcon(this.getClass().getResource("/im2g3.png")).getImage();
		l2.setIcon(new ImageIcon(image2));
			
		House house2 = new House(110,330,800,975,150);
		Hotel hotel2 = new Hotel(1150,150);
			
		PlotSquare s2 = new PlotSquare("18",l2,"Leicester Square",260,22,house2,hotel2,3,2);
		squares.add(s2);
		plot_squares.add(s2);
		
	 }
	 
	 public void makeTheBoard()
	 {
		makeSpecialSquares();
		makeCardSquares();
		makeTaxSquares();
		makeStationSquares();
		makeUtilitySquares();
		makePlotSquaresGroup1();
		makePlotSquaresGroup2();
		makePlotSquaresGroup3();
		curr_pl = players.get(0);
	 }
	
	 
	 public Player changeTurn()
	 {
		 int i;
		 boolean flag = true;
		 for (Player p : players)
		 {
			 if (p.getName().equals(curr_pl.getName()))
			 {
				 i = players.indexOf(p);
				 if (i==players.size()-1)
					 i=0;
				 else
					 i++;
				 
				 do{
					 if (players.get(i).isBankrupt()==false)
					 { 
						 curr_pl = players.get(i);
					     flag=false;
					 }
					 if (i==players.size()-1)
						 i=0;
					 else
						 i++;
				 }while (flag==true);
				 break;
			 }
				 
		 }
		 return curr_pl;
	 }
	//getters and setters
	public ArrayList<Player> getPlayers() {
		return players;
	}

	
	public ArrayList<Square> getSquares() {
		return squares;
	}

	public int getN() {
		return n;
	}

	public Dice getDice() {
		return dice;
	}

	public void setN(int n) {
		this.n = n;
	}

	public Player getCurr_pl() {
		return curr_pl;
	}

	public ArrayList<PlotSquare> getPlot_squares() {
		return plot_squares;
	}

	
	}


	


