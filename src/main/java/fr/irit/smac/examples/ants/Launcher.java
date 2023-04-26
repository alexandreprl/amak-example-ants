package fr.irit.smac.examples.ants;

import fr.irit.smac.amak.scheduling.Scheduler;
import fr.irit.smac.amak.ui.MainWindow;
import fr.irit.smac.amak.ui.SchedulerToolbar;
import fr.irit.smac.amak.ui.VectorialGraphicsPanel;

public class Launcher {
	public static void main(String[] args) {

		var environment = new WorldEnvironment();
		var amas = new AntHill(environment);

		var scheduler = new Scheduler(amas, environment);

		new AntsAmasWindow(scheduler, amas);
	}
}
