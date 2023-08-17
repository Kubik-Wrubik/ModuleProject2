package Island;

import Island.Entities.Entity;
import Island.Entities.Herbivorous.*;
import Island.Entities.Meat;
import Island.Entities.Predators.*;
import Island.Entities.Vegetation;

/**
 * Class for representation entities as a one character
 */
public class PseudoGraphic {
	/**
	 * Transform the {@link Entity} into a unicode character
	 */
	public static String iconTransform(Entity entity){
		if(entity instanceof Meat) return "\uD83E\uDD69";
		if(entity instanceof Vegetation) return "\uD83C\uDF31";
		if(entity instanceof Boar) return "\uD83D\uDC17";
		if(entity instanceof Buffalo) return "\uD83D\uDC03";
		if(entity instanceof Deer) return "\uD83E\uDD8C";
		if(entity instanceof Duck) return "\uD83E\uDD86";
		if(entity instanceof Goat) return "\uD83D\uDC10";
		if(entity instanceof Horse) return "\uD83D\uDC0E";
		if(entity instanceof Mouse) return "\uD83D\uDC01";
		if(entity instanceof Rabbit) return "\uD83D\uDC07";
		if(entity instanceof Sheep) return "\uD83D\uDC11";
		if(entity instanceof Worm) return "\uD83D\uDC1B";
		if(entity instanceof Bear) return "\uD83D\uDC3B";
		if(entity instanceof Eagle) return "\uD83E\uDD85";
		if(entity instanceof Fox) return "\uD83E\uDD8A";
		if(entity instanceof Python) return "\uD83D\uDC0D";
		if(entity instanceof Wolf) return "\uD83D\uDC3A";

		return "null";
	}
}
