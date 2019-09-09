package w;

import finalproject.*;
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
    
    public customButton(Color background, Color pressed, Color hovered){
        this.background = background;
        this.pressed = pressed;
        this.hovered = hovered;
        setFocusPainted(false);
        setContentAreaFilled(false);
        setOpaque(true);
        setBackground(background);
        addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
                if (getModel().isPressed()) {
                    setBackground(pressed);
                } else if (getModel().isRollover()) {
                    setBackground(hovered);
                } else {
                    setBackground(background);
                }
            }
        });
    }
    
    public customButton(ImageIcon display, ImageIcon press, ImageIcon hover){
        this.display = display;
        this.press = press;
        this.hover = hover;
        setFocusPainted(false);
        setContentAreaFilled(false);
        setOpaque(true);
        setIcon(display);
        addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
                if (getModel().isPressed()) {
                    setIcon(press);
                } else if (getModel().isRollover()) {
                    setIcon(hover);
                } else {
                    setIcon(display);
                }
            }
        });
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
        addChangeListener(new ChangeListener() {
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
        });
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
        addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
                if (getModel().isPressed()) {
                    setIcon(press);
                } else if (getModel().isRollover()) {
                    setIcon(hover);
                } else {
                    setIcon(display);
                }
            }
        });
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
        addChangeListener(new ChangeListener() {
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
        });
    }
}
