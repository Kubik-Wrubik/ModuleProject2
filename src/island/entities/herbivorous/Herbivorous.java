package island.entities.herbivorous;

import island.Cell;
import island.entities.Animal;
import island.entities.CanGraze;
import island.entities.Vegetation;

public abstract class Herbivorous extends Animal implements CanGraze {
	public Herbivorous(double weight, int speed, double maxSatiety, int maxAge, int breedAbleAge, Cell cell, Object lock){
		super(weight, speed, maxSatiety, maxAge, breedAbleAge, cell, lock);
	}

	public synchronized boolean searchFood(){
		Vegetation vegetation = getCurrentLocation().getVegetation();
		if(vegetation.currentPlants > 0){
			graze(this, vegetation);
			return true;
		}
		return false;
	}
}
