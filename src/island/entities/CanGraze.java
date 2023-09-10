package island.entities;

public interface CanGraze {
	default boolean isPlantsLeft(Vegetation vegetation){
		return vegetation.currentPlants-- > 0;
	}

	/**
	 * method add satiety to herbivores and reduces vegetation by one plant or less
	 * @param animal is the animal who implements this interface
	 * @param vegetation is the vegetation of current animal cell
	 */
	default void graze(Animal animal, Vegetation vegetation){
		double gotSatiety = 0;
		double epsilon = Math.pow(10, 3);

		while(isPlantsLeft(vegetation)){
			double wantToEat = Math.round((animal.getMaxSatiety() - animal.getCurrentSatiety()) * epsilon) / epsilon;
			if(wantToEat >= 1){
				eating(1, animal, vegetation);
				gotSatiety++;
			} else{
				eating(wantToEat, animal, vegetation);
				gotSatiety += wantToEat;
				break;
			}
		}
		vegetation.currentPlants = Math.round(vegetation.currentPlants * epsilon) / epsilon;
		System.out.println(animal.getName() + " eaten " + gotSatiety + " plants!");
	}

	/**
	 * method add satiety and reduce vegetation on <code>wantToEat</code>
	 * @param wantToEat show how much can eat animal
	 * @param animal is a herbivorous
	 * @param vegetation is a vegetation
	 */
	default void eating(double wantToEat, Animal animal, Vegetation vegetation){
		if(wantToEat > vegetation.currentPlants){
			animal.setCurrentSatiety(animal.getCurrentSatiety() + vegetation.currentPlants);
			vegetation.currentPlants = 0;
		} else{
			animal.setCurrentSatiety(animal.getCurrentSatiety() + wantToEat);
			vegetation.currentPlants -= wantToEat;
		}
	}
}
