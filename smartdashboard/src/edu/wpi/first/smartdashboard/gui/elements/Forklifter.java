package edu.wpi.first.smartdashboard.gui.elements;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import edu.wpi.first.smartdashboard.gui.elements.bindings.AbstractTableWidget;
import edu.wpi.first.smartdashboard.properties.Property;
import edu.wpi.first.smartdashboard.types.DataType;
import edu.wpi.first.smartdashboard.types.named.ForklifterType;
import edu.wpi.first.wpilibj.tables.ITable;

public class Forklifter extends AbstractTableWidget {

	public static final DataType[] TYPES = { ForklifterType.get() };

	JPanel panel;
	double min, max, height;
	boolean top, bot;

	public static final int MALGEZA_LENGTH = 100, MALGEZA_HEIGHT = 40,
			START_X = MALGEZA_LENGTH + 10, START_Y = 80, ROBOT_WIDTH = 150,
			ROBOT_HEIGHT = 340;
	double malgezaY;

	@Override
	public void propertyChanged(Property property) {
		// TODO Auto-generated method stub

	}

	@Override
	public void booleanChanged(ITable source, String key, boolean value,
			boolean isNew) {
		// TODO Auto-generated method stub
		switch (key) {
		case "up":
			this.top = value;
			break;
		case "down":
			this.bot = value;
			break;
		}
		repaint();
	}

	@Override
	public void doubleChanged(ITable source, String key, double value,
			boolean isNew) {
		switch (key) {
		case "height":
			this.height = value;
			break;
		case "maxHeight":
			this.max = value;
			break;
		case "minHeight":
			this.min = value;
			break;
		}
		malgezaY = START_Y + (1 - (height - min) / (max - min))
				* (ROBOT_HEIGHT - MALGEZA_HEIGHT);
		repaint();
	}

	@Override
	public void init() {
		panel = new JPanel() {

			@Override
			public void paint(Graphics g) {
				super.paint(g);
				if (Forklifter.this.max - Forklifter.this.min > 1e-2) {
					int[] x = { START_X, START_X, START_X + ROBOT_WIDTH };
					int[] y = { START_Y, START_Y + ROBOT_HEIGHT,
							START_Y + ROBOT_HEIGHT };

					Graphics2D g2d = (Graphics2D) g;
					g2d.setStroke(new BasicStroke(30f));

					// draw ROBOT (Triangle)
					g2d.drawPolygon(x, y, x.length);
					if (top || bot) {
						g.setColor(Color.GREEN);
					}
					// draw MALGEZA
					g.fillRect(START_X - MALGEZA_LENGTH, (int) malgezaY,
							MALGEZA_LENGTH, MALGEZA_HEIGHT);
				}
			}
		};
		panel.setPreferredSize(new Dimension(300, 480));
		add(panel);
	}

}
