package Island.Entities;

import java.util.concurrent.ThreadLocalRandom;

/**
 * interface that allow animal to hunt. It is implemented by all Predators and some Herbivorous.
 */
public interface CanHunt {
	/**
	 * @param hunter is the animal who implements this interface
	 * @param catchChance is a hunter value that shows the percent of success of hunting a specific animal
	 * @param prey is an animal who can be caught up
	 * @return <code>true</code> if hunter caught the prey
	 */
	default boolean catchPrey(Animal hunter, int catchChance, Animal prey){
		ThreadLocalRandom random = ThreadLocalRandom.current();
		if(random.nextInt(100) < catchChance){
			Meat meat = prey.die("caught up by " + hunter.name + "!");
			eating(hunter, meat);
			return true;
		}
		System.out.println(prey.name + " escape from " + hunter.name + "!");
		return false;
	}

	/**
	 * eat random meat on the cell. Meat has not a type, so cannibalism is allowed here.
	 * @param animal is hunter looking for easy meat
	 * @return <code>true</code> if hunter found the meat
	 */
	default boolean eatRandomMeat(Animal animal){
		double epsilon = Math.pow(10, 3);
		double satietyBeforeEating = animal.currentSatiety;
		int size;
		if((size = animal.currentLocation.getMeat().size()) > 0){
			Meat meat = animal.currentLocation.getMeat().get(ThreadLocalRandom.current().nextInt(size));
			eating(animal, meat);
			System.out.println(animal.name + " eaten " + Math.round((animal.currentSatiety - satietyBeforeEating) * epsilon) / epsilon + " meat!");
			return true;
		}
		return false;
	}

	/**
	 * method add correct satiety value to animal and reduces meat weight
	 * @param animal is a hunter
	 * @param meat is a meat
	 */
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

	int getPrey(Animal animal);
}
