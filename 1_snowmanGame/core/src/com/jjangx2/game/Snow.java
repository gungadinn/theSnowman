/***************************************************************
* ���α׷��� : �� �ױ� ���
* ���α׷� ���� : ����ڰ� Ű���带 ������ ���� �״´�.
* ��(���̸�) : ����¯¯��(7��)
***************************************************************/

package com.jjangx2.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;

public class Snow extends Sprite
{
	private World world;
	private Body body;
	private Fixture fixture;
	
/* Instance Method */
	
	public Snow(World world, Snowman snowman) 
	{
		super(new Texture("snow.png"));
		
		this.world = world;
		SetBuildedPosition(snowman);
		SetRigidBody();
	}
	
	// ������ ��ġ�� �����մϴ�.
	private void SetBuildedPosition(Snowman snowman)
	{
		float playerX = snowman.b2body.getPosition().x;
		float playerY = snowman.b2body.getPosition().y;
		
		if(snowman.runningRight) {
			playerX += snowman.getWidth() * 0.5f;
		}
		else {
			playerX -= snowman.getWidth() + (32/snowmanGame.PPM * 0.25f);
		}
		
		playerY -= snowman.getHeight() * 0.5f - (32/snowmanGame.PPM * 0.25f);
		
		setBounds(playerX, playerY, 32/snowmanGame.PPM, 32/snowmanGame.PPM);
		CheckPosition();
	}
	
	private void CheckPosition()
	{
		Rectangle newRect = getBoundingRectangle();
		System.out.println(newRect.area());
		System.out.println(newRect.getX());
		System.out.println(newRect.getY());
		
		
		for(int i=0; i < snowList.size(); i++)
		{	 
			Rectangle predRect = snowList.get(i).getBoundingRectangle();
			
			if(predRect.contains(newRect.getX(), newRect.getY())) {
				setX(predRect.getX());
				setY(newRect.getY() + (32/snowmanGame.PPM));
			}
			
			else if(predRect.contains(newRect.getX() + (32/snowmanGame.PPM * 0.25f), newRect.getY()))
			{
				setX(predRect.getX());
				setY(newRect.getY() + (32/snowmanGame.PPM));
			}
		}
	}
	
	// ������ ����
	private void SetRigidBody()
	{
		CreateBody();
		SetFixture();
	}
	
	// ��ü�� ����
	private void CreateBody()
	{
		BodyDef bodyDef= new BodyDef();
		bodyDef.type=BodyDef.BodyType.StaticBody;
		
		Rectangle rect = getBoundingRectangle();
		
		float x =  rect.getX();
		float y = rect.getY();
		bodyDef.position.set(x + rect.getWidth() * 0.5f, y + rect.getHeight() * 0.5f);
		this.body = world.createBody(bodyDef);
	}
	
	// ������ ����
	private void SetFixture()
	{
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = GetShape();
		
		this.fixture = body.createFixture(fixtureDef);
		this.fixture.setUserData("snow");
		body.createFixture(fixtureDef);
	}
	
	// �������� ����
	private PolygonShape GetShape()
	{
		PolygonShape shape=new PolygonShape();
		Rectangle rect = getBoundingRectangle();
		shape.setAsBox(rect.getWidth() * 0.5f, rect.getHeight() * 0.5f);
		
		return shape;
	}
	
/* Static Method */
	
	// ���ӿ��� ���Ǵ� ������ �����մϴ�.
	private static ArrayList<Snow> snowList = new  ArrayList<Snow>();
	public static boolean isCreating = true;

	public static void Create(World world, Snowman player) 
	{		
		if(!isCreating) return;
		
		if(player.b2body.getLinearVelocity().y == 0 && player.b2body.getLinearVelocity().x == 0) 
		{
			if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) 
			{				
				snowList.add(new Snow(world, player));
			}		
		}
	}
	
	// ���� ȭ�鿡 �׸��ϴ�.
	public static void Draws(Batch batch) 
	{
		if(snowList.size() < 0) { return; }
		
		for(int i=0; i < snowList.size(); i++)
		{
			batch.begin();
			snowList.get(i).draw(batch);
			batch.end();
		}
	}
	
	public static void Clear() {
		snowList.clear();
	}
}
