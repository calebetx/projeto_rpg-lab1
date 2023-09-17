package gamestates;

// Para decidir o que "desenhar" na tela, o menu ou o jogo em si

public enum Gamestate {

	PLAYING, MENU;
	
	public static Gamestate state = MENU;
	
}
