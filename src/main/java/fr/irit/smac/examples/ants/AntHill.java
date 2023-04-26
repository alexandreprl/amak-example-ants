package fr.irit.smac.examples.ants;

import fr.irit.smac.amak.Amas;
import fr.irit.smac.amak.ui.MainWindow;
import fr.irit.smac.amak.ui.VectorialGraphicsPanel;
import fr.irit.smac.amak.ui.drawables.DrawableImage;
import fr.irit.smac.amak.ui.drawables.DrawableRectangle;
import fr.irit.smac.amak.ui.drawables.DrawableString;

import java.awt.*;

public class AntHill extends Amas<WorldEnvironment> {

	public AntHill(WorldEnvironment environment) {
		super(environment, 1, ExecutionPolicy.ONE_PHASE);

		for (int i = 0; i < 50; i++)
			new AntAgent(this, 0, 0);
	}
}
