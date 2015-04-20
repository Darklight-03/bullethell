package entities.enemies;

import entities.EntityBase;

public class BasicEnemy1 extends EntityBase {
	public BasicEnemy1(String img, int x1, int y1){
		super(img);
		x = x1;
		y = y1;
	}
	public boolean update(){
		return false;
	}
}
