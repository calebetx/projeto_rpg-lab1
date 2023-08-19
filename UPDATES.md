16/08/2023

1 - Separei o projeto em 3 classes, está na pasta 'rpg_lab_1';
2 - Criei a função initialDisplay() para ter o menu inicial assim que executar;
3 - Implementei algumas variáveis no Herói;

Em progresso a seguir: Implementar as classes do herói(Vantagens/Características/Diferencial)


OBSERVAÇÕES:

- Em Java, para comparar strings, utiliza-se '.equals()'. Da pra ver isso na função 'setClass'
- Para ler uma entrada do tipo 'Char', é possível utilizar '.next().charAt(0)'. Ex: 'char newGame = leitor.next().charAt(0);'
--------------------- / ---------------------------------
19/08/2023

1 - Divisão dos pacotes, agora existe 'main' e 'entities' : Onde se concentra o "run" / Entidades, player, inimigo, objeto etc
2 - Nova classe 'GameWindow', nela vão conter as propriedades da interface eqa tamanho da tela;
3 - Nova classe 'GamePanel', nela vai ser definido o que aparece na tela. Enquanto a gameWindow da propriedades para a janela, como o tamanho, a GamePanel que irá "desenhar";
4 - O main, que vai rodar o jogo, está no MainClass agora
5 - Novo pacote 'inputs' e classe 'KeyboardInputs'

Em progresso a seguir:
º Definir as entradas(Em progresso)
º Implementar menu inicial
º Implementar as classes do herói(Vantagens/Características/Diferencial)

OBSERVAÇÕES:
- [Importante] O 'super' em um método chama a função qual a classe está se extendendo
Exemplo: 
'public class GamePanel extends JPanel'
E puxo 'super.paintComponent(g)'
Esse método está sendo chamado na 'JPanel'.
- CTRL + SPACE em uma linha incompleta mostra as possíveis sugestões
- 'implements' é semelhante ao extends, só que chama uma 'interface' e não uma 'class'

- Para criação da interface utiliza-se o import 'swing.JFrame';
- 'Graphics g' permite desenhar (import.awt.Graphics)
- 'g.drawRect' desenha sem preencher o meio;
- 'g.fillRect' desenha preenchendo o meio;