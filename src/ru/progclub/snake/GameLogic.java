package ru.progclub.snake;

public class GameLogic {
	public static final int DEFAULT_WIDTH = 30;	// стандартная ширина поля
	public static final int DEFAULT_HEIGHT = 30;	// стандартная высота поля
	
	public boolean endGame;
	
	private int direction, newDirection;	// направление движения головы 0-л; 1-в; 2-п;3-н
	
	private int gX, gY;	// координаты головы
	
	private int score;	// количество очков
	
	private int length;
	
	private int[][] field;	// массив, по которому перемещается наша змейка
	
	/**
	 * Конструктор дефолтный
	 */
	public GameLogic(){
		field = new int[DEFAULT_WIDTH][DEFAULT_HEIGHT];	// создаем стандартное поле
	}
	
	private void turn(){
		if(Math.abs((newDirection-direction))!=2){
			direction = newDirection;
		}
	}
	
	/**
	 * Генерирует пищу для змейки
	 */
	private void generateFeed(){
		int x,y;	// переменные координат
		
		while(true){
			x=(int)(Math.random()*DEFAULT_WIDTH);	// рандомим х в пределах 0-30
			y=(int)(Math.random()*DEFAULT_HEIGHT);	// рандомим у в пределах 0-30
			
			if(field[x][y] == 0){	// если ячейка пуста
				field[x][y] = -1;	// ставим в нее еду
				break;				// и ломаем цикл
			}
		}
	}
	
	public void start(){
		
		this.clear();		// очищаем игровое поле
		
		
		
		this.direction=1;	// устанавливаем направление движения влево
		
		this.score = 0;		// обнуляем счет
		
		
		
		this.field[14][14] = 1;	// спавним голову
		this.field[14][15] = 2;
		this.field[14][16] = 3;
		
		this.length = 3;
		
		this.gX = this.gY = 14;	// ставим координату головы
		
		this.endGame = false;
		
		generateFeed();	// создаем еду
		
		
		
	}
	
	public void move(){
		int flag = moveHead();
		
		if(flag == 3) endGame = true;
		
		for(int i=0; i<30; i++){
			for(int j=0; j<30; j++){
				if(field[i][j]>0) field[i][j]++;
				else if(field[i][j] == -2) field[i][j] = 1;
				
				if(flag!=1){
					if(field[i][j] == (length+1)) field[i][j] = 0;
				}
			}
		}
		
		if (flag == 1){
			length++;
			generateFeed();
			score+=10;
		}
		turn();
	}
	
	/**
	 * Перемещает голову змейки по игровому полю
	 */
	public int moveHead(){
		switch(direction){	// и в зависимости от направления меняем координаты
			case 0:	// для движения влево
				// если упираемся в границу экрана, переносим голову в правый край
				gX = gX-1 >= 0 ? gX-1 : 29;	
				break;
			case 1:	// для движения вверх
				// если упираемся в границу экрана, переносим голову в нижний край
				gY = gY-1 >= 0 ? gY-1 : 29;
				break;
			case 2:	// для движения вправо
				// если упираемся в границу экрана, переносим голову в левый край
				gX = gX+1 <= 29 ? gX+1 : 0;
				break;
			case 3:	// для движения вниз
				// если упираемся в границу экрана, переносим голову в верхний край
				gY = gY+1 <= 29 ? gY+1 : 0;
				break;
		}
		
		int result = 0;
		
		if(field[gY][gX]==-1) result = 1;
		else if(field[gY][gX]==0) result = 2;
		else if(field[gY][gX]>0) result = 3;
		
		field[gY][gX]= -2;
		
		return result;
		
	}
	
	/**
	 * Очищает игровое поле
	 */
	private void clear(){
		for(int i=0; i<DEFAULT_HEIGHT; i++){
			for(int j=0; j<DEFAULT_WIDTH; j++){
				field[i][j] = 0;
			}
		}
	}
	
	/**
	 * Возвращает значение в массиве по координатам
	 * @param col
	 * @param row
	 * @return
	 */
	public int getValue(int col, int row){
		return this.field[row][col];
	}
	
	/**
	 * Устанавливает направление движения
	 * @param dir
	 */
	public void setDirection(int dir){
		newDirection = dir;
	}
	
	/**
	 * Возвращает количество набранных очков
	 * @return
	 */
	public int getScore(){
		return this.score;
	}
}
