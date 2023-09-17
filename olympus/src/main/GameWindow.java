package main;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;

// Responsável por criar a janela do jogo, a tela. A GamePanel é a interface em si, enquanto a GameWindow é a resolução max
public class GameWindow {
	private JFrame jframe;
	
	public GameWindow(GamePanel gamePanel) {
		
		jframe = new JFrame();
		
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ao clicar no 'X' para fechar a interface, o programa para de rodar
		jframe.add(gamePanel);
		jframe.setResizable(false);
		jframe.pack();
		jframe.setVisible(true); // Para que a interface seja visível, por padrão é marcado como 'false
		jframe.setLocationRelativeTo(null); // Coloca o vídeo no centro da tela
		
		// Caso minimize a tela, parar as ações
		jframe.addWindowFocusListener(new WindowFocusListener() {
			
			@Override
			public void windowLostFocus(WindowEvent e) {
				gamePanel.getGame().windowFocusLost();
			}
			
			@Override
			public void windowGainedFocus(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}

}