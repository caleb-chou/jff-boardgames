package finalproject;

import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.sound.sampled.*;

public class Resources {
    public static final String RESOURCES_FOLDER = "Resources/";
    public static final String AUDIO_RESOURCES_FOLDER = "Audio_Resources/";
    
    static HashMap<String, BufferedImage> resources;
    
    static HashMap<String, File> audioResources;
    
    public static void read(){
        File folder = new File(RESOURCES_FOLDER);
        File[] files = folder.listFiles();
        
        resources = new HashMap<>();
        
        for(File f : files){
            BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_3BYTE_BGR);
            try{
                image = ImageIO.read(f);
            } catch(Exception ex) {
                try{
                    File imageFile = new File(RESOURCES_FOLDER + "Error.png");
                    image = ImageIO.read(imageFile);
                } catch(Exception ex2) {System.out.println("File Error.png not found");}
            }
            resources.put(f.getName(), image);
        }
        
        File audioFolder = new File(AUDIO_RESOURCES_FOLDER);
        File[] audioFiles = audioFolder.listFiles();
        
        audioResources = new HashMap<>();
        
        for(File f : audioFiles){
            try {
                File as = new File(AUDIO_RESOURCES_FOLDER + f.getName()).getAbsoluteFile();
                audioResources.put(f.getName(), as);
            } catch (Exception ex){};
        }
    }
    
    public static BufferedImage getImage(String name){
        if(!resources.containsKey(name)) return new BufferedImage(1, 1, BufferedImage.TYPE_3BYTE_BGR);
        return resources.get(name);
    }
    
    public static ImageIcon scaleImage(BufferedImage image, int size){
        return new ImageIcon(image.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH));
    }
    
    public static ImageIcon scaleImage(BufferedImage image, int length, int height){
        return new ImageIcon(image.getScaledInstance(length, height, java.awt.Image.SCALE_SMOOTH));
    }
    
    public static ImageIcon layerImage(ImageIcon background, ImageIcon image, int x, int y){
        BufferedImage CBI = new BufferedImage(x, y, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = CBI.createGraphics();
        g.drawImage(background.getImage(), (x - background.getIconWidth())/2, (y - background.getIconHeight())/2, null);
        g.drawImage(image.getImage(), (x - image.getIconWidth())/2, (y - image.getIconHeight())/2, null);
        g.dispose();
        return new ImageIcon(CBI);
    }
    
    public static void playAudio(String audio){
        if(!audioResources.containsKey(audio)) return;
        File f = audioResources.get(audio);
        try{
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(f);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(gainControl.getMaximum());
            clip.start();
        } catch(Exception ex){};
    }
}
