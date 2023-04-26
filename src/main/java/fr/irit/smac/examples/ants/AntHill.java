package fr.irit.smac.examples.ants;

import fr.irit.smac.amak.Amas;
import fr.irit.smac.amak.ui.MainWindow;
import fr.irit.smac.amak.ui.VectorialGraphicsPanel;
import fr.irit.smac.amak.ui.drawables.DrawableImage;
import fr.irit.smac.amak.ui.drawables.DrawableRectangle;
import fr.irit.smac.amak.ui.drawables.DrawableString;

import java.awt.*;

public class AntHill extends Amas<WorldEnvironment> {
	private final VectorialGraphicsPanel graphicsPanel;
	private DrawableString antsCountLabel;

	public AntHill(WorldEnvironment environment, VectorialGraphicsPanel graphicsPanel) {
		super(environment, 1, ExecutionPolicy.ONE_PHASE);
		this.graphicsPanel = graphicsPanel;

		for (int i = 0; i < 50; i++)
			new AntAgent(this, 0, 0, graphicsPanel);
		
		DrawableRectangle d = new DrawableRectangle(graphicsPanel, 0, 0, 800, 600);
		d.setStrokeOnly();
		new DrawableRectangle(graphicsPanel, 90, 20, 180, 40).setColor(new Color(0.9f, 0.9f, 0.9f, 0.8f)).setFixed().setLayer(5);

		new DrawableImage(graphicsPanel, 20, 20, getClass().getResource("/ant.png").getFile()).setFixed().setLayer(10);
		antsCountLabel = (DrawableString) new DrawableString(graphicsPanel, 45, 25, "Ants count").setFixed().setLayer(10);

	}

	@Override
	protected void onSystemCycleEnd() {
		antsCountLabel.setText("Ants count: " + getAgents().size());
	}
}
