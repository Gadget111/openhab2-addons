/**
 * Copyright (c) 2014-2016 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.energeniemihome.internal;

import static org.openhab.binding.energeniemihome.EnergenieMiHomeBindingConstants.THING_TYPE_MIHOME_SWITCH;

import java.util.Collections;
import java.util.Set;

import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingTypeUID;
import org.eclipse.smarthome.core.thing.binding.BaseThingHandlerFactory;
import org.eclipse.smarthome.core.thing.binding.ThingHandler;
import org.openhab.binding.energeniemihome.handler.EnergenieMiHomeHandler;

/**
 * The {@link EnergenieMiHomeHandlerFactory} is responsible for creating things and thing
 * handlers.
 *
 * @author Steve Henderson - Initial contribution
 */
public class EnergenieMiHomeHandlerFactory extends BaseThingHandlerFactory {

    private final static Set<ThingTypeUID> SUPPORTED_THING_TYPES_UIDS = Collections.singleton(THING_TYPE_MIHOME_SWITCH);

    @Override
    public boolean supportsThingType(ThingTypeUID thingTypeUID) {
        return SUPPORTED_THING_TYPES_UIDS.contains(thingTypeUID);
    }

    @Override
    protected ThingHandler createHandler(Thing thing) {

        ThingTypeUID thingTypeUID = thing.getThingTypeUID();

        if (thingTypeUID.equals(THING_TYPE_MIHOME_SWITCH)) {
            return new EnergenieMiHomeHandler(thing);
        }

        return null;
    }

}
