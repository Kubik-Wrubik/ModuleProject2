package Island.Entities;

/**
 * <code>MAX_PLANTS</code> limits maximum plants amount
 * <p><code>growValue</code> is final and increase plants amount every cycle
 * <p><code>currentPlants</code> contains double plants amount
 */
public class Vegetation implements Entity {
	private final static double MAX_PLANTS = 200;
	private final int growValue;
	public double currentPlants;

	/**
	 *
	 * @param initialValue is an initial value
	 * @param growValue is a growing value
	 */
	public Vegetation(double initialValue, int growValue){
		this.currentPlants = initialValue;
		this.growValue = growValue;
	}

	public void grow(){
		if((currentPlants += growValue) > MAX_PLANTS) currentPlants = MAX_PLANTS;
	}
}
