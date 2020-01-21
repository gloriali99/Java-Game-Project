package unsw.dungeon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PlayerLocation implements Subject {
	   // Subject classs implementation
		private int x ; 
		private int y ;
		private List<Observer> observerList = new ArrayList<Observer>();
		
		// add an observer to the list 
		public void addObserver(Observer o) {
			observerList.add(o); 	
		}
		// remove an observer from the list 
		public void removeObserver(Observer o) {
			observerList.remove(o); 
		}
		
		// notify all the observers in the list 
		public void notifyObserver(Boolean attack) {
			Iterator<Observer> i = observerList.iterator(); 
			while(i.hasNext()){
				Observer o = i.next(); 
				o.update(x,y, attack);
				o.getAction().action();
			}
		}
		
//		// notify all the observers in the list 
//		public void notifyObserversRun() {
//			Iterator<Observer> i = observerList.iterator(); 
//			while(i.hasNext()){
//				Observer o = i.next(); 
//				o.update(x,y, false);
//				o.getAction().action();
//			}
//		}
//	
		public void locationChanged(boolean attack, int xCo, int yCo) {
			this.x = xCo;
			this.y = yCo;
			
			notifyObserver(attack);
			
		}
		
}
