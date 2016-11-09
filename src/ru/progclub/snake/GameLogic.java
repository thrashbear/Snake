package ru.progclub.snake;

public class GameLogic {
	public static final int DEFAULT_WIDTH = 30;	// стандартна€ ширина пол€
	public static final int DEFAULT_HEIGHT = 30;	// стандартна€ высота пол€
	
	
	private int[][] field;	// массив, по которому перемещаетс€ наша змейка
	
	/**
	 *  онструктор дефолтный
	 */
	public GameLogic(){
		field = new int[DEFAULT_WIDTH][DEFAULT_HEIGHT];	// создаем стандартное поле
	}
	
	/**
	 *  оструктор, создающей поле указанной ширины и высоты
	 * @param width ширина пол€
	 * @param height высота пол€
	 */
	public GameLogic(int width, int height){
		field = new int[width>0 ? width : DEFAULT_WIDTH][height>0 ? height : DEFAULT_HEIGHT];
	}
	
	/**
	 * √енерирует пищу дл€ змейки
	 */
	private void generateFeed(){
		int x,y;	// переменные координат
		
		while(true){
			x=(int)(Math.random()*DEFAULT_WIDTH);	// рандомим х в пределах 0-30
			y=(int)(Math.random()*DEFAULT_HEIGHT);	// рандомим у в пределах 0-30
			
			if(field[y][x] == 0){	// если €чейка пуста
				field[y][x] = -1;	// ставим в нее еду
				break;				// и ломаем цикл
			}
		}
	}
	
	public void start(){
		this.clear();
		
		field[DEFAULT_WIDTH/2][DEFAULT_HEIGHT/2] = 1;
		
		generateFeed();
		
	}
	
	/**
	 * ќчищает игровое поле
	 */
	private void clear(){
		for(int i=0; i<DEFAULT_HEIGHT; i++){
			for(int j=0; j<DEFAULT_WIDTH; j++){
				field[i][j] = 0;
			}
		}
	}
	
	/**
	 * ¬озвращает значение в массиве по координатам
	 * @param col
	 * @param row
	 * @return
	 */
	public int getValue(int col, int row){
		return this.field[col][row];
	}
}
