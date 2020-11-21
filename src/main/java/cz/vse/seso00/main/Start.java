package cz.vse.seso00.main;

import cz.vse.seso00.MainController;
import cz.vse.seso00.model.Hra;
import cz.vse.seso00.model.IHra;
import cz.vse.seso00.textUi.TextoveRozhrani;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.io.InputStream;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;

/*******************************************************************************
 * Třída  Start je hlavní třídou projektu,
 * který představuje jednoduchou textovou adventuru určenou k dalším úpravám a rozšiřování
 *
 * @author Jarmila Pavlíčková
 * @version ZS 2016/2017
 */
public class Start extends Application {
    /***************************************************************************
     * Metoda, prostřednictvím níž se spouští celá aplikace.
     *
     * @param args Parametry příkazového řádku
     */
    public static void main(String[] args) {
        IHra hra = new Hra();
        TextoveRozhrani ui = new TextoveRozhrani(hra);


        List<String> vstup = Arrays.asList(args);

        if (vstup.contains("text")) {
            ui.hraj();

//        } else if (vstup.contains(".txt")) {
//            ui.hrajZeSouboru(args[0]);

        } else {
            launch();
        }
    }
    /***************************************************************************
     * Metoda, prostřednictvím níž se spouští javaFx aplikace.
     *
     * @param primaryStage
     */

    @Override
    public void start(Stage primaryStage) throws Exception {

        System.out.println("startuji");
        primaryStage.setTitle("Pirát");

        FXMLLoader loader = new FXMLLoader();
        InputStream stream = getClass().getClassLoader().getResourceAsStream("scene.fxml");
        Parent root = loader.load(stream);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        primaryStage.setMaxWidth(1760);
        primaryStage.setMaxHeight(990);

        primaryStage.setMinWidth(1760);
        primaryStage.setMinHeight(990);

        MainController controller = loader.getController();
        IHra hra = new Hra();
        controller.init(hra);

        primaryStage.show();


    }


}