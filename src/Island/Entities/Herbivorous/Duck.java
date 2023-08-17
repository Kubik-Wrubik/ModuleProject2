package Island.Entities.Herbivorous;

import Island.Cell;
import Island.Entities.Animal;
import Island.Entities.CanHunt;
import Island.Entities.Vegetation;

import java.util.List;

public class Duck extends Herbivorous implements CanHunt {
	public Duck(String name, Cell cell, Object lock){
		super(name, 1, 4, 0.15, 5, 1, cell, lock);
	}

	@Override
	public boolean searchFood(){
		if(eatRandomMeat(this)) return true;

		List<Animal> animals = currentLocation.getAnimals();
		for(Animal animal : animals){
			int successPercent = getPrey(animal);
			if(successPercent != 0){
				return catchPrey(this, successPercent, animal);
			}
		}

		Vegetation vegetation = currentLocation.getVegetation();
		if(vegetation.currentPlants > 0){
			graze(this, vegetation);
			return true;
		}

		return false;
	}

	@Override
	public int getPrey(Animal animal){
		if(animal instanceof Worm) return 90;
		return 0;
	}
}
