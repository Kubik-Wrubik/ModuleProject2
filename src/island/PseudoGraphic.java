package island;

import island.entities.Animal;
import island.entities.herbivorous.*;
import island.entities.Meat;
import island.entities.predators.*;
import island.entities.Vegetation;

/**
 * Class for representation entities as a one character
 */
public class PseudoGraphic {
	/**
	 * Transform the {@link Animal} into a unicode character
	 */
	public static String iconTransform(Animal animal){
		if(animal instanceof Boar) return "\uD83D\uDC17";
		if(animal instanceof Buffalo) return "\uD83D\uDC03";
		if(animal instanceof Deer) return "\uD83E\uDD8C";
		if(animal instanceof Duck) return "\uD83E\uDD86";
		if(animal instanceof Goat) return "\uD83D\uDC10";
		if(animal instanceof Horse) return "\uD83D\uDC0E";
		if(animal instanceof Mouse) return "\uD83D\uDC01";
		if(animal instanceof Rabbit) return "\uD83D\uDC07";
		if(animal instanceof Sheep) return "\uD83D\uDC11";
		if(animal instanceof Worm) return "\uD83D\uDC1B";
		if(animal instanceof Bear) return "\uD83D\uDC3B";
		if(animal instanceof Eagle) return "\uD83E\uDD85";
		if(animal instanceof Fox) return "\uD83E\uDD8A";
		if(animal instanceof Python) return "\uD83D\uDC0D";
		if(animal instanceof Wolf) return "\uD83D\uDC3A";

		return "null";
	}
}
