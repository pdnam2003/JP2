package E2.Entity;

public enum Gender {
    MALE("Male"), FEMALE("Female");
    private String genderLabel;
    Gender (String genderLabel){
        this.genderLabel = genderLabel;
    }
    public String GenderLabel(){
        return genderLabel;
    }
    public void setGenderLabel(String genderLabel){
        this.genderLabel =genderLabel;
    }

}
