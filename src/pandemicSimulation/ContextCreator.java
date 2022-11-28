package pandemicSimulation;


import cern.jet.random.Uniform;

import repast.simphony.context.Context;
import repast.simphony.context.space.grid.GridFactory;
import repast.simphony.context.space.grid.GridFactoryFinder;
import repast.simphony.dataLoader.ContextBuilder;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridBuilderParameters;
import repast.simphony.space.grid.SimpleGridAdder;
import repast.simphony.space.grid.WrapAroundBorders;

public class ContextCreator implements ContextBuilder<Agent> {

	@Override
	public Context build(Context<Agent> context) {
		context.setId("Pandemic");
		int width = RunEnvironment.getInstance().getParameters().getInteger("gridWidth");
		int height = RunEnvironment.getInstance().getParameters().getInteger("gridHeight");
		double pInf = RunEnvironment.getInstance().getParameters().getInteger("Infection probability");
		double pRec = RunEnvironment.getInstance().getParameters().getInteger("Recovery probability");
		double pDie = RunEnvironment.getInstance().getParameters().getInteger("Death probability");
		double  medFacilities = RunEnvironment.getInstance().getParameters().getInteger("Medical Facilities");
		GridFactory gridFactory = GridFactoryFinder.createGridFactory(null);
		Grid<Agent> grid = gridFactory.createGrid("grid", context,
		new GridBuilderParameters<Agent>(new WrapAroundBorders(),new SimpleGridAdder<Agent>(), false, width, height));
		//return null;
	
	for (int x=0; x<50;x++) {
		for (int y=0;y<50;y++) {
			Uniform u = RandomHelper.createUniform();
			u.nextDoubleFromTo(0, 1);
			boolean b= Math.random()>0.5;//Math.random() sup à 0.5 donc b=true?
			//if math.random() entre 0 et 0.2 so susceptible if 0.2 et 0.4 so Infectious with symptoms
			//Agent a =b? new AliveAgent(grid): new DeadAgent(grid);
		//	context.add(a);//to add agents to the grid
			//grid.moveTo(a, x,y);//to choose where to put the agent
		}
	}
	return context;
	}
	

}
