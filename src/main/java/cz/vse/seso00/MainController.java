package cz.vse.seso00;

import javafx.scene.Cursor;
import javafx.scene.control.Label;

public class MainController {

    public Label labelA;
    public Label labelB;
    public Label labelC;

    public void init(){
        labelA.setText("text A");
        labelA.setCursor(Cursor.HAND);

    }
}
