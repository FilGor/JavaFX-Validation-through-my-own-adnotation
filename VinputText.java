package sample;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.lang.reflect.Field;

public class VinputText { //extends hbox?
    Image correctimage = new Image("file:C:\\Users\\Feeli\\IdeaProjects\\untitled\\src\\sample\\resources\\check.png");
    Image notCorrectimage = new Image("file:C:\\Users\\Feeli\\IdeaProjects\\untitled\\src\\sample\\resources\\notecheck.png");

    private Tooltip imagetooltip = new Tooltip();


    private HBox subHBox;
    private TextInputControl textInput;
    private Field uploadedField;
    private Validator consideredValidator;
    private ImageView image;

    public VinputText(VBox mainvbox, Field field){

    this.subHBox = new HBox();
    this.textInput = new TextField();
    this.uploadedField = field;

    this.image = new ImageView();
    subHBox.getChildren().add(image);
    subHBox.getChildren().add(textInput);
    subHBox.getChildren().add(new Label(field.getName()));
    mainvbox.getChildren().add(subHBox);

    }
    void registerValidator(Validator v){
        this.consideredValidator =v;
    }

    void checkFieldListener(){
        imagetooltip.setText(uploadedField.getAnnotation(MyPattern.class).message());

        textInput.textProperty().addListener((observable -> {
            consideredValidator.validate(textInput.getText());
            if (consideredValidator.isValid()) {
                image.setImage(correctimage);
                image.setOnMouseEntered(null);
                Tooltip.uninstall(image,imagetooltip);

            }else{
                image.setImage(notCorrectimage);
                Tooltip.install(image,imagetooltip);
            }

        }));
    }

}