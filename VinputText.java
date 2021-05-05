package sample;


import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class VinputText {
    Image correctimage = new Image("file:C:\\Users\\Feeli\\IdeaProjects\\untitled\\src\\sample\\resources\\check.png");
    Image notCorrectimage = new Image("file:C:\\Users\\Feeli\\IdeaProjects\\untitled\\src\\sample\\resources\\notecheck.png");

    private final Tooltip imagetooltip = new Tooltip();
    private final TextInputControl textInput;
    private final Field uploadedField;
    private Validator consideredValidator;
    static private List<Validator> allValidators = new ArrayList<>();
    private final ImageView image;
    private final Button confirmButton;

    public VinputText(VBox mainvbox, Field field, Button confirmButton){

        HBox subHBox = new HBox();
        if (field.getType().equals(String.class))
        { this.textInput = new TextArea();
        }else{
            this.textInput = new TextField();
        }
        textInput.setPrefSize(200,40);
        this.uploadedField = field;
        this.image = new ImageView();
        this.confirmButton = confirmButton;

    subHBox.getChildren().add(image);
    subHBox.getChildren().add(textInput);
    subHBox.getChildren().add(new Label(field.getName()));
    mainvbox.getChildren().add(subHBox);

    }
    void registerValidator(Validator v){
        this.consideredValidator =v;
        allValidators.add(v);
    }

    private int numberOfAllValidValidators;

    void checkFieldListener(){
        imagetooltip.setText(consideredValidator.getMessage());

        textInput.textProperty().addListener(((observable) -> {
            consideredValidator.validate(textInput.getText());
            if (consideredValidator.isValid()) {
                image.setImage(correctimage);
                image.setOnMouseEntered(null);
                Tooltip.uninstall(image,imagetooltip);

            }else{
                image.setImage(notCorrectimage);
                Tooltip.install(image,imagetooltip);

            }

            numberOfAllValidValidators = allValidators.stream().filter(Validator::isValid).toList().size();

            confirmButton.setDisable(allValidators.size() != numberOfAllValidValidators);
        }));


    }

}