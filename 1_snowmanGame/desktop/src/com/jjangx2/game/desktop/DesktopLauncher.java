/***************************************************************
* 프로그램명 : 눈사람 게임
* 프로그램 설명 : 메인 함수. 
* 조(조이름) : 혀니짱짱맨(7조)
* 조원 : 김은경, 박수현, 이현경, 이상록

* 작성자 : 2016039060 김은경
* 작성일 : 2017.11.9
***************************************************************/

package com.jjangx2.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.jjangx2.game.snowmanGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width=1280;
		config.height=720;
		new LwjglApplication(new snowmanGame(), config);  //snowmanGame 실행
	}
}
