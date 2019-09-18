/***************************************************************
* ���α׷��� : ������ ����
* ���α׷� ���� : ĳ���Ͱ� ������ �������� �ٲ�鼭 ĳ���͸� ���� ������.
* ��(���̸�) : ����¯¯��(7��)
***************************************************************/

package com.jjangx2.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

public class spring extends InteractiveTiledObject{
	private static TiledMapTileSet tileset;
	private final int BLACK_SPRING=2;
	public spring (World world, TiledMap map, Rectangle bounds) {
		super(world, map, bounds);
		tileset=map.getTileSets().getTileSet("spring");
		fixture.setUserData(this);
		setCategoryFilter(snowmanGame.SPRING_BIT);
	}

	@Override
	public void onHeadHit() {	
		Gdx.app.log("spring", "HAHAH");
		getCell().setTile(tileset.getTile(BLACK_SPRING));
//		Hud.addScore(100);
	}
}