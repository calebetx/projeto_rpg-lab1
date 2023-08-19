package main;

import java.awt.Graphics; // Para usar o 'Graphics g'
import inputs.KeyboardInputs;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
	
	public GamePanel() {
		
		addKeyListener(new KeyboardInputs());
			
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // Puxa a função paintComponent() do JPanel
		
		g.fillRect(100, 100, 200, 50); // X, Y, LARGURA, ALTURA(Obs: o X e Y são coordenadas de onde vai aparecer)
			
	}

}
