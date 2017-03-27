/**
 * Copyright (c) 2010-2017 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.energeniemihome.discovery;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.smarthome.config.discovery.AbstractDiscoveryService;
import org.eclipse.smarthome.config.discovery.DiscoveryResult;
import org.eclipse.smarthome.config.discovery.DiscoveryResultBuilder;
import org.eclipse.smarthome.config.discovery.DiscoveryServiceCallback;
import org.eclipse.smarthome.core.thing.ThingTypeUID;
import org.eclipse.smarthome.core.thing.ThingUID;
import org.openhab.binding.energeniemihome.EnergenieMiHomeBindingConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnergenieMiHomeDiscoveryService extends AbstractDiscoveryService {

    private final Logger logger = LoggerFactory.getLogger(EnergenieMiHomeDiscoveryService.class);
    private static final int DISCOVER_TIMEOUT_SECONDS = 300;
    private DiscoveryServiceCallback discoveryServiceCallback;

    public EnergenieMiHomeDiscoveryService() throws IllegalArgumentException {
        super(EnergenieMiHomeBindingConstants.SUPPORTED_THING_TYPES_UIDS, DISCOVER_TIMEOUT_SECONDS);
        // TODO Auto-generated constructor stub
        logger.info("Starting the EnergenieMiHome Discovery Service");
    }

    public void setDiscoveryServiceCallback(DiscoveryServiceCallback discoveryServiceCallback) {
        this.discoveryServiceCallback = discoveryServiceCallback;
    }

    /**
     * Called on component activation.
     */
    public void activate() {
        super.activate(null);
    }

    @Override
    public void deactivate() {
        super.deactivate();
    }

    @Override
    public synchronized void abortScan() {
        super.abortScan();
    }

    @Override
    protected synchronized void stopScan() {
        super.stopScan();
    }

    @Override
    public void startScan() {

        // connect to the API
        logger.info("Pretending to scan for stuff");
        // get item collection

        // see if item exists

        // add it to things

        // get list of current things

        // check item collection for things

        // not here.. delete the thing
        Boolean test = false;

        if (test) {
            ThingUID newThingId = new ThingUID(EnergenieMiHomeBindingConstants.THING_TYPE_MIHOME_SWITCH, "12345");
            Map<String, Object> properties = new HashMap<>(2);

            properties.put("id", "12345");
            properties.put("username", "mobile@datapos.co.uk");

            if (discoveryServiceCallback.getExistingDiscoveryResult(newThingId) != null) {
                // "Thing " + thingUID.toString() + " was already discovered"
            }
            if (discoveryServiceCallback.getExistingThing(newThingId) != null) {
                // "Thing " + thingUID.toString() + " already exists"
            }

            DiscoveryResult discoveryResult = DiscoveryResultBuilder.create(newThingId).withLabel("Energenie Switch")
                    .withProperties(properties).build();

            logger.debug("Discovered new thing");

            this.thingDiscovered(discoveryResult);

        }
    }

    @Override
    public Set<ThingTypeUID> getSupportedThingTypes() {
        return EnergenieMiHomeBindingConstants.SUPPORTED_THING_TYPES_UIDS;
    }

}
