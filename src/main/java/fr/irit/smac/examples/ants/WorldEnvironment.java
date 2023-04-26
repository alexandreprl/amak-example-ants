package fr.irit.smac.examples.ants;

import fr.irit.smac.amak.Environment;

public class WorldEnvironment extends Environment {

	private int width;
	private int height;

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	@Override
	public void onInitialization() {
		this.width = 800;
		this.height = 600;
	}
}
