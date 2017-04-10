/**
 * Copyright (c) 2014-2016 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.energeniemihome.handler;

import static org.openhab.binding.energeniemihome.EnergenieMiHomeBindingConstants.MIHOME_SWITCH_CHANNEL_ID;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;

import org.eclipse.smarthome.config.core.Configuration;
import org.eclipse.smarthome.core.library.types.OnOffType;
import org.eclipse.smarthome.core.thing.ChannelUID;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingStatus;
import org.eclipse.smarthome.core.thing.ThingStatusDetail;
import org.eclipse.smarthome.core.thing.binding.BaseThingHandler;
import org.eclipse.smarthome.core.types.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@link EnergenieMiHomeHandler} is responsible for handling commands, which are
 * sent to one of the channels.
 *
 * @author Steve Henderson - Initial contribution
 */
public class EnergenieMiHomeHandler extends BaseThingHandler {

    private Logger logger = LoggerFactory.getLogger(EnergenieMiHomeHandler.class);
    private String username;
    private String password;

    public EnergenieMiHomeHandler(Thing thing) {
        super(thing);
    }

    @Override
    public void handleCommand(ChannelUID channelUID, Command command) {
        if (channelUID.getId().equals(MIHOME_SWITCH_CHANNEL_ID)) {
            logger.debug("MiHome switch command received: {}", command);
            logger.info("Mihome switch err switching");
            final Configuration configuration = this.getConfig();
            String itemId = String.valueOf(configuration.get("id"));

            if (command == OnOffType.ON) {
                this.sendCommandON(itemId);

            } else if (command == OnOffType.OFF) {
                this.sendCommandOFF(itemId);
            }
        }
    }

    @Override
    public void initialize() {
        // TODO: Initialize the thing. If done set status to ONLINE to indicate proper working.
        // Long running initialization should be done asynchronously in background.
        updateStatus(ThingStatus.ONLINE);
        final Configuration configuration = this.getConfig();
        if ((configuration != null) && (configuration.get("username") != null)) {
            this.username = String.valueOf(configuration.get("username"));
        }
        if ((configuration != null) && (configuration.get("password") != null)) {
            this.password = String.valueOf(configuration.get("password"));
            logger.info("username set to {}", this.username);
        }
        if (this.username == null || this.password == null) {
            updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.CONFIGURATION_ERROR,
                    "Can not access device as username and/or password are invalid");
        }
    }

    private void sendCommandON(final String itemId) {
        URL url;
        try {
            url = new URL("https://mihome4u.co.uk/api/v1/subdevices/power_on?params={\"id\":" + itemId + "}");
            Base64.Encoder encoder = Base64.getEncoder();
            String encoding = encoder.encodeToString(("email@address.com:password").getBytes());
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("User-Agent", "OpenHab command");
            connection.setRequestProperty("Authorization", "Basic " + encoding);
            InputStream content = connection.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(content));
        } catch (MalformedURLException e) {
            logger.debug("Unable to connect to Energenie - Something has gone very wrong! has the Url changed?");
            updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.CONFIGURATION_ERROR, "Issue with the URL");
            e.printStackTrace();
        } catch (IOException e) {
            logger.debug("Unable to connect to Energenie - Check internet connection");
            // No need to take it offline.
            e.printStackTrace();
        }
    }

    private void sendCommandOFF(final String itemId) {

        URL url;
        try {
            url = new URL("https://mihome4u.co.uk/api/v1/subdevices/power_off?params={\"id\":" + itemId + "}");
            Base64.Encoder encoder = Base64.getEncoder();
            String encoding = encoder.encodeToString(("email@password.com:password").getBytes());
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("User-Agent", "OpenHab command");
            connection.setRequestProperty("Authorization", "Basic " + encoding);
            InputStream content = connection.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(content));
        } catch (MalformedURLException e) {
            logger.debug("Unable to connect to Energenie - Something has gone very wrong! has the Url changed?");
            updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.CONFIGURATION_ERROR, "Issue with the URL");
            e.printStackTrace();
        } catch (IOException e) {
            logger.debug("Unable to connect to Energenie - Check internet connection");
            // No need to take it offline.
            e.printStackTrace();
        }
    }
}
