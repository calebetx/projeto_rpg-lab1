package utilz;

import java.awt.geom.Rectangle2D;

import main.Game;

public class HelpMethods {

	public static boolean CanMoveHere(float x, float y, float width, float height, int[][] lvlData) {
		// Se os 4 pontos(Rectangle) da hitbox não estão sendo sobrepostos a outro objeto, então pode mover(return True)
		if (!IsSolid(x, y, lvlData))
			if (!IsSolid(x + width, y + height, lvlData))
				if (!IsSolid(x + width, y, lvlData))
					if (!IsSolid(x, y + height, lvlData))
						return true;
		return false;
	}

	private static boolean IsSolid(float x, float y, int[][] lvlData) {
		// Permite sair da tela
		if (x < 0 || x >= Game.GAME_WIDTH)
			return true;
		if (y < 0 || y >= Game.GAME_HEIGHT)
			return true;

		float xIndex = x / Game.TILES_SIZE;
		float yIndex = y / Game.TILES_SIZE;

		int value = lvlData[(int) yIndex][(int) xIndex];

		// Posições do array(imagem) 'outside_sprites' que são transparentes logo o personagem pode atravessar. Obs: value 11 é uma imagem transparente
		if (value >= 48 || value < 0 || value != 11)
			return true;
		return false;
	}
	
	// Esse método é para permitir que se mova para próximo da parede, de modo que não que fique um espaço entre a parede e a entidade
	public static float GetEntityXPosNextToWall(Rectangle2D.Float hitbox, float xSpeed) {
		int currentTile = (int) (hitbox.x / Game.TILES_SIZE);// Localizando a grade em que o player está
		if (xSpeed > 0) {
			// Right
			int tileXPos = currentTile * Game.TILES_SIZE;// A largura da grade em que o player está
			int xOffset = (int) (Game.TILES_SIZE - hitbox.width);// A distância entre o player e a "borda"
			return tileXPos + xOffset - 1;// Retorna a distância entre a tile esquerda e o player |o -1 é para que não haja sobreposição
		} else
			// Left
			return currentTile * Game.TILES_SIZE;
	}
	
	public static float GetEntityYPosUnderRoofOrAboveFloor(Rectangle2D.Float hitbox, float airSpeed) {
		int currentTile = (int) (hitbox.y / Game.TILES_SIZE);
		if (airSpeed > 0) {
			// Caindo ou tocando o chão
			int tileYPos = currentTile * Game.TILES_SIZE;
			int yOffset = (int) (Game.TILES_SIZE - hitbox.height);
			return tileYPos + yOffset - 1;
		} else
			// Jumping
			return currentTile * Game.TILES_SIZE;

	}
	
	public static boolean IsEntityOnFloor(Rectangle2D.Float hitbox, int[][] lvlData) {
		if (!IsSolid(hitbox.x, hitbox.y + hitbox.height + 1, lvlData)) // Pixel inferior esquerdo
			if (!IsSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 1, lvlData)) // Pixel inferior direito
				return false;

		return true;

	}
	
	
}