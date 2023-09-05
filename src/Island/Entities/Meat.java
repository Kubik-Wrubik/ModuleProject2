package Island.Entities;

import Island.Cell;

/**
 * <code>deadAnimal</code> is the name of dead animal
 * <p><code>weight</code> is the meat amount and equals animal weight
 * <p><code>years</code> is animal years
 * <p><code>currentLocation</code> is cell where animal has died
 */
public class Meat implements Entity{
	public String deadAnimal;
	public double weight;
	private int years = 0;
	private final Cell currentLocation;

	/**
	 * @param animal is dead or killed animal
	 */
	public Meat(Animal animal){
		deadAnimal = animal.name;
		weight = animal.weight;
		currentLocation = animal.currentLocation;
		currentLocation.addMeat(this);
	}

	/**
	 * after 3 cycles(years) meat disappear from cell
	 */
	public void gettingRot(){
		int rotingYear = 3;
		if(rotingYear <= years){
			currentLocation.removeMeat(this);
			System.out.println(deadAnimal + "'s meat has rotten!");
		}else years++;
	}
}
