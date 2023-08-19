package main;
import javax.swing.JFrame;

public class GameWindow {
	private JFrame jframe;
	
	public GameWindow(GamePanel gamePanel) {
		
		jframe = new JFrame();
		
		jframe.setSize(400, 400); // Define LARGURA, ALTURA
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ao clicar no 'X' para fechar a interface, o programa para de rodar
		jframe.add(gamePanel);
		jframe.setLocationRelativeTo(null);
		jframe.setVisible(true); // Para que a interface seja visível, por padrão é marcado como 'false'
	}

}