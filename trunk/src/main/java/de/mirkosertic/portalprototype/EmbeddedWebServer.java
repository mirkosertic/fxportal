/**
 * FreeDesktopSearch - A Search Engine for your Desktop
 * Copyright (C) 2013 Mirko Sertic
 *
 * This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation; either version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program; if not, see <http://www.gnu.org/licenses/>.
 */
package de.mirkosertic.portalprototype;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.webapp.WebAppContext;

public class EmbeddedWebServer {

    private Server jetty;

    public EmbeddedWebServer() {

        jetty = new Server(4711);

        WebAppContext theWebApp = new WebAppContext();
        theWebApp.setContextPath("/");
        theWebApp.setBaseResource(Resource.newClassPathResource("/webapp"));
        theWebApp.setDescriptor("WEB-INF/web.xml");
        theWebApp.setClassLoader(getClass().getClassLoader());

        jetty.setHandler(theWebApp);
    }

    public void start() {
        try {
            jetty.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getWelcomeUrl() {
        return "http://127.0.0.1:4711/index.ftl";
    }

    public void stop() {
        try {
            jetty.stop();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}