/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.stig.util.validations;

import com.microsoft.sqlserver.jdbc.StringUtils;
import java.awt.Component;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author natio
 */
public class EmptyInput extends Component {

    public EmptyInput() {
        throw new RuntimeException("no can do");
    }

    public static boolean valueValid(List<Component> components) {
        for (Component component : components) {

            if (component instanceof JTextArea ta) {
                if (ta.getText().isEmpty()) {
                    return false;
                }
            }
            if (component instanceof JTextField tf) {
                if (tf.getText().isEmpty()) {
                    return false;
                }
            }
            if (component instanceof JComboBox cb) {
                if (cb.getSelectedItem() == null) {
                    return false;
                }
            }
            if (component instanceof JSpinner sp) {
                if (sp.getValue() == null) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void setEmpty(List<Component> inputs) {
        for (Component input : inputs) {
            if (input instanceof JTextArea ta) {
                ta.setText(StringUtils.EMPTY);
            }
            if (input instanceof JTextField tf) {
                tf.setText(StringUtils.EMPTY);
            }
            if (input instanceof JComboBox cb) {

            }
        }
    }
    public static void setEmpty(List<Component> inputs, int selectedEntity) {
        selectedEntity = 0;
        for (Component input : inputs) {
            if (input instanceof JTextArea ta) {
                ta.setText(StringUtils.EMPTY);
            }
            if (input instanceof JTextField tf) {
                tf.setText(StringUtils.EMPTY);
            }
            if (input instanceof JComboBox cb) {

            }
        }
    }

    public static void setEmpty(List<Component> inputs, ImageIcon image) {
        for (Component input : inputs) {
            if (input instanceof JTextArea ta) {
                ta.setText(StringUtils.EMPTY);
            }
            if (input instanceof JTextField tf) {
                tf.setText(StringUtils.EMPTY);
            }
            if (input instanceof JComboBox cb) {

            }
            if (input instanceof JLabel lb) {
                if (lb.getIcon() != null) {
                    lb.setIcon(image);
                }
            }
        }
    }
    public static void setEmpty(List<Component> inputs, ImageIcon image, int selectedEntity) {
        selectedEntity = 0;
        for (Component input : inputs) {
            if (input instanceof JTextArea ta) {
                ta.setText(StringUtils.EMPTY);
            }
            if (input instanceof JTextField tf) {
                tf.setText(StringUtils.EMPTY);
            }
            if (input instanceof JComboBox cb) {

            }
            if (input instanceof JLabel lb) {
                if (lb.getIcon() != null) {
                    lb.setIcon(image);
                }
            }
        }
    }
    /*
    private boolean valueValid() {
        for (Component component : movieLayout) {
            switch (component) {
                case JTextArea ta -> {
                    if (ta.getText().trim().isEmpty()) {
                        return false;
                    }
                }
                case JTextField tf -> {
                    if (tf.getText().trim().isEmpty()) {
                        return false;
                    }
                }
                case JComboBox cb -> {
                    if (cb.getSelectedItem() == null) {
                        return false;
                    }
                }
                case JSpinner sp -> {
                    if (sp.getValue() == null) {
                        return false;
                    }
                }
                case default -> {
                }
            }
        }
        return true;
    }
     */
}
