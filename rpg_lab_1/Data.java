package rpg_lab_1;
import java.util.Random;

public class Data {	
	
	// Computer
	public static int computerAtack() {
		Random gerador = new Random();
		return gerador.nextInt(3)+1;
	}
	
	
	// Display
	public static void printStatus(int userHp, int computerHp, int especialCount){
		System.out.println("=================");
		System.out.println(" - User HP: " + userHp);
		System.out.println(" - Computer Hp: " + computerHp);
		System.out.println(" - Especial Count: " + especialCount);
		System.out.println("=================");
	}

}
