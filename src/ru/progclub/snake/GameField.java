package ru.progclub.snake;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class GameField extends JPanel {
	private GameField gFld;
	private GameLogic game;	// игровая логика
	
	private Timer tmDraw, tmUpdate;	// таймер отрисовки и обновления экрана
	
	private Image bckgrnd, body, head, feed, gameOver;	// спрайты
	
	private JLabel lbScore;	// метка с заработанными очками
	
	private JButton btnNew, btnExit;	// кнопки начала новой игры и выхода
	
	
	private class myKeyLs implements KeyListener{

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
			switch(e.getKeyCode()){
			case KeyEvent.VK_LEFT:
				game.setDirection(0);
				break;
			case KeyEvent.VK_UP:
				game.setDirection(1);
				break;
			case KeyEvent.VK_RIGHT:
				game.setDirection(2);
				break;
			case KeyEvent.VK_DOWN:
				game.setDirection(3);
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {}

		@Override
		public void keyTyped(KeyEvent e) {}		
	}	
	
	
	public GameField(){
		gFld = this;
		loadResources(); // загружаем спрайты и иные ресурсы
		
		// добавляем наш обработчик клавиатуры
		this.addKeyListener(new myKeyLs());
		this.setFocusable(true);
		
		// инициализируем переменную game и запускаем игровую логику		
		game = new GameLogic();
		game.start();
		
		
		
		
		//создаем и запускаем таймер отрисовки
		tmDraw = new Timer(20, new ActionListener(){
			public void actionPerformed(ActionEvent e){
				repaint();
			}
		});
		tmDraw.start();
		
		// создаем надпись с очками
				lbScore = new JLabel("Счет: -");	// инициализируем и заполняем начальное значение
				lbScore.setForeground(Color.BLACK);	// устанавливаем фон
				lbScore.setFont(new Font("serif", 0, 30));	// шрифт
				lbScore.setBounds(630, 200, 150, 50);	// куда расположится и размер
				add(lbScore); // добавляем надпись к полотну
		
		tmUpdate = new Timer(100, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(!game.endGame){
					game.move();
				}
				
				lbScore.setText("Счет: "+game.getScore());
			}
			
		});
		tmUpdate.start();
		
		setLayout(null);
		
		
		
		
		
		// кнопка новой игры
		btnNew = new JButton();			// инициализируем
		btnNew.setText("New Game");		// задаем текст кнопки
		btnNew.setForeground(Color.BLUE);	// цвет
		btnNew.setFont(new Font("serif", 0, 20));	// шрифт
		btnNew.setBounds(630, 30, 150, 50);			// где находится и размер
		btnNew.addActionListener(new ActionListener() { // навешиваем АЛ

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				game.start();	// который запускает игру
				
				btnNew.setFocusable(false);
				btnExit.setFocusable(false);
				gFld.setFocusable(true);
			}	
			
		});
		add(btnNew);	// добавляем кнопку в полотно
		
		
		// кнопка выхода
		btnExit = new JButton();
		btnExit.setText("Выход");
		btnExit.setForeground(Color.RED);
		btnExit.setFont(new Font("serif", 0, 20));
		btnExit.setBounds(630, 100, 150, 50);
		btnExit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
			
		});
		this.add(btnExit);
		
		
		
		
		
		
	}
	
	/**
	 * Загружает необходимые для игры ресурсы
	 */
	private void loadResources(){
		try{
			// загружаем в память спрайты
			this.bckgrnd = ImageIO.read(new File("sprites\\bg.png"));
			this.body = ImageIO.read(new File("sprites\\body.png"));
			this.head = ImageIO.read(new File("sprites\\head.png"));
			this.feed = ImageIO.read(new File("sprites\\feed.png"));
			this.gameOver = ImageIO.read(new File("sprites\\gameover.png"));
		} catch (IOException ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * Отрисовывает графические компоненты игры
	 */
	public void paintComponent(Graphics gr){
		super.paintComponent(gr);
		
		// отрисовываем фон
		gr.drawImage(bckgrnd, 0, 0, 800, 650, null);
		
		for(int i=0; i < game.DEFAULT_HEIGHT; i++){		// height = строка
			for(int j = 0; j< game.DEFAULT_WIDTH; j++){	// width = столбец
				
				if(game.getValue(j, i) != 0){
					if(game.getValue(j, i) == 1){
						// рисуем голову
						gr.drawImage(head, 10+j*20, 10+i*20, 20, 20, null);
					} else if(game.getValue(j, i) == -1){
						// рисуем еду
						gr.drawImage(feed, 10+j*20, 10+i*20, 20, 20, null);
					} else if(game.getValue(j, i) > 1){
						gr.drawImage(body, 10+j*20, 10+i*20, 20, 20, null);
					}
				}				
			}
		}
		gr.setColor(Color.BLUE);
		for(int i=0; i<=game.DEFAULT_WIDTH; i++){
			// этим циклом рисуем сетку на игровом поле
			gr.drawLine(10+i*20, 10, 10+i*20, 610);
			gr.drawLine(10, 10+i*20, 610, 10+i*20);
		}
		
		if(game.endGame){
			gr.drawImage(gameOver, 250, 200, 200, 100, null);
		}
	}


}
