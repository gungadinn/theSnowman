/***************************************************************
* ���α׷��� : ��ġ�� �κ� ����
* ���α׷� ���� : ���� ������Ʈ���� ��ġ�� �κе��� �����Ѵ�.
* ��(���̸�) : ����¯¯��(7��)
***************************************************************/


package com.jjangx2.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;


public class WorldContactListener implements ContactListener{

	//��������
		public void beginContact(Contact contact) 
		{
			Fixture fixA = contact.getFixtureA();
			Fixture fixB = contact.getFixtureB();
			
			
			if (fixA.getUserData()=="bottom" || fixB.getUserData()=="bottom") 
			{
				Fixture bottom = fixA.getUserData()=="bottom" ? fixA : fixB;
				Fixture object = bottom == fixA ? fixB : fixA;
				
				if (object.getUserData() != null && InteractiveTiledObject.class.isAssignableFrom(object.getUserData().getClass())) {
					((InteractiveTiledObject) object.getUserData()).onHeadHit();
				}
			}
			
			contactDoor(fixA,fixB);
			contactDead(fixA,fixB);
		}

		//����Ʈ������
		public void endContact(Contact contact) 
		{	
			Fixture fixA = contact.getFixtureA();
			Fixture fixB = contact.getFixtureB();
			
			if (fixA.getUserData()=="bottom" || fixB.getUserData()=="bottom") 
			{
				Fixture head = fixA.getUserData()=="bottom" ? fixA : fixB;
				Fixture object = head == fixA ? fixB : fixA;
				
				exitBrick(object);
			}
		}
		
		private void exitBrick(Fixture object)
		{
			if (object.getUserData() != null && Brick.class.isAssignableFrom(object.getUserData().getClass())) {
				((Brick) object.getUserData()).onExitHit();
			}
		}
		
		private void contactDoor(Fixture fixA, Fixture fixB) {
			Fixture snowman = null;
			
			if(fixA.getUserData()=="snowman") {
				snowman = fixA;
			}
			
			else if(fixB.getUserData()=="snowman") {
				snowman = fixB;
			}
			Fixture object = snowman == fixA?fixB:fixA;
			
			if (object.getUserData() != null && door.class.isAssignableFrom(object.getUserData().getClass())) {
				((door) object.getUserData()).onHeadHit();
				}
		}

		private void contactDead(Fixture fixA, Fixture fixB) {
			Fixture snowman = null;
			
			if(fixA.getUserData()=="snowman") {
				snowman = fixA;
			}
			
			else if(fixB.getUserData()=="snowman") {
				snowman = fixB;
			}
			Fixture object = snowman == fixA?fixB:fixA;
			
			if (object.getUserData() != null && dead.class.isAssignableFrom(object.getUserData().getClass())) {
				((dead) object.getUserData()).onHeadHit();
				}
		}
		
		//������ �ε����� �ٲ��ְ� ��
		public void preSolve(Contact contact, Manifold oldManifold) {	}

		//��..................
		public void postSolve(Contact contact, ContactImpulse impulse) {	}
}
 