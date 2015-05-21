package game.stages;

import util.Log;

public class Stage {
	int count;
	Stage(){
		count = 0;
		Log.debug("new stage");
	}
	public int getStage(){
		return 0;
	}
	public int getCount() {
		return count;
	}
}

