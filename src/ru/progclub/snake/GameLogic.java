package ru.progclub.snake;

public class GameLogic {
	public static final int DEFAULT_WIDTH = 30;	// стандартная ширина поля
	public static final int DEFAULT_HEIGHT = 30;	// стандартная высота поля
	
	private int direction;	// направление движения головы 0-л; 1-в; 2-п;3-н
	
	private int gX, gY;	// координаты головы
	
	private int score;	// количество очков
	
	private int[][] field;	// массив, по которому перемещается наша змейка
	
	/**
	 * Конструктор дефолтный
	 */
	public GameLogic(){
		field = new int[DEFAULT_WIDTH][DEFAULT_HEIGHT];	// создаем стандартное поле
	}
	
	/**
	 * Генерирует пищу для змейки
	 */
	private void generateFeed(){
		int x,y;	// переменные координат
		
		while(true){
			x=(int)(Math.random()*DEFAULT_WIDTH);	// рандомим х в пределах 0-30
			y=(int)(Math.random()*DEFAULT_HEIGHT);	// рандомим у в пределах 0-30
			
			if(field[y][x] == 0){	// если ячейка пуста
				field[y][x] = -1;	// ставим в нее еду
				break;				// и ломаем цикл
			}
		}
	}
	
	public void start(){
		this.clear();		// очищаем игровое поле
		
		this.direction=0;	// устанавливаем направление движения влево
		
		this.score = 0;		// обнуляем счет
		
		this.gX = this.gY = 15;	// ставим координату головы
		
		this.field[15][15] = 1;	// спавним голову
		
		generateFeed();	// создаем еду
		
	}
	
	/**
	 * Перемещает голову змейки по игровому полю
	 */
	public void moveHead(){
		this.field[gX][gY] = 0;	// сразу удаляем голову с текущего положения
		
		
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
		
		if(field[gY][gX]==-1){	// если голова змейки "наехала" на еду
			generateFeed();	// создаем новую еду
			score+=10;		// и добавляем очки
		}
		
		field[gY][gX] = 1;	// и ставим в полученные координаты голову
		
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
		return this.field[col][row];
	}
	
	/**
	 * Устанавливает направление движения
	 * @param dir
	 */
	public void setDirection(int dir){
		direction = dir>=0 && dir<=3 ? dir : direction;
	}
	
	/**
	 * Возвращает количество набранных очков
	 * @return
	 */
	public int getScore(){
		return this.score;
	}
}
