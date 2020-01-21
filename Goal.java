package unsw.dungeon;

public interface Goal {
	public boolean goalComplete(boolean nextToExit);
	public String goalText();

}
