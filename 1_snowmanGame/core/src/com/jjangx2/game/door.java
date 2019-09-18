/***************************************************************
* ���α׷��� : �� ����
* ���α׷� ���� : ���� ������Ʈ �� ���� �����Ѵ�. (���� �ܰ�� �Ѿ�� ���� ����)
* ��(���̸�) : ����¯¯��(7��)
***************************************************************/

package com.jjangx2.game;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

public class door extends InteractiveTiledObject{
	public door(World world, TiledMap map, Rectangle bounds ) {
		super(world, map, bounds);
		fixture.setUserData(this);
	}
	
	public void onHeadHit() 
	{
		
		// 8�� stage �ְ� ����, 7 �������� ���� �� �����ϸ� +1 ��������
		if(snowmanGame.I.StageLevel < 8) {
			snowmanGame.I.StageLevel += 1;
			snowmanGame.I.setScreen(new PlayScreen(snowmanGame.I, snowmanGame.I.StageLevel));
		//	Hud.addStage(1);
				
		}
		
		// 8�̸� Ŭ���� ������ ���ϴ�.
		else {
			snowmanGame.I.setScreen(new GameClearScreen());
		}
	}
}