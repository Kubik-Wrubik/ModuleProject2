package island.entities.predators;

import island.Cell;
import island.entities.Animal;
import island.entities.herbivorous.*;

import java.util.HashMap;
import java.util.Map;

public class Wolf extends Predator {
	public final String icon = "\uD83D\uDC3A";
	private final int chanceToGetHorse = 10;
	private final int chanceToGetDeer = 15;
	private final int chanceToGetRabbit = 60;
	private final int chanceToGetMouse = 80;
	private final int chanceToGetGoat = 60;
	private final int chanceToGetSheep = 70;
	private final int chanceToGetBoar = 15;
	private final int chanceToGetBuffalo = 10;
	private final int chanceToGetDuck = 40;

	public Wolf(Cell cell, Object lock){
		super( 50, 3, 8, 14, 2, cell, lock);
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
