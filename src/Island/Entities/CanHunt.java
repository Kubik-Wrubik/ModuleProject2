package Island.Entities;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public interface CanHunt {
	int getPrey(Animal animal);

	default boolean catchPrey(Animal hunter, int catchChance, Animal prey){
		ThreadLocalRandom random = ThreadLocalRandom.current();
		if(random.nextInt(100) < catchChance){
			Meat meat = prey.die("caught up!");
			eating(hunter, meat);
			return true;
		}
		System.out.println(prey.name + " escape from " + hunter.name + "!");
		return false;
	}

	default boolean eatRandomMeat(Animal animal){ // meat has not type so can be cannibalism
		int size;
		if((size = animal.currentLocation.getMeat().size()) > 0){
			Meat meat = animal.currentLocation.getMeat().get(ThreadLocalRandom.current().nextInt(size));
			eating(animal,meat);
			return true;
		}
		return false;
	}

	default void eating(Animal animal, Meat meat){
		double wantToEat = animal.maxSatiety - animal.currentSatiety;
		if(wantToEat > meat.weight){
			animal.currentSatiety += meat.weight;
			animal.currentLocation.removeMeat(meat);
		} else{
			animal.currentSatiety += wantToEat;
			meat.weight -= wantToEat;
		}
	}
}
