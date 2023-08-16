package Island.Entities.Herbivorous;

import Island.Cell;
import Island.Entities.Animal;
import Island.Entities.CanHunt;
import Island.Entities.Vegetation;

import java.util.List;

public class Mouse extends Herbivorous implements CanHunt {


	public Mouse(String name, Cell cell, Object lock){
		super(name, 0.05, 1, 0.01,2, 0, cell, lock);
	}

//	@Override
//	public boolean searchFood(){
//		int successPercent = 0;
//		List<Animal> animals = currentLocation.getAnimals();
//		for(Animal animal: animals){
//			if(animal instanceof Worm) successPercent = 90;
//			if(successPercent != 0){
//				if (catchPrey(this, successPercent,animal)) return true;
//				break;
//			}
//		}
//
//		List<Vegetation> plants = currentLocation.getPlants();
//		return eatPlant(plants);
//	}

	@Override
	public int getPrey(Animal animal){
		return 0;
	}
}
