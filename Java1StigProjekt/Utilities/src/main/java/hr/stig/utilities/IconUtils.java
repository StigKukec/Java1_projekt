/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.stig.utilities;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author natio
 */
public class IconUtils {

    private IconUtils() {
        throw new RuntimeException();
    }

    public static ImageIcon setPoster(File fileImage, int width, int height) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(fileImage);
        Image image = bufferedImage.getScaledInstance(
                width,
                height,
                Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }

}
