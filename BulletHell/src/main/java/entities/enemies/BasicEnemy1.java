package entities.enemies;

import main.GameManager;
import reference.Config;
import entities.EntityBase;

public class BasicEnemy1 extends EntityBase {
	boolean left;
	public BasicEnemy1(String img, int x1, int y1){
		super(img);
		x = x1;
		y = y1;
		if(x<Config.width/2){
			left = true;
			vx = 2;vy=1;ay=-.05;ax=-.01;
		}else{
			left = false;
		}
	}
	public boolean update(){
		if (GameManager.count % (int)((Config.UPS * Config.GAME_SPEED) / 100) == 0) {
			physUpdate();
			
			
			
		}
		return false;
	}
}
