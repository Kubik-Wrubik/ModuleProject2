package Island.Entities;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public interface CanHunt {
	default boolean catchPrey(int catchChance, List<Animal> animals, Animal prey){
		ThreadLocalRandom random = ThreadLocalRandom.current();
		if(random.nextLong(100) < catchChance){
			prey.hasEaten = true;
			animals.remove(prey);
//			new Meat(prey);
			System.out.println(prey.name + " caught up!");
			return true;
		}
		System.out.println(prey.name + " is alive!");
		return false;
	}

	default boolean eatMeat(Animal animal){
		if(animal.currentLocation.getMeat().size() > 0){
			Meat meat = animal.currentLocation.getMeat().get(0);
			double wantToEat = animal.maxSatiety - animal.currentSatiety;
			if(wantToEat > meat.weight){
				animal.currentSatiety += meat.weight;
				animal.currentLocation.removeMeat(meat);
			} else{
				animal.currentSatiety += wantToEat;
				meat.weight -= wantToEat;
			}
			return true;
		}
		return false;
	}
}
