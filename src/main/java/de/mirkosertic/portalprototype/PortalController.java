/**
 * FXDesktop - An example application to show JavaFX portal capabilities
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

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.web.WebView;
import javafx.stage.Window;
import netscape.javascript.JSObject;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PortalController implements Initializable {

    @FXML
    MenuItem menuItemClose;

    @FXML
    Menu actions;

    @FXML
    WebView webView;

    private PortalApplication application;

    private Window window;

    public void configure(PortalApplication aApplication, String aWelcomeUrl, Window aWindow) {
        window = aWindow;
        application = aApplication;
        webView.getEngine().setJavaScriptEnabled(true);
        //webView.getEngine().getHistory().setMaxSize(0);
        webView.getEngine().getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {

            public void changed(ObservableValue<? extends State> ov, State t, State t1) {
                if (t1 == State.SUCCEEDED) {

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            MicroDataExtractor theExtractor = new MicroDataExtractor();
                            MicroData theMicroData = theExtractor.extractMicroDataFrom(webView.getEngine().getDocument());
                            process(theMicroData);

                            JSObject window = (JSObject) webView.getEngine().executeScript("window");
                            window.setMember("desktop", new DesktopGateway());
                        }
                    });
                }
            }
        });
        webView.getEngine().load(aWelcomeUrl);
        webView.getEngine().setJavaScriptEnabled(true);
    }

    public void initialize(URL aUrl, ResourceBundle aResourceBundle) {
        assert menuItemClose != null;
        assert webView != null;

        menuItemClose.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                close();
            }
        });
    }

    private void process(MicroData aMicrodata) {
        actions.getItems().clear();

        List<MicroDataElement> theElements = aMicrodata.items("http://mirkosertic.de/fxportal/action");
        for (MicroDataElement theElement : theElements) {

            final String theUrl = application.generateUrlFor(theElement.getNode().getAttributes().getNamedItem("href").getNodeValue());

            MenuItem theItem = new MenuItem(theElement.textValue());
            theItem.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    // Open the URL
                    webView.getEngine().load(theUrl);
                }
            });
            actions.getItems().add(theItem);
        }

        actions.setDisable(actions.getItems().size() == 0);
    }

    public void close() {
        application.shutdown();
    }
}