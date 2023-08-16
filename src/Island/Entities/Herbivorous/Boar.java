package Island.Entities.Herbivorous;

import Island.Cell;
import Island.Entities.Animal;
import Island.Entities.CanHunt;
import Island.Entities.Vegetation;

import java.util.List;

public class Boar extends Herbivorous implements CanHunt {
	public Boar(String name, Cell cell, Object lock){
		super(name, 400, 2, 50,15, 2, cell, lock);
	}

//	@Override
//	public boolean searchFood(){
//		int successPercent = 0;
//		List<Animal> animals = currentLocation.getAnimals();
//		for(Animal animal: animals){
//			if(animal instanceof Mouse) successPercent = 50;
//			if(animal instanceof Worm) successPercent = 90;
//
//			if(successPercent != 0){
//				if (catchPrey(this, successPercent,animal)) return true;
//				break;
//			}
//		}
//
//		List<Vegetation> plants = currentLocation.getVegetation();
//		return eatPlant(plants);
//	}



	@Override
	public void run(){
	}

	@Override
	public int getPrey(Animal animal){
		return 0;
	}
}
