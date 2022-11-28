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
import cern.jet.random.Uniform;

public class Susceptible extends Agent {
	
	Parameters params = RunEnvironment.getInstance().getParameters();

	double pInf = (double) params.getValue("Infection probability");
	
	public Susceptible(Grid<Agent> grid) {
		super(grid);
		infected = false;
	}

	//double pInf = RunEnvironment.getInstance().getParameters().getInteger("Infection probability");

	
	
	@Override
	public void compute() {// to get infected 
		MooreQuery<Agent> query= new MooreQuery<Agent>(grid,this);//set of the neighbours 
		
		for (Agent o : query.query())
			if (o instanceof Infected) {
				Uniform u = RandomHelper.createUniform();
		
				if (u.nextDoubleFromTo(0, 1)<pInf)
					infected=true;
				
				}
					
	}

	@Override
	public void implement() {//implement an infected agent 
		if (!infected) {
			GridPoint gpt = grid.getLocation(this);
			Context<Object> context = ContextUtils.getContext(this);
			context.remove(this);
			Infected iA = new Infected(grid);
			context.add(iA);
			grid.moveTo(iA, gpt.getX(), gpt.getY());
			}
		
	}

}
