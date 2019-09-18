/***************************************************************
* ���α׷��� : �޴��� ���
* ���α׷� ���� : Ű����κ��� �Է¹޾� �޴��� ȭ���� �����ش� 
* ��(���̸�) : ����¯¯��(7��)
***************************************************************/

package com.jjangx2.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
//import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Game;

public class GameScreen implements Screen //screen �������̽��� ������ GameScreen Ŭ����
{
 //   private Game game;  
    private SpriteBatch batch;

    public GameScreen (Game game) 
    {
   //     this.game = game;
        batch = new SpriteBatch();
    }
 
    //ȭ���� �������� �� ȣ��ȴ�
    public void render(float delta) 
    {
        renderClear();
    }

    private void renderClear()
    {
        Gdx.gl.glClearColor(1, 1, 1, 1);  //�Ͼ��
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    //���ø����̼��� ����� �� �ҷ����� �޼ҵ�
    public void dispose() 
    {
        batch.dispose();
    }
    
    //�Ⱦ�
    public void show() {}
    
    public void resize(int width, int height) {}
 
    public void pause() {}
 
    public void resume() {}
 
    public void hide() {}   
}