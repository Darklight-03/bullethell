package entities.enemies;

import entities.projectiles.BasicShot;
import game.GameManager;
import graphics.ImageLoader;

import java.awt.Graphics;
import java.awt.Rectangle;

import reference.Config;
import util.Log;

public class BigPlane extends EnemyBase{
	
	public static final int RoF=1, numBullets = 16;
	int i = 0;
	
	public BigPlane(int x){
		super(400);
		setImage(Config.scale(ImageLoader.bigPlane0,2,2));
		this.x = x;
		y = 50;
		vy = .5;
	}
	
	public boolean update(){
		updateHitBox();
		if (GameManager.getGame().getCount() % (int) ((Config.UPS * Config.GAME_SPEED) / 100) == 0) {
			physUpdate();
		}
		if(GameManager.getGame().getCount() % (int) ((Config.UPS * Config.GAME_SPEED) / 100) == 0) {
			setImage(Config.scale(ImageLoader.bigPlane0,2,2));
		}
		if(y>600){
			vy = -.1;
		}
		if(y>100){
			if(GameManager.getGame().getCount()%(int) (Config.UPS*Config.GAME_SPEED)/0.1 == 0){
				int angleN = 360/numBullets;
				for (int n = 0;n<numBullets;n++)
				GameManager.getGame().enemyProjectiles.add(new BasicShot(Config.scale(ImageLoader.roundLargeBullet,3,3),(angleN*n),0.5,x,y,0,0,true));
			}
		}
		
			return true;
		
	}
	
	
	public void drawThis(Graphics bg) {
		i++; if(i>3){
			i = 0%1;
		}
		bg.drawImage(Config.scale(ImageLoader.bigPlane.get(i),2,2), drawX(), drawY(), null);
		
	}
	
	
	@Override
	public void updateHitBox() {
		hitBox = new Rectangle((int) x - 30, (int) y - 22, 60, 22);
	}
}
