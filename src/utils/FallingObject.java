package utils;

import javax.swing.*;
import java.awt.*;

public class FallingObject {
    public int x,y;
    public int w,h;
    public int speed;
    public String path;

    public FallingObject(int x, int y, int w, int h, int speed, String path) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.speed = speed;
        this.path = path;
    }

    public void update (){
        y += speed;
    }

    public void draw(Graphics g, Component c){
        ImageIcon object =new ImageIcon(path);
        Image image = object.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
        g.drawImage(image,x,y,c);

    }

    public Rectangle getBounds() {
        return new Rectangle(x,y,w,h);
    }
}
