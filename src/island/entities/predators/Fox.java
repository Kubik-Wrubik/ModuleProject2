package island.entities.predators;

import island.Cell;
import island.entities.Animal;
import island.entities.herbivorous.*;

import java.util.HashMap;
import java.util.Map;

public class Fox extends Predator {
	public final String icon = "\uD83E\uDD8A";
	private final int chanceToGetRabbit = 70;
	private final int chanceToGetMouse = 90;
	private final int chanceToGetDuck = 60;
	private final int chanceToGetWorm = 40;

	public Fox(Cell cell, Object lock){
		super(8, 2, 2, 3, 2, cell, lock);
		preys.put(Rabbit.class, chanceToGetRabbit);
		preys.put(Mouse.class, chanceToGetMouse);
		preys.put(Duck.class, chanceToGetDuck);
		preys.put(Worm.class, chanceToGetWorm);
	}

	@Override
	public String getIcon(){
		return icon;
	}
}
