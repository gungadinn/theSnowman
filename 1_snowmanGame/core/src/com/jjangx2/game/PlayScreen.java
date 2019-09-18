/***************************************************************
* ���α׷��� : �� ���
* ���α׷� ���� : ���� ����ϴ� �޼ҵ�
* ��(���̸�) : ����¯¯��(7��)
***************************************************************/

package com.jjangx2.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jjangx2.game.snowmanGame;

public class PlayScreen implements Screen {
	private static final Class<MapObject> Rectangle = null;

	//�츮 ���ӿ��� ���� ������. ȭ�鿡�� ���°�
	private snowmanGame game;
	private TextureAtlas atlas;
	private Snowman player;
	private dead dead;
	
	//�⺻���� ������
	private OrthographicCamera gamecam;
	private Viewport gamePort;
	private Hud hud;
	
	//Ÿ�� �� ����
	private TmxMapLoader maploader;
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	public int i=1; //�������� ���� ����
	
	//Box2d ����
	private World world;
	private Box2DDebugRenderer b2dr;
	//private B2WorldCreator creator;
	
	//�� ���������
	//private Snowman player; 
	
	public PlayScreen(snowmanGame game, int stage ) {
		Snow.Clear();
		atlas = new TextureAtlas("snowman.pack");
		this.game=game;
		
		gamecam = new OrthographicCamera();   //gamecam�� �����Ͽ� camera world�� ���Ͽ� ĳ���͸� ����ٴѴ�.
		
		//ȭ�� ũ�Ⱑ �����Ǿ ���μ��� ������ �����ϰ� ������ ������ ������ ���͹ڽ��� ���̱� ���Ͽ� FitViewport�� ����Ѵ�.
		gamePort = new FitViewport(snowmanGame.V_WIDTH/snowmanGame.PPM, snowmanGame.V_HEIGHT/snowmanGame.PPM,gamecam);
		
		hud = new Hud(game.batch);    //����, �ð�, �ܰ� ������ ���Ͽ� hub�� ����
	    
	    //���� �����ְ� �� renderer �����Ѵ�
	    maploader = new TmxMapLoader();
	    map=maploader.load("STAGE"+Integer.toString(stage)+".tmx");
	    renderer = new OrthogonalTiledMapRenderer(map, 1/snowmanGame.PPM);
	    
	    //���� ������ �� gamecam�� �߾ӿ� ��ġ�ϵ��� ����
	    gamecam.position.set(gamePort.getWorldWidth()/2,gamePort.getWorldHeight()/2,0);
	    
	    //Box2D world ����. x���� �߷��� ���� Y�� -10. bodies sleep true (boolean Ÿ��)
	    //���⿡ ������Ʈ�� ����
	    world = new World(new Vector2(0,-10),true);
	   
	    b2dr = new Box2DDebugRenderer();
	    
	    new B2worldCreator(world, map);
	    
	    player=new Snowman(world, this); //������� ���ӿ� �����

	    world.setContactListener(new WorldContactListener());
	    
	 	}
	
	public TextureAtlas getAtlas() {
		return atlas;
	}

	public void handleInput(float delta) {
		if(player.currentState != Snowman.State.DEAD) {
		
			//����ڰ� ���콺�� �����̸� ī�޶� �������� ����(world)�� ������.
		if(Gdx.input.isKeyJustPressed(Input.Keys.UP) && player.b2body.getLinearVelocity().y == 0) 
			player.b2body.applyLinearImpulse(new Vector2(0,4f), player.b2body.getWorldCenter(),true);
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x<=2)
			player.b2body.applyLinearImpulse(new Vector2(0.1f, 0),  player.b2body.getWorldCenter(),  true);
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x>= -2)
			player.b2body.applyLinearImpulse(new Vector2(-0.1f, 0),  player.b2body.getWorldCenter(),  true);
	
		Snow.Create(world, player);
		}
	}
	
	public void update(float delta) {
		handleInput(delta);  //handle ����� ���� �־��ֱ�
		
		world.step(1/60f, 6,2);  //�ѹ��� �󸶳� �̵��Ұǰ� (60 times per second) ���� : �ù����̼� �ð�/�ӵ�/��ġ����
		
		player.update(delta);
		hud.update(delta);
		
		gamecam.position.x=0; //ī�޶� ����!
		gamecam.position.set(gamePort.getWorldWidth()/2,gamePort.getWorldHeight()/2,0);

		/*		if(player.currentState!=Snowman.State.DEAD) {
		gamecam.position.x = player.b2body.getPosition().x;
	} */
			
		gamecam.update();   //��ȭ�� ���������� �˸��� ��ǥ�� gamecam�� ������Ʈ �ȴ�
		
		renderer.setView(gamecam);     //���Ӽ��󿡼� gamecam�� ���� �͵鸸 ���������� �Ѵ�
	}

	@Override
	public void render(float delta) {
		//������ ȭ�� �и�
		update(delta);
		
		//���������� clear
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		renderer.render();  //�� ����
		 
		b2dr.render(world, gamecam.combined);
		
		game.batch.setProjectionMatrix(gamecam.combined);
		game.batch.begin();
		player.draw(game.batch);
		
		game.batch.end();
		
		Snow.Draws(game.batch);
		
		//Hud ī�޶� �� ������ batch�� �׷��ֱ�
		game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
		hud.stage.draw();
	
		/*
		if(gameOver()){
            game.setScreen(new GameOverScreen(game));
            dispose();
        }*/
	}

	/*
	public boolean gameOver() {
		if(player.currentState==Snowman.State.DEAD&&player.getStateTimer()>3) {
			return true;
		}
		return false;
	} */
	
	@Override
	public void resize(int width, int height) {
		gamePort.update(width, height);
	}	
	
    public TiledMap getMap() {
    	return map;
    }
    
    public World getWorld() {
    	return world;
    }
	
	@Override
	public void dispose() {
		map.dispose();
		renderer.dispose();
		world.dispose();
		b2dr.dispose();
		hud.dispose();	
		Snow.Clear();
	}
	
	/* �� �� */
	@Override
	public void pause() {	}

	@Override
	public void resume() {	}

	@Override
	public void hide() {	}
	
	@Override
	public void show() {	}
	
}