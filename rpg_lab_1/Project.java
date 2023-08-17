package rpg_lab_1;
import java.util.Scanner;

public class Project {
	
	public static Scanner leitor = new Scanner(System.in);
	
	public void battle(Player player) {
		
		int userHp = player.getStatusInt("userHp");
		int especialCount = player.getStatusInt("especialCount");
		
		int atackChoice;
		int computerHp;
		int i = 1;
		
		while(userHp >0) {
			
			computerHp = 9 + i;
			System.out.println("-=-=-=-=-=-=-=-=-=-");
			System.out.println("ENEMY " + i + " APPEARS!");
			System.out.println("-=-=-=-=-=-=-=-=-=-\n");
			
			while(userHp > 0 && computerHp > 0) {
				Data.printStatus(userHp, computerHp, especialCount);
				atackChoice = Player.userAtack();
				switch(atackChoice) {
					case 1:
						System.out.println("User's aply a punch!");
						computerHp -= 3;
						break;
					case 2:
						if (especialCount > 0) {
							System.out.println("User's aply a Especial Attack!");
							computerHp -= 6;
							especialCount--;
						}
						else {
							System.out.println("Especial Attack is over!");
						}
						break;
					default:
						System.out.println("Invalid option.");
						break;
				}
				if(computerHp > 0) {
					atackChoice = Data.computerAtack();
					switch(atackChoice) {
						case 1:
							System.out.println("Enemy aply a punch!");
							userHp -= 2 + (int)(i/5);
							break;
						case 2:
							System.out.println("Enemey aply a kick!");
							userHp -= 3 + (int)(i/5);
							break;
						case 3:
							System.out.println("Enemey aply a Especial Atack!");
							userHp -= 6 + (int)(i/10);
							break;
					}
				}
				else {
					System.out.println("Enemy " + i + " defeat!");
					userHp += 5 + (int)(i/3);
				}
			}
			i++;
		}
	}
	
	public static Player initialDisplay() {
		
		System.out.println("-=-=-=-=-=-=-=-=-=-");
		System.out.println("\nWELCOME TO THE GUNGEON\n");
		System.out.println("-=-=-=-=-=-=-=-=-=-\n");
		
		System.out.println("ARE YOU A NEW PLAYER? (y/n)");
		char newGame = leitor.next().charAt(0);
		if(newGame == 'y') {
			Player player = new Player("null", 1, 1, "null");
			
			System.out.printf("What's your name?\n");
			player.setStatus("name");
			System.out.printf("What's your class?\n");
			player.setStatus("grade");
			
			player.setClass();
			
			 System.out.printf("%s %d %d %s\n", player.getStatusStr("name"), player.getStatusInt("userHp"), player.getStatusInt("especialCount"), player.getStatusStr("grade"));
			 char newG2ame = leitor.next().charAt(0); // Essa linha é só pra pausar
			
			
			return player;
		}
		else if(newGame == 'n') {
			return null;
		}
		else {
			return null;
		}
		
		
		
	}
	
	public static void main(String[] args) {
		Project projeto = new Project();
		int continua = 1;
		while(continua == 1) {
			
			Player player = initialDisplay();
			projeto.battle(player);
			
			System.out.println("Fim de jogo. Deseja continuar? (1) Sim (2) Não");
			continua = leitor.nextInt();
		}

	}
	
	

}
