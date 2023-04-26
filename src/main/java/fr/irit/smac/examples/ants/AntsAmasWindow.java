package fr.irit.smac.examples.ants;

import fr.irit.smac.amak.scheduling.Schedulable;
import fr.irit.smac.amak.scheduling.SchedulableExecutionException;
import fr.irit.smac.amak.scheduling.Scheduler;
import fr.irit.smac.amak.ui.MainWindow;
import fr.irit.smac.amak.ui.SchedulerToolbar;
import fr.irit.smac.amak.ui.VectorialGraphicsPanel;
import fr.irit.smac.amak.ui.drawables.DrawableImage;
import fr.irit.smac.amak.ui.drawables.DrawableRectangle;
import fr.irit.smac.amak.ui.drawables.DrawableString;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class AntsAmasWindow extends MainWindow implements Schedulable {
	private final DrawableString antsCountLabel;
	private final AntHill amas;
	private final VectorialGraphicsPanel graphicsPanel;
	private Map<AntAgent, DrawableImage> agentsDrawable = new HashMap<>();

	public AntsAmasWindow(Scheduler scheduler, AntHill amas) {
		super();
		scheduler.add(this);
		this.amas = amas;
		addToolbar(new SchedulerToolbar("Scheduler", scheduler));


		graphicsPanel = new VectorialGraphicsPanel("Ants");
		setLeftPanel(graphicsPanel);

		DrawableRectangle d = new DrawableRectangle(graphicsPanel, 0, 0, 800, 600);
		d.setStrokeOnly();
		new DrawableRectangle(graphicsPanel, 90, 20, 180, 40).setColor(new Color(0.9f, 0.9f, 0.9f, 0.8f)).setFixed().setLayer(5);

		new DrawableImage(graphicsPanel, 20, 20, getClass().getResource("/ant.png").getFile()).setFixed().setLayer(10);
		antsCountLabel = (DrawableString) new DrawableString(graphicsPanel, 45, 25, "Ants count").setFixed().setLayer(10);
	}

	@Override
	public void cycle() throws InterruptedException, SchedulableExecutionException {
		antsCountLabel.setText("Ants count: " + amas.getAgents().size());

		for (var agent :
				amas.getAgents()) {
			if (agent instanceof AntAgent antAgent)
				drawAnt(antAgent);
		}
		var itr = agentsDrawable.entrySet().iterator();
		while (itr.hasNext()) {
			var e = itr.next();
			if (!amas.getAgents().contains(e.getKey())) {
				e.getValue().remove();
				itr.remove();
			}
		}
	}

	private void drawAnt(AntAgent antAgent) {
		var ad = agentsDrawable.computeIfAbsent(antAgent, (a) -> {
			return new DrawableImage(graphicsPanel, 0, 0, getClass().getResource("/ant.png").getFile());
		});
		ad.move(antAgent.dx, antAgent.dy);
		ad.setAngle(antAgent.angle);
	}

	@Override
	public boolean stopCondition() {
		return false;
	}

	@Override
	public void onSchedulingStarts() {

	}

	@Override
	public void onSchedulingStops() {

	}
}
