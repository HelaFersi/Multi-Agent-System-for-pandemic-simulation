package pandemicSimulation;

import cern.jet.random.Uniform;
import repast.simphony.context.Context;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.parameter.Parameters;
import repast.simphony.query.space.grid.MooreQuery;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridPoint;
import repast.simphony.util.ContextUtils;

public class Infected extends Agent {
	
	
	

	public Infected(Grid<Agent> grid) {
		super(grid);
		infected=true;
		symp = false;
	}
	private int daysInfection;
	private	boolean symp;
	Parameters params = RunEnvironment.getInstance().getParameters();
	double pRec = (double) params.getValue("Recovery probability");
	double pDie = (double) params.getValue("Death probability");
	double  medFacilities = RunEnvironment.getInstance().getParameters().getInteger("Medical Facilities");
	int  maxDays = RunEnvironment.getInstance().getParameters().getInteger("Maximum days of infection");

	@Override
	public void compute() {// to infect sus / to recove 
		
		this.daysInfection ++;
		
		if(daysInfection >= maxDays) {
			symp = true;}
		
		MooreQuery<Agent> query= new MooreQuery<Agent>(grid,this);//set of the neighbours 
		
		for (Agent o : query.query())
			if (o instanceof Susceptible) {
				Uniform u = RandomHelper.createUniform();
		
				if (u.nextDoubleFromTo(0, 1)<pRec&&u.nextDoubleFromTo(0, 1)<pDie)
					infected=false;
				}				
	}

	@Override
	public void implement() {
		if (! infected) {//if infected is false
			GridPoint gpt = grid.getLocation(this);
			Context<Object> context = ContextUtils.getContext(this);
			context.remove(this);
			Uniform u = RandomHelper.createUniform();
			if(u.nextDoubleFromTo(0, 1)<medFacilities)
			{
			Rcovered r = new Rcovered(grid);
			context.add(r);
			grid.moveTo(r, gpt.getX(), gpt.getY());
			}
			else {
				Dead d = new Dead(grid);
				context.add(d);
				grid.moveTo(d, gpt.getX(), gpt.getY());
				
				}
			}
		
		
	}

}
