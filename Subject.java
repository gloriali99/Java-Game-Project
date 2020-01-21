package unsw.dungeon;

public interface Subject {
	public void addObserver(Observer o); 
	public void removeObserver(Observer o); 
	//public void locationChanged(boolean run, boolean move, int x , int y); 
}
