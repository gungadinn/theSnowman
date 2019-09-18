/***************************************************************
* ���α׷��� : ȭ�� ���
* ���α׷� ���� : ����ȭ��, �޴��� ȭ��, �ε�ȭ���� ����Ѵ�
* ��(���̸�) : ����¯¯��(7��)
***************************************************************/


package com.jjangx2.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainScreen implements Screen
{
   private snowmanGame game;
    
   private SpriteBatch batch; 
   private Texture introTexture;
   private Texture manualTexture;
   private Texture prepareTexture;

   private ExtendInputProcessor inputProcessor;

   private Music music;
   
   // View Division
   private enum ViewType
   {
       Intro,
       Manual,
       Prepare
   }

   private ViewType view;

   /* Setting */

   public MainScreen(snowmanGame game)
   {
       initInputProcessor();
       initImageTexture();

       this.view = ViewType.Intro;
       this.game = game;
       
	    music=snowmanGame.manager.get("Tido_Kang.ogg", Music.class);
	    music.setLooping(true);
	    music.play();
   }

   // Separate keyboard up, down.
   private void initInputProcessor()
   {
       inputProcessor = new ExtendInputProcessor();
       Gdx.input.setInputProcessor(inputProcessor);
   }

   // Set Image Texture and SpriteBatch.
   private void initImageTexture()
   {
       batch = new SpriteBatch();

       introTexture = new Texture("0_MainScreen.jpg");
        manualTexture = new Texture("1_manual.jpg");
        prepareTexture=new Texture("2_loading.jpg");
   }

   /* Render And Update */
	   
   @Override
   public void render(float delta)
   {
       clearScreen();
       renderTargetView();
   }

   // Clear Screen
   private void clearScreen()
   {
       Gdx.gl.glClearColor(1, 1, 1, 1);
       Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
   }

   // Render View Target
   private void renderTargetView()
   {
       switch (view)
       {
           case Intro:
               renderIntroView();
               break;

           case Manual:
               renderManualView();
               break;

           case Prepare:
               renderPrepareView();
               break;
       }
   }

   private boolean isIputting()
   {
       return inputProcessor.isKeyPressed() && Gdx.input.isKeyPressed(Input.Keys.SPACE);
   }

   private void drawTexture(Texture texture)
   {
       batch.begin();
        batch.draw(texture, 0, 0);
        batch.end();
   }

   /* View */

   private void renderIntroView()
   {
       drawTexture(introTexture);

       // Intro -> Manual
        if(isIputting())
       {
     	   inputProcessor.setKeyPressed(false);
            view = ViewType.Manual;
 	   }
   }

   private void renderManualView()
   {
       drawTexture(manualTexture);

        // Manual -> Prepare
       if(isIputting())
       {
           inputProcessor.setKeyPressed(false);
           view = ViewType.Prepare;
       }
   }

   private void renderPrepareView()
   {
       drawTexture(prepareTexture);

       // Prepare -> PlayScreen
       if(isIputting())
       {
           inputProcessor.setKeyPressed(false);
           game.setScreen(new PlayScreen(game, snowmanGame.I.StageLevel)); // �������� ����
       }
   }
   
   /* Delete */

   @Override
   public void dispose() {
       batch.dispose();
   }

   //�� ��
   public void show() {}
   	
   public void resize(int width, int height) {}
   
   public void pause() {}
   
   public void resume() {}
   
   public void hide() {}
}