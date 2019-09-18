/***************************************************************
* ���α׷��� : ���� Ŭ���� ��
* ���α׷� ���� : 8�ܰ���� �����ϸ� ����ȭ���� �����ش� 
* ��(���̸�) : ����¯¯��(7��)
***************************************************************/


package com.jjangx2.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jjangx2.game.snowmanGame;

public class GameClearScreen implements Screen
{

	private SpriteBatch batch;
	private snowmanGame game;
	
	private Texture clearTexture;
	
	 public GameClearScreen () 
    {
		game = snowmanGame.I;
        batch = new SpriteBatch();
        
        clearTexture = new Texture("2_loading.jpg");
    }
	

	@Override
	public void render(float delta) 
	{
		renderClear();	
		
		batch.begin();
        batch.draw(clearTexture, 0, 0);
        batch.end();
        
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
        	//Gdx.app.exit();
        }
	}
	
	private void renderClear()
    {
        Gdx.gl.glClearColor(1, 1, 1, 1);  //�Ͼ��
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

	public void dispose() {
		 batch.dispose();	
	}

	//�Ⱦ�
	public void show() {	}

	public void resize(int width, int height) {	}

	public void pause() {	}

	public void resume() {	}

	public void hide() {	}
}
