/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.setter.custom.disposition;

import com.motorola.models.representation.DispatchableIncident;
import com.motorola.models.representation.Disposition;
import com.motorola.translation.setter.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * Custom setter for nested {@link Disposition} object in {@link DispatchableIncident}.
 * This nested object uses fields from parent object payload.
 *
 * When this setter called for some value - it looks down into {@link DispatchableIncident} model
 * for disposition, create it (if needed) and use provided setter to set disposition's field value.
 */
public class DispositionSetter implements Setter<DispatchableIncident> {

	private final BiConsumer<Disposition, Object> dispositionSetFunction;

	public DispositionSetter(BiConsumer<Disposition, Object> dispositionSetFunction) {
		this.dispositionSetFunction = dispositionSetFunction;
	}

	@Override
	public void accept(DispatchableIncident incident, Object value) {
		List<Disposition> dispositions = incident.getDispositions();
		if (dispositions == null) {
			dispositions = new ArrayList<>();
		}
		if (dispositions.isEmpty()) {
			dispositions.add(new Disposition());
		}
		Disposition disposition = dispositions.get(0);
		if (value != null) {
			dispositionSetFunction.accept(disposition, value);
		}
	}
}
