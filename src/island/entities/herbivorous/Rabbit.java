package island.entities.herbivorous;

import island.Cell;

public class Rabbit extends Herbivorous {
	public final String icon = "\uD83D\uDC07";
	public Rabbit(Cell cell, Object lock){
		super( 2, 2, 0.45, 7, 2, cell, lock);
	}

	@Override
	public String getIcon(){
		return icon;
	}
}
