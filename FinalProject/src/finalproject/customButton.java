package finalproject;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;

public class customButton extends JButton{
    Color background;
    Color pressed;
    Color hovered;
    
    ImageIcon display;
    ImageIcon hover;
    ImageIcon press;
    
    ChangeListener c;
    
    public customButton(Color background, Color pressed, Color hovered){
        this.background = background;
        this.pressed = pressed;
        this.hovered = hovered;
        setFocusPainted(false);
        setContentAreaFilled(false);
        setOpaque(true);
        setBackground(background);
        c = new ChangeListener() {
                public void stateChanged(ChangeEvent evt) {
                    if (getModel().isPressed()) {
                        setBackground(pressed);
                    } else if (getModel().isRollover()) {
                        setBackground(hovered);
                    } else {
                        setBackground(background);
                    }
                }
            };
        addChangeListener(c);
    }
    
    public customButton(ImageIcon display, ImageIcon press, ImageIcon hover){
        this.display = display;
        this.press = press;
        this.hover = hover;
        setFocusPainted(false);
        setContentAreaFilled(false);
        setOpaque(true);
        setIcon(display);
        c = new ChangeListener() {
                public void stateChanged(ChangeEvent evt) {
                    if (getModel().isPressed()) {
                        setIcon(press);
                    } else if (getModel().isRollover()) {
                        setIcon(hover);
                    } else {
                        setIcon(display);
                    }
                }
            };
        addChangeListener(c);
    }
    
    public customButton(Color background, Color pressed, Color hovered, ImageIcon display, ImageIcon press, ImageIcon hover){
        this.background = background;
        this.pressed = pressed;
        this.hovered = hovered;
        this.display = display;
        this.press = press;
        this.hover = hover;
        setFocusPainted(false);
        setContentAreaFilled(false);
        setOpaque(true);
        setBackground(background);
        setIcon(display);
        c = new ChangeListener() {
                public void stateChanged(ChangeEvent evt) {
                    if (getModel().isPressed()) {
                        setBackground(pressed);
                        setIcon(press);
                    } else if (getModel().isRollover()) {
                        setBackground(hovered);
                        setIcon(hover);
                    } else {
                        setBackground(background);
                        setIcon(display);
                    }
                }
            };
        addChangeListener(c);
    }
    
    public customButton(String icon, int scale){
        this.display = Resources.scaleImage(Resources.getImage(icon + ".png"), scale);
        this.press = Resources.scaleImage(Resources.getImage(icon + "_Pressed.png"), scale);
        this.hover = Resources.scaleImage(Resources.getImage(icon + "_Hovered.png"), scale);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setOpaque(true);
        setBackground(background);
        setIcon(display);
        c = new ChangeListener() {
                public void stateChanged(ChangeEvent evt) {
                    if (getModel().isPressed()) {
                        setIcon(press);
                    } else if (getModel().isRollover()) {
                        setIcon(hover);
                    } else {
                        setIcon(display);
                    }
                }
            };
        addChangeListener(c);
    }
    
    public customButton(String icon, int scale, Color background, Color pressed, Color hovered){
        this.background = background;
        this.pressed = pressed;
        this.hovered = hovered;
        this.display = Resources.scaleImage(Resources.getImage(icon + ".png"), scale);
        this.press = Resources.scaleImage(Resources.getImage(icon + "_Pressed.png"), scale);
        this.hover = Resources.scaleImage(Resources.getImage(icon + "_Hovered.png"), scale);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setOpaque(true);
        setBackground(background);
        setIcon(display);
        c = new ChangeListener() {
                public void stateChanged(ChangeEvent evt) {
                    if (getModel().isPressed()) {
                        setBackground(pressed);
                        setIcon(press);
                    } else if (getModel().isRollover()) {
                        setBackground(hovered);
                        setIcon(hover);
                    } else {
                        setBackground(background);
                        setIcon(display);
                    }
                }
            };
        addChangeListener(c);
    }
    
    public void update(Color background, Color pressed, Color hovered){
        setBackground(background);
        this.background = background;
        this.pressed = pressed;
        this.hovered = hovered;
        removeChangeListener(c);
        c = new ChangeListener() {
                public void stateChanged(ChangeEvent evt) {
                    if (getModel().isPressed()) {
                        setBackground(pressed);
                    } else if (getModel().isRollover()) {
                        setBackground(hovered);
                    } else {
                        setBackground(background);
                    }
                }
            };
        addChangeListener(c);
    }
}
