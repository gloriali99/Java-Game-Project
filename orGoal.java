package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

public class orGoal implements Goal {
	
	private List<Goal> goals = new ArrayList<Goal>();
	
	public orGoal() {
		this.goals = new ArrayList<Goal>();
	}

	//return true if at least one subgoal is complete
	@Override
	public boolean goalComplete(boolean nextToExit) {
		for (Goal g: goals) {
			if (g.goalComplete(nextToExit)) {
				return true;
			} 	
		}
		return false;
	}

	public void addGoal(Goal goal) {
		this.goals.add(goal);
		
	}
	
	public String goalText() {
		String text = "( OR: ";
		for (Goal g: goals) {
			text = text + " " + g.goalText();
		}
		text = text + ")";
		return text;
	}
	
	
	
}