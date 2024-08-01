package flightreservationsystem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class admin {
    
    private static admin instance = null;
    private final String folderPath;
    private final String adminPassFilePath;
    
    private admin() {
        folderPath = "D:\\fiverr\\nori saudi arabia\\2\\delivery\\frs";
        adminPassFilePath = folderPath+"\\adminpass.txt";
    }
    
    public static admin getInstance(){
        if(instance == null){
            instance = new admin();
        }
        return instance;
    }
    
    public void changeAdminPass(String newPass){
        File oldFile = new File(adminPassFilePath);
        oldFile.delete();
        
        File newFile = new File(folderPath+"\\adminpass.txt");
        try {
            newFile.createNewFile();
            newFile.setReadable(true);
            newFile.setWritable(true);
            try (FileWriter fw = new FileWriter(newFile)) {
                fw.write(newPass);
                fw.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(FlightReservationSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
