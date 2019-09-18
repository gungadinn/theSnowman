/***************************************************************
* ���α׷��� : �������� ���
* ���α׷� ���� : ���� �� �� ȭ�鿡 ��������(����,�ܰ�)���� ����Ѵ�.
* ��(���̸�) : ����¯¯��(7��)
***************************************************************/

package com.jjangx2.game;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.jjangx2.game.snowmanGame;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Hud implements Disposable{
	public Stage stage;
	private Viewport viewport;
	
	//������
	private Integer worldTimer;
	private float timeCount; //0 �����ϸ� true
	private static Integer wstage;
	
	private Label countdownLabel;   //�ð� �ٲ��
	private static Label wstageLabel; //�ܰ� �ٲ��
	private Label timeLabel;     //TIME ���� ����
	private Label levelLabel;    //��ܰ����� ���Ұ��� (�Ƹ���)
	
	public Hud(SpriteBatch sb) {
		worldTimer=300;  //���� �����Ұǵ� �ٲٰ� ������ �ٲ㵵 ��
		timeCount=0;
		wstage=1;
		
		//HUD ī�޶� ���� �������
		viewport=new FitViewport(snowmanGame.V_WIDTH, snowmanGame.V_HEIGHT, new OrthographicCamera());
		stage=new Stage(viewport, sb);
		
		Table table = new Table();  //���⿡ Label ����
		table.top(); //Top-align
		table.setFillParent(true); //��ü�� ä������
		
		//string ���Ű� ��Ʈ�� �÷� ����
		countdownLabel=new Label(String.format("%03d", worldTimer),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		wstageLabel=new Label(String.format("%d", snowmanGame.I.StageLevel), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		timeLabel=new Label("TIME",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		levelLabel=new Label("STAGE",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		
		//�����ֱ�
		table.add(levelLabel).expandX().padTop(10);
		table.add(timeLabel).expandX().padTop(10);
		
		//�̰� �ι�°��
		table.row();
		table.add(wstageLabel).expandX();
		table.add(countdownLabel).expandX();
		
		//table ������
		stage.addActor(table);
	}
	
	public void update (float delta) {
		timeCount += delta;  //�ð� ����
		if (timeCount>=1) {
			worldTimer--;
			countdownLabel.setText(String.format("%03d", worldTimer));
			timeCount=0;
		}
	}
	
	public void dispose() {
		stage.dispose();
	}
}