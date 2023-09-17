package main;

import java.awt.Dimension;
import java.awt.Graphics; // Para usar o 'Graphics g'
import inputs.KeyboardInputs;
import inputs.MouseInputs;
import javax.swing.JPanel;
import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;

public class GamePanel extends JPanel {
	
	private MouseInputs mouseInputs; // Criado para usar a mesma classe para o mouseListener e mouseMotionListener
	private Game game;
	
	public GamePanel(Game game) {
		this.game = game;
		
		setPanelSize(); // Define o tamanho da tela
		
		// Entradas de Teclado/Mouse
		addKeyListener(new KeyboardInputs(this));
		
		MouseInputs mouseInputs = new MouseInputs(this);
		addMouseListener(mouseInputs); // O mouseListener é para click, pressionar o click ou soltar o click
		addMouseMotionListener(mouseInputs); // O mouseMotionListener é para mover/arrastar
			
	}
	

	private void setPanelSize() {
		Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
		setPreferredSize(size);
		System.out.println("size : " + GAME_WIDTH + " :" + GAME_HEIGHT);
	}
	
	public void updateGame() {

	}
	
	// Método para desenhar o vídeo
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // Puxa a função paintComponent() do JPanel
		game.render(g);
	}
	
	public Game getGame() {
		return game;
	}

}
