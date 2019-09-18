/***************************************************************
* ���α׷��� : ��� ������Ʈ ����
* ���α׷� ���� : ������Ʈ (����, ���� ���)�� ��ġ�� �ڵ���� �����Ҵ�.
* ��(���̸�) : ����¯¯��(7��)
***************************************************************/

package com.jjangx2.game;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public abstract class InteractiveTiledObject {
	protected World world;
	protected TiledMap map;
	protected TiledMapTile tile;
	protected Rectangle bounds;
	protected Body body;
	
	protected Fixture fixture;
	
	public InteractiveTiledObject(World world, TiledMap map, Rectangle bounds) {
		this.world=world;
		this.map=map;
		this.bounds=bounds;
		
		BodyDef bdef= new BodyDef();
		FixtureDef fdef=new FixtureDef();
		PolygonShape shape=new PolygonShape();

		bdef.type=BodyDef.BodyType.StaticBody;
		bdef.position.set((bounds.getX()+bounds.getWidth()/2)/snowmanGame.PPM, (bounds.getY()+bounds.getHeight()/2)/snowmanGame.PPM);
		
		body=world.createBody(bdef);
		
		shape.setAsBox(bounds.getWidth()/2/snowmanGame.PPM, bounds.getHeight()/2/snowmanGame.PPM);
		fdef.shape=shape;
		fixture=body.createFixture(fdef);
		body.createFixture(fdef);	
	}

	public abstract void onHeadHit(); //�����̶� �ε����� ��Եǳ�. �̷� ��� ���ٱ�
	
	public void setCategoryFilter(short filterBit) {
		Filter filter=new Filter();
		filter.categoryBits=filterBit;
		fixture.setFilterData(filter);
	}
	
	public TiledMapTileLayer.Cell getCell() {
		TiledMapTileLayer layer=(TiledMapTileLayer)map.getLayers().get(1);
		return layer.getCell((int)(body.getPosition().x*snowmanGame.PPM/16), (int)(body.getPosition().y*snowmanGame.PPM/16)); //16�� ���� Ÿ�� ����� �Ǿ����
	}
	
}
