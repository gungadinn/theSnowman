/***************************************************************
* ���α׷��� : ���� ���� ����
* ���α׷� ���� : ����(world)�� �����ؾ� �ϴ� �͵��� �������ش�. (��, ���� ���..)
* ��(���̸�) : ����¯¯��(7��)
***************************************************************/

package com.jjangx2.game;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class B2worldCreator {
	public B2worldCreator(World world, TiledMap map) {
		BodyDef bdef=new BodyDef();
	    PolygonShape shape = new PolygonShape();
	    FixtureDef fdef = new FixtureDef();
	    Body body;
	    
	    //���� ���� ����
	    for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class))
	    {
	    	Rectangle rect=((RectangleMapObject) object).getRectangle();
	    	
	    	bdef.type=BodyDef.BodyType.StaticBody;
	    	bdef.position.set((rect.getX()+rect.getWidth()/2)/snowmanGame.PPM,(rect.getY()+rect.getHeight()/2)/snowmanGame.PPM);
	    	
	    	body=world.createBody(bdef);
	    	
	    	shape.setAsBox(rect.getWidth()/2/snowmanGame.PPM, rect.getHeight()/2/snowmanGame.PPM);
	    	fdef.shape=shape;
	    	body.createFixture(fdef);
	    }
	    
	    //����
	    for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class))
	    {
	    	Rectangle rect=((RectangleMapObject) object).getRectangle();
	    	
	    	new Brick(world, map,rect);
	    }
	    
	    //�������� ��ҵ�
	    for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class))
	    {
	    	Rectangle rect=((RectangleMapObject) object).getRectangle();
	    	
	    	new dead(world, map,rect);
	    }
	    
	    //��
	    for(MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class))
	    {
	    	Rectangle rect=((RectangleMapObject) object).getRectangle();
	    	
	    	new door(world, map, rect);
	    }	
	    
	    //������ �κ��ε� ���� �� ��
//	    for(MapObject object : map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class))
//	    {
//	    	Rectangle rect=((RectangleMapObject) object).getRectangle();
//	    	
//	    	new spring(screen, rect);
//	    }
	    
	   
	}
}