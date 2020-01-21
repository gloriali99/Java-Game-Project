package unsw.dungeon;

public class Portal extends Entity{
	
	private int ID; 
	//private Portal portal;
	
	public Portal(int x , int y, int id) {
		super(x,y); 
		this.ID = id; 
		// make copy or not? 
		//this.portal = new Portal();
	}


	public int getID() {
		return ID;
	}
	
	public void setID(int iD) {
		ID = iD;
	}

	// MIGHT HAVE TO REFACTOR 

	
	//finds and returns the other portal
	public Portal findOtherPortal(Dungeon dungeon){
		for(Entity e : dungeon.getEntityList()){
			// find portal in dungeon list 
			if(e instanceof Portal){
				int id = ((Portal) e).getID();
				// check if its the same id 
				if(id == getID()){
					int xCo = e.getX();
					int yCo = e.getY();
					// check if coordinates are different 
					if(getX() != xCo && getY() != yCo){
						return (Portal)e;
						
					}
				}
			}
		}
		return null;
	}
	

}
