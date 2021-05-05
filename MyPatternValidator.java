package sample;

public class MyPatternValidator implements Validator {

    private final MyPattern mpAnnotation;
    private boolean valid = false;

    public MyPatternValidator(MyPattern mpAnnotation){
        this.mpAnnotation = mpAnnotation;
    }

    @Override
    public void validate(String value) {
        valid = value.matches(mpAnnotation.regex());
    }

    @Override
    public boolean isValid() {
        return valid;
    }

    @Override
    public String getMessage() {
       return mpAnnotation.message();
    }
}
