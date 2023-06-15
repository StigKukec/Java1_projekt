/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.stig.models;

/**
 *
 * @author natio
 */
public enum UserType {
    MEMBER(0),
    ADMINISTRATOR(1);
    private final Integer userType;

    private UserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getUserType() {
        return userType;
    }

    public static UserType from(Integer userType) {

        for (UserType value : values()) {
            if (value.userType.equals(userType)) {
                return value;
            }
        }
        throw new RuntimeException("Wrong user type");
    }

}
