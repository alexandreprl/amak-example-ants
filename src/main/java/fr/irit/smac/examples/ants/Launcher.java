package fr.irit.smac.examples.ants;

import fr.irit.smac.amak.scheduling.Scheduler;
import fr.irit.smac.amak.ui.MainWindow;
import fr.irit.smac.amak.ui.SchedulerToolbar;
import fr.irit.smac.amak.ui.VectorialGraphicsPanel;

public class Launcher {
	public static void main(String[] args) {
		var window = new MainWindow();
		var graphicsPanel = new VectorialGraphicsPanel("Ants");
		window.setLeftPanel(graphicsPanel);

		var environment = new WorldEnvironment();
		var amas = new AntHill(environment, graphicsPanel);

		var scheduler = new Scheduler(amas, environment);
		window.addToolbar(new SchedulerToolbar("Scheduler", scheduler));
	}
}
