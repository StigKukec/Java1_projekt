/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.stig.utilities;

import java.io.File;
import java.util.Optional;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;



/**
 *
 * @author natio
 */
public class FileUtils {
    private static final String UPLOAD = "Upload";
    private static final String FILE_CHOOSER_TITLE = "Poster";
    
    private FileUtils() {
        throw new RuntimeException("no no no");
    }
    
    public static Optional imageChooser(String description ,String...extensions){
            JFileChooser chooser = new JFileChooser(
                FileSystemView.getFileSystemView()
        );
        chooser.setFileFilter(new FileNameExtensionFilter(description,extensions));
        //chooser.setToolTipText("Upload");
        chooser.setDialogTitle(FILE_CHOOSER_TITLE);
        chooser.setApproveButtonText(UPLOAD);
        chooser.setApproveButtonToolTipText(UPLOAD);
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            String fileName = file.getName();
            String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
         return Optional.of(file.exists() ? file: Optional.empty());
        }
        return Optional.empty();
    }

     
}
