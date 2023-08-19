package main;

import java.util.Scanner;

public class MainClass {
	
	public static Scanner leitor = new Scanner(System.in);

	public static void main(String[] args) {		
		new Game();
		
		/*int continua = 1;
		while(continua == 1) {
			
			Player player = Game.initialDisplay();
			projeto.battle(player);
			
			System.out.println("Fim de jogo. Deseja continuar? (1) Sim (2) Não");
			continua = leitor.nextInt();
			
		}*/
	}

}

/*
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
*/