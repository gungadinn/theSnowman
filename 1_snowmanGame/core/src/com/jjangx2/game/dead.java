/***************************************************************
* ���α׷��� : �������� ��ġ�� ����
* ���α׷� ���� : ���� ������Ʈ �� ĳ���Ϳ� ������ �״� �͵��� �����Ѵ�.
* ��(���̸�) : ����¯¯��(7��)
***************************************************************/


package com.jjangx2.game;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

public class dead extends InteractiveTiledObject{

      public dead(World world, TiledMap map, Rectangle bounds) {
		super(world, map, bounds);
		fixture.setUserData(this);
	}

	public void onHeadHit() {    	
     	snowmanGame.I.setScreen(new GameOverScreen(snowmanGame.I));
    }
}