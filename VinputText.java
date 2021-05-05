package sample;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.lang.reflect.Field;

public class VinputText { //extends hbox?
    Image correctimage = new Image("file:C:\\Users\\Feeli\\IdeaProjects\\untitled\\src\\sample\\resources\\check.png");
    Image notCorrectimage = new Image("file:C:\\Users\\Feeli\\IdeaProjects\\untitled\\src\\sample\\resources\\notecheck.png");

    private HBox subHBox;
    private TextInputControl textInput;
    private Field uploadedField;
    private Validator consideredValidator;
    private ImageView image;

    public VinputText(HBox mainhbox, Field field){

    this.subHBox = new HBox();
    this.textInput = new TextField();
    this.uploadedField = field;

    this.image = new ImageView();
    subHBox.getChildren().add(image);
    subHBox.getChildren().add(textInput);
    subHBox.getChildren().add(new Label(field.getName()));
    mainhbox.getChildren().add(subHBox);


    }
    void registerValidator(Validator v){
        this.consideredValidator =v;
    }

    void checkFieldListener(){
        textInput.textProperty().addListener((observable -> {
            consideredValidator.validate(textInput.getText());
            if (consideredValidator.isValid()) {
                image.setImage(correctimage);
            }else{
                image.setImage(notCorrectimage);
            }
            System.out.println(consideredValidator.isValid());

        }));
    }


}
