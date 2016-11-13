package ru.progclub.snake;

import javax.swing.JFrame;

public class GameWindow extends JFrame {
	
	/**
	 * Конструктор окна по умолчанию
	 */
	public GameWindow(){
		// создаем игровое поле
		GameField fld = new GameField();
		getContentPane().add(fld);	// добавляем поле в текущее окно
		
		setTitle("Змейка");			// ставим заголовок
		
		setBounds(0,0,800,650);		// устанавливаем положение окна и его размеры
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// закрыть приложение при закрытии окна
		
		setResizable(false);	// запретить изменять размер окна
		
		setVisible(true);		// показать текущее окно
	}
	
}
