import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class Board extends JFrame{
	
	private Dice dice = new Dice();
	private ArrayList<Player> players = new ArrayList<>();
	private ArrayList<Square> squares = new ArrayList<>();
	private ArrayList<Card> com_cards = new ArrayList<>();
	private ArrayList<Card> chance_cards = new ArrayList<>();
	private int n; 
	private Player curr_pl;
	
	//Creates the community chest and chance cards.
	public void makeTheCards(){
		
		Card c1 = new Card(0, "GET OUT OF JAIL CARD",1,0);
		Card c2 = new Card(0, "EARN 500$",500,1);
		Card c3 = new Card(0, "MOVE 2 SQUARES AHEAD",2,2);
		Card c4 = new Card(0, "GO BACK 3 SQUARES",-3,3);
		Card c5 = new Card(0, "GO TO 'GO'",0,4);
		
		Card c6 = new Card(1, "GIVE TO EVERY PLAYER 50$",50,0);
		Card c7 = new Card(1, "GO TO JAIL!",0,1);
		Card c8 = new Card(1, "PAY 125$",125,2);
		Card c9 = new Card(1, "DOCTOR'S EXPENSES. PAY 100$",100,3);
		Card c10 = new Card(1,"CONTRIBUTE TO CHARITY. PAY 12$",12,4);
	
		com_cards.add(c1);
		com_cards.add(c2);
		com_cards.add(c3);
		com_cards.add(c4);
		com_cards.add(c5);
		
		chance_cards.add(c6);
		chance_cards.add(c7);
		chance_cards.add(c8);
		chance_cards.add(c9);
		chance_cards.add(c10);
		
	}
	
	//The upper card is drawn by the player and then added as last in the list 
	public Card generateCard(Player p,int t){  
		
		Card return_card = new Card();
		 
		if (t == 0 ){
			
			return_card = com_cards.get(0);
			com_cards.remove(0);
			com_cards.add(return_card);	
			 
		}else{
			
			return_card = chance_cards.get(0);
			chance_cards.remove(0);
			chance_cards.add(return_card); 
		
		} 
		
		return return_card;     
	
	}
	
	//Creates the "GO", "Jail", "Free Parking" and "Go to Jail" squares.
	public void makeSpecialSquares(){
		
		JLabel l1 = new JLabel();
		JLabel l2 = new JLabel();
		JLabel l3 = new JLabel();
		JLabel l4 = new JLabel();
		
		//start
	    Image image1 = new ImageIcon(this.getClass().getResource("/square0.jpg")).getImage();
	    l1.setIcon(new ImageIcon(image1));
		Square s1 = new Square("0" ,"Start",l1);
		squares.add(s1);
				
		//jail
		Image image2 = new ImageIcon(this.getClass().getResource("/square10.jpg")).getImage();
		l2.setIcon(new ImageIcon(image2));
		Square s2 = new Square("10","Jail",l2);
		squares.add(s2);
				
		//free parking
		Image image3 = new ImageIcon(this.getClass().getResource("/square20.jpg")).getImage();
		l3.setIcon(new ImageIcon(image3));
		Square s3 = new Square("20","Free parking",l3);
		squares.add(s3);
				
		//go to jail
		Image image4 = new ImageIcon(this.getClass().getResource("/square30.jpg")).getImage();
		l4.setIcon(new ImageIcon(image4));
		Square s4 = new Square("30","Go to Jail",l4);
		squares.add(s4);
		
	}
	
	//Creates the community chest and chance card squares.
	public void makeCardSquares(){
		
		JLabel l1 = new JLabel();
		JLabel l2 = new JLabel();
		JLabel l3 = new JLabel();
		JLabel l4 = new JLabel();
		JLabel l5 = new JLabel();
		JLabel l6 = new JLabel();
		
		//community chest cards
		Image image1 = new ImageIcon(this.getClass().getResource("/square2.jpg")).getImage();
		l1.setIcon(new ImageIcon(image1));
		CardSquare s1 = new CardSquare("2","Community chest",l1);
		squares.add(s1);
		
		Image image2 = new ImageIcon(this.getClass().getResource("/square17.jpg")).getImage();
		l2.setIcon(new ImageIcon(image2));
		CardSquare s2 = new CardSquare("17","Community chest",l2);
		squares.add(s2);
		
		Image image3 = new ImageIcon(this.getClass().getResource("/square33.jpg")).getImage();
		l3.setIcon(new ImageIcon(image3));
		CardSquare s3 = new CardSquare("33","Community chest",l3);
		squares.add(s3);
		
		//chance cards
		Image image4 = new ImageIcon(this.getClass().getResource("/square7.jpg")).getImage();
		l4.setIcon(new ImageIcon(image4));
		CardSquare s4 = new CardSquare("7","Chance",l4);
		squares.add(s4);
		
		Image image5 = new ImageIcon(this.getClass().getResource("/square22.jpg")).getImage();
		l5.setIcon(new ImageIcon(image5));
		CardSquare s5 = new CardSquare("22","Chance",l5);
		squares.add(s5);
		
		Image image6 = new ImageIcon(this.getClass().getResource("/square36.jpg")).getImage();
		l6.setIcon(new ImageIcon(image6));
		CardSquare s6 = new CardSquare("36","Chance",l6);
		squares.add(s6); 
		
	}
	
	public void makeTaxSquares(){
		
		JLabel l1 = new JLabel();
		JLabel l2 = new JLabel();
		
		//luxury tax
		Image image1 = new ImageIcon(this.getClass().getResource("/square38.jpg")).getImage();
		l1.setIcon(new ImageIcon(image1));
		TaxSquare s1 = new TaxSquare("38","Luxury Tax",l1, 100);
		squares.add(s1);
				
		//income tax
		Image image2 = new ImageIcon(this.getClass().getResource("/square4.jpg")).getImage();
		l2.setIcon(new ImageIcon(image2));
		TaxSquare s2 = new TaxSquare("4","Income Tax",l2,200);
		squares.add(s2);
		
	}
	 
	public void makeStationSquares(){
		
		JLabel l1 = new JLabel();
		JLabel l2 = new JLabel();
		JLabel l3 = new JLabel();
		JLabel l4 = new JLabel();
		
		//first station
		Image image1 = new ImageIcon(this.getClass().getResource("/square5.jpg")).getImage();
		l1.setIcon(new ImageIcon(image1));
		StationSquare s1 = new StationSquare("5",l1, "Reading Railroad");
		squares.add(s1);	
		
		//second station
		Image image2 = new ImageIcon(this.getClass().getResource("/square15.jpg")).getImage();
		l2.setIcon(new ImageIcon(image2));
		StationSquare s2 = new StationSquare("15",l2,"Pennsylvania Railroad");
		squares.add(s2);
					
		//third station
		Image image3 = new ImageIcon(this.getClass().getResource("/square25.jpg")).getImage();
		l3.setIcon(new ImageIcon(image3));
		StationSquare s3 = new StationSquare("25",l3,"B. & O. Railroad");
		squares.add(s3);
				
		//fourth station
		Image image4 = new ImageIcon(this.getClass().getResource("/square35.jpg")).getImage();
		l4.setIcon(new ImageIcon(image4));
		StationSquare s4 = new StationSquare("35",l4,"Short Line");
		squares.add(s4); 
	
	}
	 
	public void makeUtilitySquares(){
		
		JLabel l1 = new JLabel();
		JLabel l2 = new JLabel();
		 
		//water works
	    Image image1 = new ImageIcon(this.getClass().getResource("/square28.jpg")).getImage();
	    l1.setIcon(new ImageIcon(image1));
	    UtilitySquare s1 = new UtilitySquare("28",l1,"Water Works");
		squares.add(s1);	
				
		//electric company
		Image image2 = new ImageIcon(this.getClass().getResource("/square12.jpg")).getImage();
		l2.setIcon(new ImageIcon(image2));
		UtilitySquare s2 = new UtilitySquare("12",l2,"Electric Company");
		squares.add(s2);
		
	}
	 
	//Creates the brown group of plots
	public void makePlotSquaresGroup1(){
		
		JLabel l1 = new JLabel();
		JLabel l2 = new JLabel();
				
		//first square
	    Image image1 = new ImageIcon(this.getClass().getResource("/im1g1.jpg")).getImage();
		l1.setIcon(new ImageIcon(image1));
		
		House house1 = new House(10,30,90,160,50);
		Hotel hotel1 = new Hotel(250,50);
		
		PlotSquare s1 = new PlotSquare("1",l1, "Meditterenean Avenue",60,4,house1,hotel1,1,2);
		squares.add(s1);
		
		//second square
		Image image2 = new ImageIcon(this.getClass().getResource("/im2g1.jpg")).getImage();
		l2.setIcon(new ImageIcon(image2));
		
		House house2 = new House(20,60,180,320,50);
		Hotel hotel2 = new Hotel(450,50);
		
		PlotSquare s2 = new PlotSquare("3",l2,"Baltic Avenue",60,8,house2,hotel2,1,2);
		squares.add(s2);
		
	}
	 
	//Creates the light blue group of plots
	public void makePlotSquaresGroup2(){
		
		JLabel l1 = new JLabel();
		JLabel l2 = new JLabel();
		JLabel l3 = new JLabel();	
					
		//first square
		Image image1 = new ImageIcon(this.getClass().getResource("/im1g2.jpg")).getImage();
		l1.setIcon(new ImageIcon(image1));
				
		House house1 = new House(30,90,270,400,50);
		Hotel hotel1 = new Hotel(550,50);
				
		PlotSquare s1 = new PlotSquare("6",l1, "Oriental Avenue",100,12,house1,hotel1,2,3);
		squares.add(s1);
				
		//second square
		Image image2 = new ImageIcon(this.getClass().getResource("/im2g2.jpg")).getImage();
		l2.setIcon(new ImageIcon(image2));
			
		House house2 = new House(30,90,270,400,50);
		Hotel hotel2 = new Hotel(550,50);
				
		PlotSquare s2 = new PlotSquare("8",l2,"Vermont Avenue",100,12,house2,hotel2,2,3);
		squares.add(s2);
			
		//third square
		Image image3 = new ImageIcon(this.getClass().getResource("/im3g2.jpg")).getImage();
		l3.setIcon(new ImageIcon(image3));
					
		House house3 = new House(40,100,300,450,50);
		Hotel hotel3 = new Hotel(600,50);
						
		PlotSquare s3 = new PlotSquare("9",l3,"Connecticut Avenue",120,16,house3,hotel3,2,3);
		squares.add(s3);
	 
	}
	
	//Creates the pink group of plots 
	public void makePlotSquaresGroup3(){
		
		JLabel l1 = new JLabel();
		JLabel l2 = new JLabel();
		JLabel l3 = new JLabel();
					
		//first square
		Image image1 = new ImageIcon(this.getClass().getResource("/im1g3.jpg")).getImage();
		l1.setIcon(new ImageIcon(image1));
			
		House house1 = new House(50,150,450,625,100);
		Hotel hotel1 = new Hotel(750,100);
			
		PlotSquare s1 = new PlotSquare("11",l1, "St. Charles Place",140,20,house1,hotel1,3,3);
		squares.add(s1);
			
		//second square
		Image image2 = new ImageIcon(this.getClass().getResource("/im2g3.jpg")).getImage();
		l2.setIcon(new ImageIcon(image2));
			
		House house2 = new House(50,150,450,625,100);
		Hotel hotel2 = new Hotel(750,100);
			
		PlotSquare s2 = new PlotSquare("13",l2,"States Avenue",140,24,house2,hotel2,3,3);
		squares.add(s2);
		
		//third square
		Image image3 = new ImageIcon(this.getClass().getResource("/im3g3.jpg")).getImage();
		l3.setIcon(new ImageIcon(image3));
						
		House house3 = new House(60,180,500,700,100);
		Hotel hotel3 = new Hotel(900,100);
							
		PlotSquare s3 = new PlotSquare("14",l3,"Virginia Avenue",160,24,house3,hotel3,3,3);
		squares.add(s3);
		
	}
	 
	//Creates the orange group of plots
	public void makePlotSquaresGroup4(){
		
		JLabel l1 = new JLabel();
		JLabel l2 = new JLabel();
		JLabel l3 = new JLabel();
					
		//first square
		Image image1 = new ImageIcon(this.getClass().getResource("/im1g4.jpg")).getImage();
		l1.setIcon(new ImageIcon(image1));
			
		House house1 = new House(70,200,550,750,100);
		Hotel hotel1 = new Hotel(950,100);
			
		PlotSquare s1 = new PlotSquare("16",l1, "St. James Place",180,28,house1,hotel1,4,3);
		squares.add(s1);
			
		//second square
		Image image2 = new ImageIcon(this.getClass().getResource("/im2g4.jpg")).getImage();
		l2.setIcon(new ImageIcon(image2));
			
		House house2 = new House(70,200,550,750,100);
		Hotel hotel2 = new Hotel(950,100);
			
		PlotSquare s2 = new PlotSquare("18",l2,"Tennessee Avenue",180,28,house2,hotel2,4,3);
		squares.add(s2);
		
		//third square
		Image image3 = new ImageIcon(this.getClass().getResource("/im3g4.jpg")).getImage();
		l3.setIcon(new ImageIcon(image3));
						
		House house3 = new House(80,220,600,800,100);
		Hotel hotel3 = new Hotel(1000,100);
							
		PlotSquare s3 = new PlotSquare("19",l3,"New York Avenue",200,32,house3,hotel3,4,3);
		squares.add(s3);
		
	}
	 
	//Creates the red group of plots
	public void makePlotSquaresGroup5(){
		
		JLabel l1 = new JLabel();
		JLabel l2 = new JLabel();
		JLabel l3 = new JLabel();
					
		//first square
		Image image1 = new ImageIcon(this.getClass().getResource("/im1g5.jpg")).getImage();
		l1.setIcon(new ImageIcon(image1));
			
		House house1 = new House(90,250,700,875,150);
		Hotel hotel1 = new Hotel(1050,150);
			
		PlotSquare s1 = new PlotSquare("21",l1, "Kentucky Avenue",220,36,house1,hotel1,5,3);
		squares.add(s1);
			
		//second square
		Image image2 = new ImageIcon(this.getClass().getResource("/im2g5.jpg")).getImage();
		l2.setIcon(new ImageIcon(image2));
			
		House house2 = new House(90,250,700,875,150);
		Hotel hotel2 = new Hotel(1050,150);
			
		PlotSquare s2 = new PlotSquare("23",l2,"Indiana Avenue",220,36,house2,hotel2,5,3);
		squares.add(s2);
		
		//third square
		Image image3 = new ImageIcon(this.getClass().getResource("/im3g5.jpg")).getImage();
		l3.setIcon(new ImageIcon(image3));
						
		House house3 = new House(100,300,750,925,150);
		Hotel hotel3 = new Hotel(1100,150);
							
		PlotSquare s3 = new PlotSquare("24",l3,"Illinois Avenue",240,40,house3,hotel3,5,3);
		squares.add(s3);
		
	}
	 
	//Creates the yellow group of plots
	public void makePlotSquaresGroup6(){
		
		JLabel l1 = new JLabel();
		JLabel l2 = new JLabel();
		JLabel l3 = new JLabel();
					
		//first square
		Image image1 = new ImageIcon(this.getClass().getResource("/im1g6.jpg")).getImage();
		l1.setIcon(new ImageIcon(image1));
			
		House house1 = new House(110,330,800,975,150);
		Hotel hotel1 = new Hotel(1150,150);
			
		PlotSquare s1 = new PlotSquare("26",l1, "Atlantic Avenue",220,44,house1,hotel1,6,3);
		squares.add(s1);
			
		//second square
		Image image2 = new ImageIcon(this.getClass().getResource("/im2g6.jpg")).getImage();
		l2.setIcon(new ImageIcon(image2));
			
		House house2 = new House(110,330,800,975,150);
		Hotel hotel2 = new Hotel(1150,150);
			
		PlotSquare s2 = new PlotSquare("27",l2,"Ventnor Avenue",220,44,house2,hotel2,6,3);
		squares.add(s2);
	
		//third square
		Image image3 = new ImageIcon(this.getClass().getResource("/im3g6.jpg")).getImage();
		l3.setIcon(new ImageIcon(image3));
						
		House house3 = new House(120,360,850,1025,150);
		Hotel hotel3 = new Hotel(1200,150);
							
		PlotSquare s3 = new PlotSquare("29",l3,"Marvin Gardens",240,48,house3,hotel3,6,3);
		squares.add(s3);
		
	}
	 
	//Creates the green group of plots
	public void makePlotSquaresGroup7(){
		
		JLabel l1 = new JLabel();
		JLabel l2 = new JLabel();
		JLabel l3 = new JLabel();
					
		//first square
		Image image1 = new ImageIcon(this.getClass().getResource("/im1g7.jpg")).getImage();
		l1.setIcon(new ImageIcon(image1));
			
		House house1 = new House(130,390,900,1100,200);
		Hotel hotel1 = new Hotel(1275,200);
			
		PlotSquare s1 = new PlotSquare("31",l1, "Pacific Avenue",300,52,house1,hotel1,7,3);
		squares.add(s1);
		
		//second square
		Image image2 = new ImageIcon(this.getClass().getResource("/im2g7.jpg")).getImage();
		l2.setIcon(new ImageIcon(image2));
			
		House house2 = new House(130,390,900,1100,200);
		Hotel hotel2 = new Hotel(1275,200);
			
		PlotSquare s2 = new PlotSquare("32",l2,"North Carolina Avenue",300,52,house2,hotel2,7,3);
		squares.add(s2);
	
		//third square
		Image image3 = new ImageIcon(this.getClass().getResource("/im3g7.jpg")).getImage();
		l3.setIcon(new ImageIcon(image3));
						
		House house3 = new House(150,450,1000,1200,200);
		Hotel hotel3 = new Hotel(1400,200);
							
		PlotSquare s3 = new PlotSquare("34",l3,"Pennsylvania Avenue",320,56,house3,hotel3,7,3);
		squares.add(s3);
		
	}
	 
	//Creates the blue group of plots
	public void makePlotSquaresGroup8(){
		
		JLabel l1 = new JLabel();
		JLabel l2 = new JLabel();
					
		//first square
		Image image1 = new ImageIcon(this.getClass().getResource("/im1g8.jpg")).getImage();
		l1.setIcon(new ImageIcon(image1));
			
		House house1 = new House(175,500,1100,1300,200);
		Hotel hotel1 = new Hotel(1500,200);
			
		PlotSquare s1 = new PlotSquare("37",l1, "Park Place",350,52,house1,hotel1,8,2);
		squares.add(s1);
			
		//second square
		Image image2 = new ImageIcon(this.getClass().getResource("/im2g8.jpg")).getImage();
		l2.setIcon(new ImageIcon(image2));
			
		House house2 = new House(200,600,1400,1700,200);
		Hotel hotel2 = new Hotel(2000,200);
			
		PlotSquare s2 = new PlotSquare("39",l2,"Boardwalk",400,52,house2,hotel2,8,2);
		squares.add(s2);	
	 
	}
	
	public void makeTheBoard(){
		
		makeSpecialSquares();
		makeCardSquares();
		makeTaxSquares();
		makeStationSquares();
		makeUtilitySquares();
		makePlotSquaresGroup1();
		makePlotSquaresGroup2();
		makePlotSquaresGroup3();
		makePlotSquaresGroup4();
		makePlotSquaresGroup5();
		makePlotSquaresGroup6();
		makePlotSquaresGroup7();
		makePlotSquaresGroup8();
		curr_pl = players.get(0);
	 
	}
	 
	public Player changeTurn(){
		
		int i;
		boolean flag = true;
		for (Player p : players){
			
			if (p.getName().equals(curr_pl.getName())){
				
				i = players.indexOf(p);
				if (i == players.size()-1)
					i=0;
				else
					i++;
				 
				do{
					if (!players.get(i).isBankrupt()){
						
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
	
}


	


