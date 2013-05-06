package de.mirkosertic.portalprototype;

import insidefx.undecorator.Undecorator;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class PortalApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private EmbeddedWebServer embeddedWebServer;
    private Stage stage;

    @Override
    public void start(Stage aStage) throws Exception {

        stage = aStage;

        // Boot embedded JSP container
        embeddedWebServer = new EmbeddedWebServer();
        embeddedWebServer.start();

        aStage.setTitle("Portal Prototype");
        aStage.setWidth(800);
        aStage.setHeight(600);
        aStage.initStyle(StageStyle.TRANSPARENT);

        FXMLLoader theLoader = new FXMLLoader(getClass().getResource("/scenes/mainscreen.fxml"));
        AnchorPane theMainScene = (AnchorPane) theLoader.load();

        final PortalController theController = theLoader.getController();
        theController.configure(this, embeddedWebServer.getWelcomeUrl(), stage.getOwner());

        Undecorator theUndecorator = new Undecorator(stage, theMainScene);
        theUndecorator.getStylesheets().add("/skin/undecorator.css");

        Scene theScene = new Scene(theUndecorator);
        theScene.setFill(Color.TRANSPARENT);
        aStage.setScene(theScene);

        aStage.show();

        aStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent aEvent) {
                shutdown();
            }
        });
    }

    public void shutdown() {
        embeddedWebServer.stop();
        stage.hide();

        // Raw shutdown of all threads
        System.exit(0);
    }
}
