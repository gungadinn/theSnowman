/***************************************************************
* ���α׷��� : ���ӿ��� �� ���
* ���α׷� ���� : �÷��̾ dead�繰�� ���� �� ���ӿ��� �� ���
* ��(���̸�) : ����¯¯��(7��)
***************************************************************/

package com.jjangx2.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.jjangx2.game.snowmanGame;

public class GameOverScreen implements Screen{
	private Viewport viewport;
	private Stage stage;
	
	private Game game;
	
	public GameOverScreen(Game game) {
		this.game = game;
		viewport = new FitViewport(snowmanGame.V_WIDTH, snowmanGame.V_HEIGHT,new OrthographicCamera());
		stage = new Stage(viewport,( (snowmanGame)game).batch);
		
		snowmanGame.manager.get("Tido_Kang.ogg", Music.class).stop();
		snowmanGame.manager.get("OldWatch.ogg", Music.class).play();
	
		
		//gameover �ؽ�Ʈ�����
		Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(),Color.WHITE);
		Table table = new Table();
		table.center();
		
		stage.addActor(table);
		table.setFillParent(true);
		
		Label gameOverLabel = new Label("GAME OVER", font);
		Label playAgainLabel = new Label("Click to Play Again", font);
		
		table.add(gameOverLabel).expandX();
		table.row();
		table.add(playAgainLabel).expandX().padTop(10f);
		stage.addActor(table);
		
	}
	
	@Override
	public void render(float delta) 
	{
	   if(Gdx.input.justTouched()) {
		  snowmanGame.manager.get("OldWatch.ogg", Music.class).stop();
	 	  snowmanGame.manager.get("Tido_Kang.ogg", Music.class).play(); //���� �ٽ� ���� �� �뷡 �÷���
	      game.setScreen(new PlayScreen((snowmanGame)game, snowmanGame.I.StageLevel));
	      dispose();
	   }
	   Gdx.gl.glClearColor(0, 0, 0, 1);
	   Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	   stage.draw();
	}
	   
	
	public void dispose() {
		stage.dispose();
	}
	
	//�Ⱦ�
	public void resize(int width, int height) { }

	public void pause() { }

	public void resume() { }

	public void hide() { }

	public void show() { }
	
}
