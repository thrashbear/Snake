package ru.progclub.snake;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class GameField extends JPanel {
	private GameLogic game;	// игровая логика
	
	private Timer tmDraw;	// таймер отрисовки 
	
	private Image bckgrnd, body, head, feed, gameOver;	// спрайты
	
	private JLabel lbScore;	// метка с заработанными очками
	
	private JButton btnNew, btnExit;	// кнопки начала новой игры и выхода
	
	public GameField(){
		loadResources();	// загружаем спрайты и иные ресурсы
		
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
		
		setLayout(null);
		
		
		// создаем надпись с очками
		lbScore = new JLabel("Счет: -");	// инициализируем и заполняем начальное значение
		lbScore.setForeground(Color.WHITE);	// устанавливаем фон
		lbScore.setFont(new Font("serif", 0, 30));	// шрифт
		lbScore.setBounds(630, 200, 150, 50);	// куда расположится и размер
		add(lbScore); // добавляем надпись к полотну
		
		
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
			}	
			
		});
		add(btnNew);	// добавляем кнопку в полотно
		
		
		// кнопка выхода
		btnExit = new JButton();
		
		
		
		
		
		
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
	
	public void paintComponent(Graphics gr){
		super.paintComponent(gr);
		
		gr.drawImage(bckgrnd, 0, 0, 800, 600, null);
		
		for(int i=0; i < game.DEFAULT_HEIGHT; i++){		// height = строка
			for(int j = 0; j< game.DEFAULT_WIDTH; j++){	// width = столбец
				
				if(game.getValue(j, i) != 0){
					if(game.getValue(j, i) == 1){
						gr.drawImage(head, 10+j*20, 10+i*20, 20, 20, null);
					}
					else if(game.getValue(j, i) == -1){
						gr.drawImage(feed, 10+j*20, 10+i*20, 20, 20, null);
					}
				}				
			}
		}
		gr.setColor(Color.BLUE);
		for(int i=0; i<=game.DEFAULT_WIDTH; i++){
			gr.drawLine(10+i*20, 10, 10+i*20, 610);
			gr.drawLine(10, 10+i*20, 610, 10+i*20);
		}
	}


}
