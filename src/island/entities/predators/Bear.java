package island.entities.predators;

import island.Cell;
import island.entities.Animal;
import island.entities.herbivorous.*;

import java.util.HashMap;
import java.util.Map;

public class Bear extends Predator {
	public final String icon = "\uD83D\uDC3B";
	private final int chanceToGetPython = 80;
	private final int chanceToGetHorse = 40;
	private final int chanceToGetDeer = 80;
	private final int chanceToGetRabbit = 80;
	private final int chanceToGetMouse = 90;
	private final int chanceToGetGoat = 70;
	private final int chanceToGetSheep = 70;
	private final int chanceToGetBoar = 50;
	private final int chanceToGetBuffalo = 20;
	private final int chanceToGetDuck = 10;

	public Bear(Cell cell, Object lock){
		super(500, 2, 80, 25, 4, cell, lock);
		preys.put(Python.class, chanceToGetPython);
		preys.put(Horse.class, chanceToGetHorse);
		preys.put(Deer.class, chanceToGetDeer);
		preys.put(Rabbit.class, chanceToGetRabbit);
		preys.put(Mouse.class, chanceToGetMouse);
		preys.put(Goat.class, chanceToGetGoat);
		preys.put(Sheep.class, chanceToGetSheep);
		preys.put(Boar.class, chanceToGetBoar);
		preys.put(Buffalo.class, chanceToGetBuffalo);
		preys.put(Duck.class, chanceToGetDuck);
	}

	@Override
	public String getIcon(){
		return icon;
	}
}
