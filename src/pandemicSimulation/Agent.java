package pandemicSimulation;


import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.space.grid.Grid;

public abstract class Agent {
	protected Grid<Agent> grid;
	
	protected boolean infected;	

	public Agent(Grid<Agent> grid) {
		super();
		this.grid = grid;
	}
	@ScheduledMethod(start = 1, interval = 1, priority = 2)
	public abstract void compute();
	@ScheduledMethod(start = 1, interval = 1, priority = 1)
	public abstract void implement();

}
