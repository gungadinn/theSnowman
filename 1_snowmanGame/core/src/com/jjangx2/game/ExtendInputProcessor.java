/***************************************************************
* ���α׷��� : Ű���� ����
* ���α׷� ���� : Ű����κ��� �Է¹޾� ���� ������ �ѱ�� 
* ��(���̸�) : ����¯¯��(7��)
***************************************************************/
package com.jjangx2.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

//InputProcessor �������̽��� Ű���峪 ��ġ��ũ�����κ��� �Է��� ���� �� ���ȴ�.
//�� ������Ʈ������ ���� ������ �Ѿ �� ���ȴ�.
public class ExtendInputProcessor implements InputProcessor 
{
    private boolean keyPressed = false;
    
    public boolean isKeyPressed() {
    	return keyPressed;
    }
    
    public void setKeyPressed(boolean keyPressed) {
    	this.keyPressed = keyPressed;
    }

    //Ű�� �������� ��
    public boolean keyDown(int keycode) 
    {
        if (keycode == Input.Keys.SPACE) {
            keyPressed = true;
        }

        return false;
    }

    //Ű�� released ���� ��
    public boolean keyUp(int keycode) 
    {
        if (keycode == Input.Keys.SPACE) {
            keyPressed = false;
        }

        return false;
    }
    
    
    
    
    /* �Ⱦ� */

	//Ű�� �Է� �޾����� ��
	public boolean keyTyped(char character) {	return false; 	}

	//���콺 ���� ��ũ�� �� �� ���ȴ�
	public boolean scrolled(int amount) {	return false;	}
	
	//��ư�� ������ ���� ���콺�� ������ �� �ҷ���
	public boolean mouseMoved(int screenX, int screenY) {	return false;	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {	return false;	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {	return false;	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {	return false;	}
}
