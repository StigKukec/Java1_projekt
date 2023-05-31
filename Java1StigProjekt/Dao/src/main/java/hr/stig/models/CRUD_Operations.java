/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.stig.models;

/**
 *
 * @author natio
 */
public enum CRUD_Operations {
    MOVIE("Movie"),
    ACTOR("Actor"),
    DIRECTOR("Director");
    

    private final String operation;

    private CRUD_Operations(String operation) {
        this.operation = operation;
    }

    public String getOperation() {
        return operation;
    }

    public static CRUD_Operations from(String operation) {
        for (CRUD_Operations value : values()) {
            if (value.operation.equals(operation)) {
                return value;
            }
        }
        throw new RuntimeException("ne postoji odabrana operacija");
    }

}
