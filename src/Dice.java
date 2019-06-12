import java.util.Random;

public class Dice {
	
	private int dice1;
	private int dice2;
	private Random r = new Random();
    
	public void throwDice(){
		
		dice1= r.nextInt(6) + 1;
		dice2= r.nextInt(6) + 1;
	
	}

	public int getDice1() {
		return dice1;
	}

	public int getDice2() {
		return dice2;
	}
	
}
