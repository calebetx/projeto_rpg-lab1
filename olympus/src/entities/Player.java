package entities;

import static utilz.Constants.PlayerConstants.*;
import static utilz.HelpMethods.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import utilz.LoadSave;

public class Player extends Entity {
	private BufferedImage[][] animations; // Puxa as imagens do arquivo
	private int aniTick, aniIndex, aniSpeed = 25; // aniTick é o contador para o aniIndex(Que é a imagem do array de animação); aniSpeed define com que velocidade deve ser feita essa troca de uma imagem para a outra
	private int playerAction = IDLE; // A ação do player, nesse caso IDLE de em repouso
	private boolean moving = false, attacking = false; // Se está se movendo ou se está atacando
	private boolean left, up, right, down, jump; // Se está indo em uma direção e/ou pulando
	private float playerSpeed = 2.0f; // A velocidade do jogador
	private int[][] lvlData; // O level em si
	private float xDrawOffset = 21 * Game.SCALE; // Valor em que começa a imagem do personagem em si, fora o transparente(Margem X)
	private float yDrawOffset = 4 * Game.SCALE; // Valor em que começa a imagem do personagem em si, fora o transparente(Margem Y)
	
	// Pulo e gravidade
	private float airSpeed = 0f; // Para gerenciar a velocidade de queda ou pulo
	private float gravity = 0.04f * Game.SCALE;
	private float jumpSpeed = -2.25f * Game.SCALE;
	private float fallSpeedAfterCollision = 0.5f * Game.SCALE;
	private boolean inAir = false;
	
	// Construtor do Player, no caso ele tem a posição dele e sua largura e altura para que ele apareça(Algo como xInicial e xFinal)
	public Player(float x, float y, int width, int height) {
		super(x, y, width, height); // Puxando de 'entities' pois haverá inimigos que terão o mesmo padrão para puxar sua imagem
		loadAnimations(); // Carrega a imagem do arquivo
		initHitbox(x, y, 20 * Game.SCALE, 27 * Game.SCALE); // Define a hitbox inicial do player
	}

	// Para atualizar o local em que o player está, a animação(array de imagens) e qual é a prox animação do player
	public void update() {
		updatePos();
		updateAnimationTick();
		setAnimation();
	}

	// Desenha a hitbox do player
	public void render(Graphics g) {
		g.drawImage(animations[playerAction][aniIndex], (int)(hitbox.x - xDrawOffset), (int)(hitbox.y - yDrawOffset), width, height, null);
//		drawHitbox(g);
	}

	// Conta até certo valor, quando chega à esse valor, troca o index da imagem e caso não tenha prox imagem(fim da animação) retorna o index para 0; Fluidez da imagem
	private void updateAnimationTick() {
		aniTick++;
		if (aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= GetSpriteAmount(playerAction)) {
				aniIndex = 0;
				attacking = false;
			}

		}

	}

	// As animações do player em si. o If final é para que o contador aniTick pare de contar até que a animação seja finalizada(Percorra todas as imagens)
	private void setAnimation() {
		int startAni = playerAction;

		if (moving)
			playerAction = RUNNING;
		else
			playerAction = IDLE;

		if(inAir) {
			if(airSpeed < 0)
				playerAction = JUMP;
			else
				playerAction = FALLING;
		}
		
		if (attacking)
			playerAction = ATTACK_1;

		if (startAni != playerAction)
			resetAniTick();
	}

	// Conveniência no setAnimation
	private void resetAniTick() {
		aniTick = 0;
		aniIndex = 0;
	}

	// Altera a posição do jogador
	private void updatePos() {
		moving = false;

		if (jump)
			jump();
		if (!left && !right && !inAir) // Se não está se movendo, então não há porquê estar no método
			return;

		float xSpeed = 0;

		if (left)
			xSpeed -= playerSpeed;
		if (right)
			xSpeed += playerSpeed;

		// Para que o personagem caia quando não estiver no chão(gravidade)
		if (!inAir) 
			if(!IsEntityOnFloor(hitbox, lvlData))
				inAir = true;
		
		
		// Checando subida e descida
		if (inAir) {
			if (CanMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, lvlData)) {
				hitbox.y += airSpeed;
				airSpeed += gravity;
				updateXPos(xSpeed);
			} else {
				hitbox.y = GetEntityYPosUnderRoofOrAboveFloor(hitbox, airSpeed);
				if (airSpeed > 0)
					resetInAir();
				else // Se está batendo no teto ou chão
					airSpeed = fallSpeedAfterCollision;
				updateXPos(xSpeed);
			}

		} else
			updateXPos(xSpeed);
		moving = true;
	}

	private void jump() {
		if(inAir)
			return;
		inAir = true;
		airSpeed = jumpSpeed;
	}

	private void resetInAir() {
		inAir = false;
		airSpeed = 0;
	}

	private void updateXPos(float xSpeed) {
		if (CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData)) {
			hitbox.x += xSpeed;
		}else {
			hitbox.x = GetEntityXPosNextToWall(hitbox, xSpeed);
		}
		
	}

	// O array de imagens para animação, nesse caso tem 6x9(Não que signifique que haja 54 imagens, algumas são transparentes, mas há animação q tem 3 frames enquanto outra tem 6 frames por exemplo)
	private void loadAnimations() {

		BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);

		animations = new BufferedImage[9][6];
		for (int j = 0; j < animations.length; j++)
			for (int i = 0; i < animations[j].length; i++)
				animations[j][i] = img.getSubimage(i * 64, j * 40, 64, 40);

	}
	
	// O level em si
	public void loadLvlData(int[][] lvlData) {
		this.lvlData = lvlData;
		if(!IsEntityOnFloor(hitbox, lvlData)) 
			inAir = true;
	}

	// Isso serve em outra classe, é para que caso minimize a janela seja rodado uma instrução para que o player pare de se mover(Minimizando a janela não conta comandos então ficaria right = true sempre)
	public void resetDirBooleans() {
		left = false;
		right = false;
		up = false;
		down = false;
	}

	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}
	
	public void setJump(boolean jump) {
		this.jump = jump;
	}

}