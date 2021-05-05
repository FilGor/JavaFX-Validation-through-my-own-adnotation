package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

import java.lang.reflect.Field;
import java.util.Arrays;

public class Controller {

    @FXML
    private HBox mainHBox;


    @FXML
    void initialize() throws ClassNotFoundException {
        Class<?> cl = Class.forName("sample.Client");
        Field[] fields = cl.getDeclaredFields();
        Arrays.stream(fields)
                .filter(field -> field.isAnnotationPresent(MyPattern.class))
                .forEach(field -> {
                    MyPatternValidator mpv = new MyPatternValidator(field.getAnnotation(MyPattern.class));
                    VinputText vt = new VinputText(mainHBox,field);
                    vt.registerValidator(mpv);
                    vt.checkFieldListener();
                });
    }


}
