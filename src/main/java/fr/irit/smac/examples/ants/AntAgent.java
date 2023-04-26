package fr.irit.smac.examples.ants;

import fr.irit.smac.amak.Agent;
import fr.irit.smac.amak.ui.VectorialGraphicsPanel;
import fr.irit.smac.amak.ui.drawables.DrawableImage;

public class AntAgent extends Agent<AntHill, WorldEnvironment> {
	/**
	 * X coordinate of the ant in the world
	 */
	public double dx;
	/**
	 * Y coordinate of the ant in the world
	 */
	public double dy;
	private final VectorialGraphicsPanel graphicsPanel;
	/**
	 * Angle in radians
	 */
	private double angle = Math.random() * Math.PI * 2;
	private DrawableImage image;

	/**
	 * Constructor of the ant
	 *
	 * @param amas
	 *            the amas the ant belongs to
	 * @param startX
	 *            Initial X coordinate
	 * @param startY
	 *            Initial Y coordinate
	 */
	public AntAgent(AntHill amas, double startX, double startY, VectorialGraphicsPanel graphicsPanel) {
		super(amas);
		dx = startX;
		dy = startY;
		this.graphicsPanel = graphicsPanel;
		image = new DrawableImage(graphicsPanel, dx, dy, getClass().getResource("/ant.png").getFile());
	}

	/**
	 * Move in a random direction
	 */
	@Override
	protected void onDecideAndAct() {
		double random = amas.getEnvironment().getRandom().nextGaussian();
		angle += random * 0.1;
		dx += Math.cos(angle);
		dy += Math.sin(angle);
		while (dx >= getAmas().getEnvironment().getWidth() / 2)
			dx -= getAmas().getEnvironment().getWidth();
		while (dy >= getAmas().getEnvironment().getHeight() / 2)
			dy -= getAmas().getEnvironment().getHeight();
		while (dx < -getAmas().getEnvironment().getWidth() / 2)
			dx += getAmas().getEnvironment().getWidth();
		while (dy < -getAmas().getEnvironment().getHeight() / 2)
			dy += getAmas().getEnvironment().getHeight();

		if (amas.getEnvironment().getRandom().nextDouble() < 0.001) {
			image.setFilename(getClass().getResource("/ant_dead.png").getFile());
			destroy();
		}

		if (amas.getEnvironment().getRandom().nextDouble() < 0.001) {
			new AntAgent(getAmas(), dx, dy, graphicsPanel);
		}
	}

	@Override
	protected void onAgentCycleEnd() {
		image.move(dx, dy);
		image.setAngle(angle);
	}

}
