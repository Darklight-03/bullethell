package game.stages;

import entities.enemies.BasicEnemy1;
import game.GameManager;
import reference.Config;
import util.Log;


public class Stage1 implements Runnable{
	int count;
	boolean stop;
	public Stage1(){
		count = 0;
		stop = false;
		Thread t = new Thread(this);
		t.start();	
	}
	
	public void spawning(){
		if(count==1){
				GameManager.getGame().enemies.add(new BasicEnemy1(2, 50, 10, 3));
		}
		if(count==10){
		GameManager.getGame().enemies.add(new BasicEnemy1(10, 50, 1, 5));
		stop = true;
		}
		
	}
	
	public void stopCheck(){
		if(GameManager.getGame().getEnemies().size()==0){
			stop = false;
		}
	}

	@Override
	public void run() {
		Thread.currentThread().setPriority((int)(Thread.MAX_PRIORITY*0.8));
		// TODO Auto-generated method stub
		while(true){
			try{
				Thread.sleep((int)(1000/(1*Config.GAME_SPEED)));
			}catch(Exception e){
				Log.error("error in Thread: game.stages.Stage1.java:run()");
			}
			if(!stop){ count++;
			spawning();
			}
			else{
				stopCheck();
			}
		}
	}
	
}
