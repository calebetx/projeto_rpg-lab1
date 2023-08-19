package main;

import java.util.Random;
import java.util.Scanner;
import entities.Player;


public class Game {
	
	public static Scanner leitor = new Scanner(System.in);
	
	private GameWindow gameWindow;
	private GamePanel gamePanel;
	
	public Game() {
		gamePanel = new GamePanel();
		gameWindow = new GameWindow(gamePanel);
		gamePanel.requestFocus(); // Define que a entrada deve ser dada no JPanel(Ao invés de colocar o input no console, coloca na interface)
		
		/*int continua = 1;
		while(continua == 1) {
			
			Player player = initialDisplay();
			battle(player);
			
			System.out.println("Fim de jogo. Deseja continuar? (1) Sim (2) Não");
			continua = leitor.nextInt();
			
		}*/
	}
	
	// Computer Attack
	public static int computerAttack() {
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
				printStatus(userHp, computerHp, especialCount);
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
					atackChoice = computerAttack();
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
			
			// System.out.printf("%s %d %d %s\n", player.getStatusStr("name"), player.getStatusInt("userHp"), player.getStatusInt("especialCount"), player.getStatusStr("grade"));
			// char newG2ame = leitor.next().charAt(0); // Essa linha é só pra pausar
			
			
			return player;
		}
		else if(newGame == 'n') {
			return null;
		}
		else {
			return null;
		}
	}
	

}
