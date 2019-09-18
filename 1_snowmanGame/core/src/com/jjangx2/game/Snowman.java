/***************************************************************
* ���α׷��� : ����� ����
* ���α׷� ���� : Snowman Ŭ������ ĳ���͸� ����� �̵���Ų��  
* ��(���̸�) : ����¯¯��(7��)
***************************************************************/


package com.jjangx2.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;  //world Ŭ������ ���� ��ü, ���� �ù����̼� ���� �������ش�
import com.badlogic.gdx.utils.Array;

public class Snowman extends Sprite{
	public enum State{FALLING, JUMPING,STANDING,RUNNING, DEAD};
	public State currentState;
	public State previousState;
	
	public World world;  //������� ������ ����
	public Body b2body;
	
	public boolean isDead;
	
	private TextureRegion snowmanStand;
	private TextureRegion snowmanDead;
	
	private Animation snowmanRun;
	private Animation snowmanJump;
	private float stateTimer;
	public boolean runningRight;
	private boolean snowmanIsDead;
	
	private Game game;
	
	//����� ����
	public Snowman(World world, PlayScreen screen) {
		super(screen.getAtlas().findRegion("snowman",1));
		this.world = world;
		currentState = State.STANDING;
		previousState = State.STANDING;
		stateTimer = 0;
		runningRight = true;
				
		Array<TextureRegion> frames = new Array<TextureRegion>();
		for(int i=0;i<2;i++)
			frames.add(new TextureRegion(getTexture(), i * 48, 0, 48, 96));
		snowmanRun = new Animation(0.1f, frames);
		frames.clear();
		
		snowmanStand = new TextureRegion(getTexture(),0,0,48,96);
		
		defineSnowman();  //����� ����
		setBounds(0,0,48/snowmanGame.PPM,96/snowmanGame.PPM);  //����� ũ�� ����
		setRegion(snowmanStand);
	}
	
	public void update(float delta) 
	{
		// get Position
		if(Gdx.input.isKeyJustPressed(Input.Keys.A)) {
			System.out.println(b2body.getPosition().x);
			System.out.println(b2body.getPosition().y);
		}
		
		
		/*if (!isDead()) {
            die();
        }*/
		
		//else
			setPosition(b2body.getPosition().x-getWidth()/2, b2body.getPosition().y-getHeight()/2+0.05f);
		
		setRegion(getFrame(delta));
	}
	
	public TextureRegion getFrame(float delta) {
		currentState = getState();
		
		TextureRegion region;
		switch(currentState) {
		case DEAD:
			region = snowmanDead;
			break;
		case RUNNING:
			region = (TextureRegion) snowmanRun.getKeyFrame(stateTimer, true);
			break;
		case STANDING:
		default:
			region = snowmanStand;
			break;	
		}
		
		// ��������
		if ((b2body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()) {
			region.flip(true, false);
			runningRight = false;
		}
		// ����������
		else if((b2body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()) {
			region.flip(true, false);
			runningRight = true;
		}
		
		stateTimer = currentState == previousState ? stateTimer + delta : 0;
		previousState = currentState;
		return region;
	}
	
	public State getState() {
		 if(b2body.getLinearVelocity().x != 0)
			return State.RUNNING;
		 else if (snowmanIsDead)
				return State.DEAD;
		else
			return State.STANDING;
	}
	
	//����� ũ��� ��ġ ����
	public void defineSnowman() {
		BodyDef bdef=new BodyDef();
		
		// ��ġ�� �����Ǿ� �ִ� ���
		if(snowmanGame.IsStartPositionInStage()) 
		{
			int stage = snowmanGame.I.StageLevel;
			
			float x = snowmanGame.I.StartPosition[stage-1][0];
			float y = snowmanGame.I.StartPosition[stage-1][1];
			
			bdef.position.set(x,y);
		}
		else {
			bdef.position.set(32/snowmanGame.PPM, 32/snowmanGame.PPM);
		}
		
		bdef.type= BodyDef.BodyType.DynamicBody;
		b2body=world.createBody(bdef);
		
		FixtureDef fdef=new FixtureDef();
		PolygonShape shape=new PolygonShape();
		shape.setAsBox(20/snowmanGame.PPM, 36/snowmanGame.PPM);
		
		//categoryBits=�浹���ֺ�Ʈ   �⺻
		fdef.filter.categoryBits=snowmanGame.SNOWMAN_BIT;
		
		//������� ���� �ε��� �� �ִ°�! (maskbits=�浹 ����ũ ��Ʈ. ����� �浹�� ���� ����� ���� ����)
		fdef.filter.maskBits =snowmanGame.NOTHING_BIT|snowmanGame.GROUND_BIT| snowmanGame.SNOWMAN_BIT | 
				snowmanGame.BRICK_BIT| snowmanGame.SPRING_BIT|
				snowmanGame.DESTROYED_BIT|snowmanGame.OBJECT_BIT| snowmanGame.DEAD_BIT;
		
		fdef.shape=shape;
		b2body.createFixture(fdef);
		
		EdgeShape bottom = new EdgeShape();
		bottom.set(new Vector2(-20/snowmanGame.PPM, -40/snowmanGame.PPM),new Vector2(20/snowmanGame.PPM, -40/snowmanGame.PPM));
		fdef.shape=bottom;
		fdef.isSensor=true;
		
		b2body.createFixture(fdef).setUserData("bottom");
	}
	
	public boolean isDead() {
	return snowmanIsDead;
}



public void die() {
	if(!isDead()) {
		//GameOverScreen gameover;
		//this.game = game;
		isDead=true;
		Filter filter = new Filter();
		filter.maskBits = snowmanGame.NOTHING_BIT;
		
		 for (Fixture fixture : b2body.getFixtureList()) {
                fixture.setFilterData(filter);
            }
		 
		b2body.applyLinearImpulse(new Vector2(0,4f), b2body.getWorldCenter(),true);
		
		//GameOverScreen gameover = new GameOverScreen(game);
	}
}

public float getStateTimer() {
	return stateTimer;
}

public void hit(dead dead) {
	
	if(snowmanIsDead) {
		snowmanIsDead = true;
		Filter filter = new Filter();
		filter.maskBits = snowmanGame.NOTHING_BIT|snowmanGame.DEAD_BIT;//|snowmanGame.GROUND_BIT|
				//snowmanGame.BRICK_BIT|snowmanGame.SNOWMAN_BIT;
		for(Fixture fixture: b2body.getFixtureList())
			fixture.setFilterData(filter);
		b2body.applyLinearImpulse(new Vector2(0,4f), b2body.getWorldCenter(), true);
	}
}
}
