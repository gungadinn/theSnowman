/***************************************************************
* ���α׷��� : ����ȭ�� ���
* ���α׷� ���� : ���� ó�� ����Ǵ� �޼ҵ�� ȭ�� ����� ����ϴ� �޼ҵ带 �ҷ��´�
* ��(���̸�) : ����¯¯��(7��)
***************************************************************/
package com.jjangx2.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class snowmanGame extends Game 
{
	public static snowmanGame I;
	public static int StageLevel = 1; // �������� ǥ�� ( �������� ������ door.java)
	public static float[][] StartPosition = new float[8][2]; // ������� �ʱ� ��ġ
	
	public static final int V_WIDTH=1280;
	public static final int V_HEIGHT=720;
	public static final float PPM=100; //���ʹ� �ȼ�!
	
	public static final short NOTHING_BIT=0;
	public static final short GROUND_BIT=1;
	public static final short SNOWMAN_BIT=2;
	public static final short BRICK_BIT=4;  //���̶� �ε����� ġ����
	public static final short SPRING_BIT=8;  //�������̶� �ε����� ū�ɷ� �ٲ��ٰ���
	public static final short DESTROYED_BIT = 16;
	public static final short OBJECT_BIT = 32;  
	public static final short DEAD_BIT= 64; //enemy
	
	public SpriteBatch batch;
	public static AssetManager manager;
	
	//���ø����̼��� ������� �� ���� ó�� �ҷ����� �޼ҵ�
	public void create () 
	{
		I = this;
		
		batch=new SpriteBatch();
		manager=new AssetManager();
		manager.load("Tido_Kang.ogg", Music.class);
		manager.load("OldWatch.ogg", Music.class);
		manager.finishLoading();
		
		SetStartPosition();
		setScreen(new MainScreen(this));  //MainScreen���� �Ѿ
	}

	// ������� �������� ��ġ�մϴ�.
		private void SetStartPosition()
		{
//			// state 1 
//			StartPosition[0][0] = 0.7206954f; // x
//			StartPosition[0][1] = 0.7349999f; // y
			
			// state 1 
			StartPosition[0][0] = 9.7206954f; // x
			StartPosition[0][1] = 5.7349999f; // y
			
			// state 2
			StartPosition[1][0] = 9.3206954f; // x
			StartPosition[1][1] = 6.7349999f; // y
			
//			// state 2
//			StartPosition[1][0] = 0.7206954f; // x
//			StartPosition[1][1] = 0.7349999f; // y
			
			
//			// state 3
//			StartPosition[2][0] = 3.0f; // x
//			StartPosition[2][1] = 2.7349999f; // y
		
			// state 3
			StartPosition[2][0] = 9.0f; // x
			StartPosition[2][1] = 5.7349999f; // y
			
			// state 4
			StartPosition[3][0] = 10.2206954f; // x
			StartPosition[3][1] = 6.7349999f; // y
		
			// state 5
			StartPosition[4][0] = 2.7206954f; // x
			StartPosition[4][1] = 5.7349999f; // y
	
			// state 6
			StartPosition[5][0] = 0.7206954f; // x
			StartPosition[5][1] = 2.7349999f; // y
	
			// state 7
			StartPosition[6][0] = 0.7206954f; // x
			StartPosition[6][1] = 0.7349999f; // y
	
			// state 8
			StartPosition[7][0] = 0.7206954f; // x
			StartPosition[7][1] = 0.7349999f; // y
	}
		
		public static boolean IsStartPositionInStage()
		{
			if(StartPosition[StageLevel-1][0] != 0 && StartPosition[StageLevel-1][1] != 0) {
				
				System.out.println("startposition true");
				return true;

		}
			
			System.out.println("startposition false");
			return false;
		}
		
	//���ø����̼��� ��ü ������ �� �� �ҷ����� �޼ҵ�
	public void render () { 
		super.render();   //�� ��쿡�� game.render(); �� ����
	}

	//���ø����̼��� ����� �� �ҷ����� �޼ҵ�
	public void dispose () {
		super.dispose();
		manager.dispose();
		batch.dispose();
	}
}
