/***************************************************************
* ���α׷��� : ����� ����
* ���α׷� ���� : ���� �Լ�. 
* ��(���̸�) : ����¯¯��(7��)
* ���� : ������, �ڼ���, ������, �̻��

* �ۼ��� : 2016039060 ������
* �ۼ��� : 2017.11.9
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
		new LwjglApplication(new snowmanGame(), config);  //snowmanGame ����
	}
}
