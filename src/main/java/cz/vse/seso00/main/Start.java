package cz.vse.seso00.main;

import cz.vse.seso00.model.Hra;
import cz.vse.seso00.model.IHra;
import cz.vse.seso00.textUi.TextoveRozhrani;
import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.SQLOutput;

/*******************************************************************************
 * Třída  Start je hlavní třídou projektu,
 * který představuje jednoduchou textovou adventuru určenou k dalším úpravám a rozšiřování
 *
 * @author    Jarmila Pavlíčková
 * @version   ZS 2016/2017
 */
public class Start extends Application
{
    /***************************************************************************
     * Metoda, prostřednictvím níž se spouští celá aplikace.
     *
     * @param args Parametry příkazového řádku
     */
    public static void main(String[] args) {
//        IHra hra = new Hra();
//        TextoveRozhrani ui = new TextoveRozhrani(hra);
//        if (args.length ==0) {
//            ui.hraj();
//
//            //java -jar Adventura.jar Ahoj
//        }else {
//            ui.hrajZeSouboru(args[0]);
//        }
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("startuji");
        primaryStage.show();
    }
}