package Island.Entities.Herbivorous;

import Island.Entities.Animal;
import Island.Entities.CanGraze;
import Island.Cell;
import Island.Entities.Vegetation;

import java.util.List;

public abstract class Herbivorous extends Animal implements CanGraze {
	public Herbivorous(String name, double weight, int speed, double maxSatiety,int maxAge, int breedAbleAge, Cell cell, Object lock){
		super(name, weight, speed, maxSatiety,maxAge, breedAbleAge, cell, lock);
	}

	public synchronized boolean searchFood(){
		Vegetation vegetation = currentLocation.getVegetation();
		if(vegetation.currentPlants.get() > 0){
			eating(this, vegetation);
			return true;
//			try{
//				this.notifyAll();
//				this.wait();
//			} catch(InterruptedException e){
//				e.printStackTrace();
//			}
		}
		return false;
	}
}
