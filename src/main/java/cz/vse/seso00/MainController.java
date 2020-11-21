package cz.vse.seso00;

import cz.vse.seso00.model.*;
import javafx.event.ActionEvent;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import sun.misc.IOUtils;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Map;

/*******************************************************************************
 * Třída MainController slouží k ovládání grafické verze aplikace
 *
 * @author Ondřej Šesták
 * @version listopad 2020 (1.0)
 */
public class MainController {

    public TextArea textOutput;
    public TextField textInput;
    private IHra hra;

    public Label locationName;
    public Label locationDescription;

    public VBox exits;
    public VBox items;
    public VBox backpack;
    public VBox npcs;
    public ImageView updateBackground;
    public Button kopat;

    /**
     * Metoda inicializujíci hru
     *
     * @param hra
     */
    public void init(IHra hra) {
        this.hra = hra;
        update();
        textOutput.appendText(hra.vratUvitani() + "\n");
    }

    /**
     * Metoda která aktualizuje všechny prvky
     * nacházející se na scéně (východy,předměty,batoh,npc,pozadí)
     * a zviditelní tlačítko na kopání pokud je v batohu lopata.
     * Zárověň všechny prvky zamkne, pokud je hra u konce.
     */
    private void update() {
//        String location = getAktualniProstor().getNazev();
//        locationName.setText(location);

        String description = getAktualniProstor().getPopis();
        locationDescription.setText(description);

        updateExits();
        updateItems();
        updateBatoh();
        updateNpc();
        updateBackground();

        if (hra.getHerniPlan().getBatoh().obsahujeVec("lopata")) {
            kopat.setVisible(true);

        } else {
            kopat.setVisible(false);
        }

        if (hra.konecHry()) {
            kopat.setVisible(false);
            textOutput.appendText(hra.vratEpilog());
            textInput.setVisible(false);
        } else {
            textInput.setVisible(true);
        }
    }

    /**
     * Metoda která aktualizuje a následně zobrazuje
     * npc nacházející se v aktuálním prostoru
     * v podobě názvu postavy a jejího obrázku.
     * Zároveň při kliknutí na obrázek nebo název proběhne interakce s danou postavou.
     */
    private void updateNpc() {
        Collection<Npc> npcList = getAktualniProstor().getNpcs().values();
        npcs.getChildren().clear();

        for (Npc npc : npcList) {
            String npcName = npc.getNazev();
            String chce = npc.getChce().getNazev();
            Label npcLabel = new Label(npcName);
            InputStream Stream = getClass().getClassLoader().getResourceAsStream(npcName + ".jpg");
            Image img = new Image(Stream);
            ImageView imageView = new ImageView(img);
            imageView.setFitWidth(60);
            imageView.setFitHeight(40);
            npcLabel.setGraphic(imageView);
            if (!hra.konecHry()) {
                npcLabel.setCursor(Cursor.HAND);


                npcLabel.setOnMouseClicked(event -> {
                    if (!npc.getBoj()) {
                        executeCommand("obchod " + npcName + " " + chce);
                        //npc menu
                    } else {
                        executeCommand("napadnout " + npcName);
                    }

                });
            }
            npcs.getChildren().add(npcLabel);
        }
    }

    /**
     * Metoda která aktualizuje a následně zobrazuje
     * věci nacházející se v aktuálním prostoru
     * v podobě názvu předmětu a jeho obrázku.
     * Zároveň při kliknutí na obrázek nebo název daný předmět sebere a vloží jej do batohu.
     */
    private void updateItems() {
        Collection<Vec> itemList = getAktualniProstor().getVeci().values();
        items.getChildren().clear();

        for (Vec item : itemList) {
            String itemName = item.getNazev();
            Label itemLabel = new Label(itemName);
            InputStream Stream = getClass().getClassLoader().getResourceAsStream(itemName + ".jpg");
            Image img = new Image(Stream);
            ImageView imageView = new ImageView(img);
            imageView.setFitWidth(60);
            imageView.setFitHeight(40);
            itemLabel.setGraphic(imageView);

            if (!hra.konecHry()) {
                if (item.isPrenositelna()) {
                    itemLabel.setCursor(Cursor.HAND);
                    itemLabel.setTooltip(new Tooltip(item.getNazev()));

                    itemLabel.setOnMouseClicked(event -> {
                        executeCommand("seber " + itemName);
                    });

                } else if (item.getNazev().equals("truhla")) {
                    itemLabel.setCursor(Cursor.HAND);
                    itemLabel.setOnMouseClicked(event -> {
                        executeCommand("odemkni " + itemName);
                    });

                } else {
                    itemLabel.setTooltip(new Tooltip("Tato vec neni prenositelna  "));
                }
            }
            items.getChildren().add(itemLabel);
        }
    }

    /**
     * Metoda která aktualizuje a následně zobrazuje věci v batohu
     * v podobě názvu předmětu a jeho obrázku.
     * Zároveň při kliknutí na obrázek nebo název daný předmět odloží na zem v aktuálním prostoru.
     */
    private void updateBatoh() {
        Collection<Vec> obsahList = hra.getHerniPlan().getBatoh().getObsah().values();
        backpack.getChildren().clear();


        for (Vec item : obsahList) {
            String itemName = item.getNazev();
            Label itemLabel = new Label(itemName);
            InputStream stream = getClass().getClassLoader().getResourceAsStream(itemName + ".jpg");
            Image img = new Image(stream);
            ImageView imageView = new ImageView(img);
            imageView.setFitWidth(60);
            imageView.setFitHeight(40);
            itemLabel.setGraphic(imageView);
            if (!hra.konecHry()) {
                itemLabel.setCursor(Cursor.HAND);
                itemLabel.setOnMouseClicked(event -> {
                    executeCommand("odlož " + itemName);
                })
                ;
            }
            backpack.getChildren().add(itemLabel);


        }
    }

    /**
     * Metoda která aktualizuje a následně zobrazuje východy
     * z aktuálního prostoru v podobě názvu východu a jeho obrázku.
     * Zároveň při kliknutí na obrázek nebo název do daného prostoru vstoupí.
     */
    private void updateExits() {
        Collection<Prostor> exitList = getAktualniProstor().getVychody();
        exits.getChildren().clear();

        for (Prostor prostor : exitList) {
            String exitName = prostor.getNazev();
            Label exitLabel = new Label(exitName);

            exitLabel.setTooltip(new Tooltip(prostor.getPopis()));

            InputStream Stream = getClass().getClassLoader().getResourceAsStream(exitName + ".jpg");
            Image img = new Image(Stream);
            ImageView imageView = new ImageView(img);
            imageView.setFitWidth(60);
            imageView.setFitHeight(40);
            exitLabel.setGraphic(imageView);
            if (!hra.konecHry()) {
                exitLabel.setCursor(Cursor.HAND);

                exitLabel.setOnMouseClicked(event -> {
                    executeCommand("jdi " + exitName);

                });
            }
            exits.getChildren().add(exitLabel);

        }

    }

    /**
     * Metoda která aktualizuje a následovně zobrazuje
     * obsah batohu v podobě názvu itemu a jeho obrázku.
     */
    private void updateBackground() {

        Prostor prostor = hra.getHerniPlan().getAktualniProstor();
        String prostorName = prostor.getNazev();


        InputStream Stream = getClass().getClassLoader().getResourceAsStream(prostorName + ".jpg");
        Image img = new Image(Stream);

        updateBackground.setImage(img);
        updateBackground.setFitWidth(1200);
        updateBackground.setFitHeight(600);


    }

    /**
     * Metoda která provede příkaž
     * zadaný v parametru a aktualizuje hru.
     *
     * @param command
     */
    private void executeCommand(String command) {
        String result = hra.zpracujPrikaz(command);
        textOutput.appendText(result + "\n\n");
        update();
    }

    /**
     * Metoda která vrací aktuální prostor.
     *
     * @return aktualniProstor
     */
    private Prostor getAktualniProstor() {
        return hra.getHerniPlan().getAktualniProstor();
    }

    /**
     * Metoda která při stlačení entru
     * odešle příkaz z textového pole ke zpracování.
     *
     * @param keyEvent
     */
    public void onInputKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            executeCommand(textInput.getText());
            textInput.setText("");
        }
    }

    /**
     * Metoda která provede příkaz kopej
     * když proběhne actionEvent.
     *
     * @param actionEvent
     */
    public void kopat(ActionEvent actionEvent) {
        executeCommand("kopej truhla");
    }

    /**
     * Metoda která otevře prohlížeč a
     * zobrazí nápovědu v podobě html souboru
     * když proběhne actionEvent.
     *
     * @param actionEvent
     */
    public void napoveda(ActionEvent actionEvent) {
        try {
            Desktop desktop = java.awt.Desktop.getDesktop();
            URI oURL = new URI("http://localhost:63342/Adventura4IT115/n%C3%A1pov%C4%9Bda.html?_ijt=ckevl2kdm0e5mttfvgebdjvihs");
            desktop.browse(oURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda která začne novou hru
     * když proběhne actionEvent.
     *
     * @param actionEvent
     */
    public void novahra(ActionEvent actionEvent) {

        executeCommand("konec");
        textOutput.clear();
        textOutput.setText("restaroval jsi hru\n\n");
        init(new Hra());
    }
}
