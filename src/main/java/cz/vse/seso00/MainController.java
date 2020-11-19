package cz.vse.seso00;


import cz.vse.seso00.model.IHra;
import cz.vse.seso00.model.Prostor;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Collection;


public class MainController {

    private IHra hra;

    public Label locationName;
    public Label locationDescription;

    public VBox exits;


    public void init(IHra hra, Stage primaryStage){
        this.hra = hra;
        update();


    }

    private void update() {
        String location = getAktualniProstor().getNazev();
        locationName.setText(location);

        String description = getAktualniProstor().getPopis();
        locationDescription.setText(description);

        updateExits();
    }

    private void updateExits() {
        Collection<Prostor> exitList = getAktualniProstor().getVychody();

       for (Prostor prostor : exitList) {
           String exitName = prostor.getNazev();
           Label exitLabel = new Label(exitName);
            exits.getChildren().add(exitLabel);
       }


    }

    private Prostor getAktualniProstor( ){
        return hra.getHerniPlan().getAktualniProstor();
    }


}
