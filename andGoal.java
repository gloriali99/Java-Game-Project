package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

public class andGoal implements Goal {
	
	private List<Goal> goals = new ArrayList<Goal>();
	
	public andGoal() {
		this.goals = new ArrayList<Goal>();
	}

	
	//returns true if all subgoals are complete
	@Override
	public boolean goalComplete(boolean nextToExit){
		int count = 0;
		for (Goal g: goals) {
			if (!g.goalComplete(nextToExit)) {
				return false;
			} else {
				count++;
			}	
		}
		if (count == goals.size()) {
			return true;
		} else {
			return false;
		}
	}

	public void addGoal(Goal goal) {
		this.goals.add(goal);
		
	}
	
	public String goalText() {
		String text = "( AND: ";
		for (Goal g: goals) {
			text = text + " " + g.goalText();
		}
		text = text + ")";
		return text;
	}

	
	
}