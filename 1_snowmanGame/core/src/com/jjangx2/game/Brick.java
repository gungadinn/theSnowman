/***************************************************************
* ���α׷��� : ���� ����
* ���α׷� ���� : ���� ������Ʈ �� �������� �����Ѵ�. 
* ��(���̸�) : ����¯¯��(7��)
***************************************************************/

package com.jjangx2.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

public class Brick extends InteractiveTiledObject{
	public Brick(World world, TiledMap map, Rectangle bounds) {
		super(world, map,bounds);
		fixture.setUserData(this);
	}

	public void onHeadHit() {	
		Snow.isCreating = true;
	}
	
	public void onExitHit()
	{
		Snow.isCreating = false;
	}
}