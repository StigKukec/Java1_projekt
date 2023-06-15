/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.stig.utilities;

import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author natio
 */
public class ComponentUtils {

    private ComponentUtils() {
        throw new RuntimeException();
    }

    public static List<Component> getAllComponents(Container container) {
        List<Component> componentList = new ArrayList<>();
        Component[] children = container.getComponents();
        for (Component child : children) {
            componentList.add(child);
               if (child instanceof Container) {
                componentList.addAll(getAllComponents((Container) child));
            }
        }
        return componentList;
    }
}
