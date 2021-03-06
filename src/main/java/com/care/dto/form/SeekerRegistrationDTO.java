package com.care.dto.form;

import com.care.annotation.Name;
import com.care.annotation.Number;

import java.util.Map;

public class SeekerRegistrationDTO extends RegistrationFormDTO {

    private String numberOfChildren;
    private String spouseName;

    @Name(required = false)
    public String getSpouseName() {
        return spouseName;
    }

    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }

    @Number(required = false)
    public String getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(String numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    @Override
    public String toString() {
        return "SeekerRegistrationDTO{" +
                "numberOfChildren='" + numberOfChildren + '\'' +
                ", spouseName='" + spouseName + '\'' +
                '}' + super.toString() ;
    }

    @Override
    public void validateCustom(Map<String, String> errors) {
        super.validateCustom(errors);
    }
}
