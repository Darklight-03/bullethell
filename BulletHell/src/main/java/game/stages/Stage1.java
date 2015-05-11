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
			try {
				wait();
			}
			catch (InterruptedException e) {
				GameManager.getGame().enemies.add(new BasicEnemy1(2, 50, 10, 3));
			}
		}
		GameManager.getGame().enemies.add(new BasicEnemy1(2, 50, 10, 3));
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try{
				Thread.sleep((int)(1000/(1*Config.GAME_SPEED)));
			}catch(Exception e){
				Log.error("error in Thread: game.stages.Stage1.java:run()");
			}
			if(!stop) count++;
			notifyAll();
		}
	}
	
}
