package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.lang.reflect.Field;
import java.util.Arrays;

public class Controller {

    @FXML
    private VBox mainVBox;

    @FXML
    private Button confirmButton;

    @FXML
    void confirmButtonClicked(ActionEvent event) {

    }

    @FXML
    void initialize() throws ClassNotFoundException {
        Class<?> cl = Class.forName("sample.Client");
        Field[] fields = cl.getDeclaredFields();
        Arrays.stream(fields)
                .filter(field -> field.isAnnotationPresent(MyPattern.class))
                .forEach(field -> {
                    MyPatternValidator mpv = new MyPatternValidator(field.getAnnotation(MyPattern.class));
                    VinputText vt = new VinputText(mainVBox,field);
                    vt.registerValidator(mpv);
                    vt.checkFieldListener();
                });
    }


}
