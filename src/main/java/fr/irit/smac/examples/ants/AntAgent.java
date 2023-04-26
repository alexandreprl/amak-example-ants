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
	/**
	 * Angle in radians
	 */
	public double angle = Math.random() * Math.PI * 2;

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
	public AntAgent(AntHill amas, double startX, double startY) {
		super(amas);
		dx = startX;
		dy = startY;
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
			destroy();
		}

		if (amas.getEnvironment().getRandom().nextDouble() < 0.001) {
			new AntAgent(getAmas(), dx, dy);
		}
	}
}
