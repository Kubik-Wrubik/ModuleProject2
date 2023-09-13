package island.entities.herbivorous;

import island.Cell;

public class Buffalo extends Herbivorous {
	public final String icon = "\uD83D\uDC03";
	public Buffalo(Cell cell, Object lock){
		super( 700, 3, 100, 40, 3, cell, lock);
	}

	@Override
	public String getIcon(){
		return icon;
	}
}
