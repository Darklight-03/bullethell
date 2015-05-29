package game.stages;

import entities.enemies.BigPlane;
import entities.enemies.SmallTurretEnemy;
import game.GameManager;
import reference.Config;
import util.Log;

public class Stage1 extends Stage implements Runnable {
	boolean stop;

	public Stage1() {
		count = 0;
		stop = false;
		Thread t = new Thread(this);
		t.start();
	}

	public int getStage() {
		return 1;
	}

	
	public void spawning(){
		
		if(count==1){
				GameManager.getGame().enemies.add(new SmallTurretEnemy(2, 50, 10, 3));
		}
		if(count==10){
		GameManager.getGame().enemies.add(new SmallTurretEnemy(10, 50, 1, 4));
		stop = true;
		}
		if(count == 11){
			GameManager.getGame().enemies.add(new BigPlane(500));
			GameManager.getGame().enemies.add(new BigPlane(100));
		}
		

	}

	public void stopCheck() {
		if (GameManager.getGame().getEnemies().size() == 0) {
			stop = false;
		}
	}

	@Override
	public void run() {
		Thread.currentThread().setPriority((int) (Thread.MAX_PRIORITY * 0.8));

		while (true) {
			try {
				Thread.sleep((int) (1000 / (1 * Config.GAME_SPEED)));
			}
			catch (Exception e) {
				Log.error("error in Thread: game.stages.Stage1:run()");
			}
			if (!stop) {
				count++;
				spawning();
			}
			else {
				stopCheck();
			}
		}
	}

}
