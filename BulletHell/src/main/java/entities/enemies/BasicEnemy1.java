package entities.enemies;

import main.GameManager;
import main.Main;
import reference.Config;
import util.Log;
import entities.EntityBase;
import entities.Player;
import entities.projectiles.player.PlayerShot;

public class BasicEnemy1 extends EntityBase {
	boolean left;
	int RoF;
	int bulletSpeed;
	int Spread;
	int bps;
	int ct = 0;
	public BasicEnemy1(String img, int RoF, int Spread, int bulletsPerShot, int bulletSpeed){
		super(img);
		x = -100;
		y = 300;
		this.bulletSpeed = bulletSpeed;
		this.bps = bulletsPerShot;
		this.RoF = RoF;
		this.Spread = Spread;
		if(x<Config.width/2){
			left = true;
			vx = 2;vy=2;ay=-.02;ax=0;
		}else{
			left = false;
			vx = -2;vy = 2;ay=-.02;ax = 0;
		}
	}
	public boolean update(){
		if (GameManager.count % (int)((Config.UPS * Config.GAME_SPEED) / 100) == 0) {
			physUpdate();
		}
		if(y<-1000&&left == true){
			left = false;
			vx = -2;vy = 2;ay=-.02;ax = 0;	
			x = Config.width+100;
			y = 300;
		}
		if(y<-1000&&left == false){
			left = true;
			vx = 2;vy=2;ay=-.02;ax=0;
			x = -100;
			y = 300;
		}
		if (GameManager.count % (int)((Config.UPS * Config.GAME_SPEED) / RoF) == 0) {
			double px = Main.f.getPanel().getGM().getPlayer().getX();
			double py = Main.f.getPanel().getGM().getPlayer().getY();
			double dx = px-x;
			double dy = py-y;
			Log.info(Math.atan2(dy,dx)+"");
	//		Log.info(dx + "  " + dy);
	//		GameManager.projectiles.add(new PlayerShot("uglyPlaceholderProjectile.jpg", x, y, dx, dy, 0 , 0));
			while(bps>ct){
				GameManager.projectiles.add(new PlayerShot("uglyPlaceholderProjectile.jpg", Math.toDegrees(Math.atan2(dy,dx))+(Math.random()*Spread)-(Spread/2), bulletSpeed, x, y, 0, 0, true));
				ct++;
			}
			ct = 0;
		}
		return false;
	}
}
