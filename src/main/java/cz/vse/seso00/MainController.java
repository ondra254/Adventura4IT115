package cz.vse.seso00;


import cz.vse.seso00.model.IHra;
import cz.vse.seso00.model.Prostor;
import cz.vse.seso00.model.Vec;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

import java.io.InputStream;
import java.util.Collection;


public class MainController {

    public TextArea textOutput;
    public TextField textInput;
    private IHra hra;

    public Label locationName;
    public Label locationDescription;

    public VBox exits;
    public VBox items;


    public void init(IHra hra) {
        this.hra = hra;
        update();


    }

    private void update() {
        String location = getAktualniProstor().getNazev();
        locationName.setText(location);

        String description = getAktualniProstor().getPopis();
        locationDescription.setText(description);

        updateExits();
        updateItems();
    }

    private void updateItems() {
        Collection<Vec> itemList = getAktualniProstor().getVeci().values();
        items.getChildren().clear();

        for (Vec item : itemList) {
            String itemName = item.getNazev();
            Label itemLabel = new Label(itemName);
            if (item.isPrenositelna()) {
                itemLabel.setCursor(Cursor.HAND);
                itemLabel.setTooltip(new Tooltip(item.getNazev()));

                InputStream Stream = getClass().getClassLoader().getResourceAsStream(itemName + ".jpg");
                Image img = new Image(Stream);
                ImageView imageView = new ImageView(img);
                imageView.setFitWidth(60);
                imageView.setFitHeight(40);
                itemLabel.setGraphic(imageView);

                itemLabel.setOnMouseClicked(event -> {
                    executeCommand("seber " + itemName);
                });

            } else {
                itemLabel.setTooltip(new Tooltip("Tato vec neni prenositelna  "));
            }
            items.getChildren().add(itemLabel);
        }
    }

    private void updateExits() {
        Collection<Prostor> exitList = getAktualniProstor().getVychody();
        exits.getChildren().clear();

        for (Prostor prostor : exitList) {
            String exitName = prostor.getNazev();
            Label exitLabel = new Label(exitName);
            exitLabel.setCursor(Cursor.HAND);
            exitLabel.setTooltip(new Tooltip(prostor.getPopis()));

            InputStream Stream = getClass().getClassLoader().getResourceAsStream(exitName + ".jpg");
            Image img = new Image(Stream);
            ImageView imageView = new ImageView(img);
            imageView.setFitWidth(60);
            imageView.setFitHeight(40);
            exitLabel.setGraphic(imageView);

            exitLabel.setOnMouseClicked(event -> {
                executeCommand("jdi " + exitName);

            });
            exits.getChildren().add(exitLabel);

        }

    }

    private void executeCommand(String command) {
        String result = hra.zpracujPrikaz(command);
        textOutput.appendText(result + "\n\n");
        update();
    }

    private Prostor getAktualniProstor() {

        return hra.getHerniPlan().getAktualniProstor();
    }


    public void onInputKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode()== KeyCode.ENTER) {
            executeCommand(textInput.getText());
            textInput.setText("");
        }
    }
}
