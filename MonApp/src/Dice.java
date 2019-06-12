import java.util.Random;

public class Dice {
	private int dice1;
	private int dice2;
	private Random r = new Random();
	//int d[] = {3,4, 1,2,  2,2, 4,2,  3,3,2 ,2, 2,6, 1,1,1,2,3,6,5,2,3,6,5,4,1,2,3,6,5,2,1};
	//int k =0;
	
	public void throwDice()
	{
		dice1= r.nextInt(6) + 1;
		dice2= r.nextInt(6) + 1;
		///dice1=d[k];
		//dice2=d[k+1];
		//k=k+2;
		//System.out.println(dice1 + " " + dice2);
		
	
	}

	public int getDice1() {
		return dice1;
	}

	public int getDice2() {
		return dice2;
	}
	
	

}
