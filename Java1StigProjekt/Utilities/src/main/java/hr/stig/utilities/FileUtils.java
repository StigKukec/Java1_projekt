/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.stig.utilities;

import hr.stig.factory.UrlConnectionFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    private static final String DIRECTORY = "assets";
    private static boolean SUCCESSFUL_DELETION = true;

    private FileUtils() {
        throw new RuntimeException("no no no");
    }

    private static void createDirectoryHierarchy(String destination) throws IOException {
        String dir = destination.substring(0, destination.indexOf(File.separator));
        if (!Files.exists(Paths.get(dir))) {
            Files.createDirectories(Paths.get(dir));
        }
    }

    public static void deleteDirectory() throws IOException {
        if (Files.exists(Paths.get(DIRECTORY))) {
            File fileItems = new File(DIRECTORY);
            File[] files = fileItems.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        boolean deleatedFile = file.delete();
                        SUCCESSFUL_DELETION = deleatedFile;
                        if (deleatedFile) {
                            System.out.println("Successfully deleted file!");
                        } else {
                            System.out.println("Deleting file failed!");
                        }
                    }
                }
                if (SUCCESSFUL_DELETION) {
                    MessageUtils.messageSuccess("MOVIE DELETION", "Deletion was successful.");
                } else {
                    MessageUtils.messageError("MOVIE DELETION ERROR", "Something went wrong while deleting movies.");
                }
            }
        } else {
            MessageUtils.messageError("NOT EXISTANT DIRECTORY", "There is no directory from which deletion can be done.");
        }
    }

    public static void copyFromUrl(String source, String destination) throws IOException {
        createDirectoryHierarchy(destination);
        //DIRECTORY = destination;
        HttpURLConnection con = UrlConnectionFactory.getHttpURLConnection(source);
        try (InputStream is = con.getInputStream();) {
            Files.copy(is, Paths.get(destination));
        }

    }

    public static Optional imageChooser(String description, String... extensions) {
        JFileChooser chooser = new JFileChooser(
                FileSystemView.getFileSystemView()
        );
        chooser.setFileFilter(new FileNameExtensionFilter(description, extensions));
        //chooser.setToolTipText("Upload");
        chooser.setDialogTitle(FILE_CHOOSER_TITLE);
        chooser.setApproveButtonText(UPLOAD);
        chooser.setApproveButtonToolTipText(UPLOAD);
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            String fileName = file.getName();
            String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
            return Optional.of(file.exists() ? file : Optional.empty());
        }
        return Optional.empty();
    }

}
