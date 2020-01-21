package unsw.dungeon;

public class Switch extends Entity {
	
	private boolean on_off;

    public Switch(int x, int y) {
        super(x, y);
    }
    
    public void switch_on() {
    	this.on_off = true;
    }
    
    public void switch_off() {
    	this.on_off = false;
    }

	public boolean onSwitch() {
		return on_off;
	}

}
