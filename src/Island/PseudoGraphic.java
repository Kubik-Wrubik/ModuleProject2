package Island;

import Island.Entities.Animal;
import Island.Entities.Herbivorous.*;
import Island.Entities.Predators.*;

public class PseudoGraphic {
	public static String iconTransform(Animal animal){
		if(animal instanceof Boar) return "\uD83D\uDC17";
		else if(animal instanceof Buffalo) return "\uD83D\uDC03";
		else if(animal instanceof Deer) return "\uD83E\uDD8C";
		else if(animal instanceof Duck) return "\uD83E\uDD86";
		else if(animal instanceof Goat) return "\uD83D\uDC10";
		else if(animal instanceof Horse) return "\uD83D\uDC0E";
		else if(animal instanceof Mouse) return "\uD83D\uDC01";
		else if(animal instanceof Rabbit) return "\uD83D\uDC07";
		else if(animal instanceof Sheep) return "\uD83D\uDC11";
		else if(animal instanceof Worm) return "\uD83D\uDC1B";
		else if(animal instanceof Bear) return "\uD83D\uDC3B";
		else if(animal instanceof Eagle) return "\uD83E\uDD85";
		else if(animal instanceof Fox) return "\uD83E\uDD8A";
		else if(animal instanceof Python) return "\uD83D\uDC0D";
		else if(animal instanceof Wolf) return "\uD83D\uDC3A";
		return "null";
	}
}
