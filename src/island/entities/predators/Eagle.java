package island.entities.predators;

import island.Cell;
import island.entities.Animal;
import island.entities.herbivorous.*;

import java.util.HashMap;
import java.util.Map;

public class Eagle extends Predator {
	private final int chanceToGetFox = 10;
	private final int chanceToGetRabbit = 90;
	private final int chanceToGetMouse = 90;
	private final int chanceToGetDuck = 80;

	public Eagle(Cell cell, Object lock){
		super( 6, 3, 1, 20, 4, cell, lock);
		preys.put(Python.class, chanceToGetFox);
		preys.put(Rabbit.class, chanceToGetRabbit);
		preys.put(Mouse.class, chanceToGetMouse);
		preys.put(Duck.class, chanceToGetDuck);
	}
}
