package Island.Entities.Herbivorous;

import Island.Cell;
import Island.Entities.Animal;
import Island.Entities.CanGraze;
import Island.Entities.Vegetation;

public abstract class Herbivorous extends Animal implements CanGraze {
	public Herbivorous(String name, double weight, int speed, double maxSatiety, int maxAge, int breedAbleAge, Cell cell, Object lock){
		super(name, weight, speed, maxSatiety, maxAge, breedAbleAge, cell, lock);
	}

	public synchronized boolean searchFood(){
		Vegetation vegetation = currentLocation.getVegetation();
		if(vegetation.currentPlants > 0){
			graze(this, vegetation);
			return true;
		}
		return false;
	}
}
