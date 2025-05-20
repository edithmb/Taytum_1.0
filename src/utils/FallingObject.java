package utils;

import javax.swing.*;
import java.awt.*;

public class FallingObject {
    public int x,y;
    public int w,h;
    public int speed;
    public String path;
    private Image image;

    public FallingObject(int x, int y, int w, int h, int speed, String path) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.speed = speed;
        java.net.URL imageURL = getClass().getResource(path);
        if (imageURL != null) {
            ImageIcon object = new ImageIcon(imageURL);
            this.image = object.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
        } else {
            System.out.println("No se encontró la imagen: " + path);
        }

    }

    public void update (){
        y += speed;
    }

    public void draw(Graphics g, Component c){
        if ( image != null){
            g.drawImage(image,x,y,c);
        }

    }
    public Rectangle getBounds() {
        return new Rectangle(x,y,w,h);
    }
}
