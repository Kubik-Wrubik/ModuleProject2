package island.entities.predators;

import island.Cell;
import island.entities.Animal;
import island.entities.herbivorous.*;

import java.util.HashMap;
import java.util.Map;

public class Python extends Predator {
	private final int chanceToGetFox = 15;
	private final int chanceToGetRabbit = 20;
	private final int chanceToGetMouse = 40;
	private final int chanceToGetDuck = 10;
	public Python(Cell cell, Object lock){
		super(15, 1, 3, 10, 2, cell, lock);
		preys.put(Fox.class, chanceToGetFox);
		preys.put(Rabbit.class, chanceToGetRabbit);
		preys.put(Mouse.class, chanceToGetMouse);
		preys.put(Duck.class, chanceToGetDuck);
	}
}
