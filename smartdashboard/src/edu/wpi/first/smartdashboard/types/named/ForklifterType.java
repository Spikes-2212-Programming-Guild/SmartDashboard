package edu.wpi.first.smartdashboard.types.named;

import edu.wpi.first.smartdashboard.gui.elements.Forklifter;
import edu.wpi.first.smartdashboard.types.NamedDataType;

public class ForklifterType extends NamedDataType {
	public static final String LABEL = "Forklifter";

	private ForklifterType() {
		super(LABEL, Forklifter.class);
	}

	public static NamedDataType get() {
		if (NamedDataType.get(LABEL) != null) {
			return NamedDataType.get(LABEL);
		} else {
			return new ForklifterType();
		}
	}
}
