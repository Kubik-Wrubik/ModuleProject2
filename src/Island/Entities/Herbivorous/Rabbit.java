package Island.Entities.Herbivorous;

import Island.Cell;
import Island.Entities.Animal;
import Island.Entities.Plant;
import Island.Island;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Rabbit extends Herbivorous{


	public Rabbit(String name, Cell cell, Object lock){
		super(name, cell,lock);
		weight = 2;
		speed = 2;
		maxSatiety = 0.45;
	}

	@Override
	public boolean eat(){
		List<Plant> plants = currentLocation.getPlants();
		return eatPlant(plants);
	}
}
