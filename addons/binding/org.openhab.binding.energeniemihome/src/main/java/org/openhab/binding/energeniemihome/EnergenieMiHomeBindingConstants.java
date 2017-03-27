/**
 * Copyright (c) 2014-2016 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.energeniemihome;

import java.util.Set;

import org.eclipse.smarthome.core.thing.ThingTypeUID;

import com.google.common.collect.ImmutableSet;

/**
 * The {@link EnergenieMiHomeBinding} class defines common constants, which are
 * used across the whole binding.
 *
 * @author Steve Henderson - Initial contribution
 */
public class EnergenieMiHomeBindingConstants {

    public static final String BINDING_ID = "energeniemihome";

    // List of all Thing Type UIDs
    public final static ThingTypeUID THING_TYPE_MIHOME_SWITCH = new ThingTypeUID(BINDING_ID, "MiHomeSwitch");

    // List of all Channel ids
    public final static String MIHOME_SWITCH_CHANNEL_ID = "switch";

    // Thing type for discovery

    public final static Set<ThingTypeUID> SUPPORTED_THING_TYPES_UIDS = ImmutableSet.of(THING_TYPE_MIHOME_SWITCH);

}
