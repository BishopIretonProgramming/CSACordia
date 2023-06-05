package src.gui.buttons;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class Button implements MouseListener {
    private Point pos;
    private Point mousePos = new Point(); //the location of the cursor relative to the swing component

    public Button() {
        this.pos = new Point(0, 0);
    }

    public Button(int x, int y) {
        this.pos = new Point(x, y);
    }

    public abstract void doAction();
    public abstract boolean mouseIsOver(); //if the mouse is over this button

    public void mousePressed(MouseEvent e) {
        mousePos.setLocation(e.getX(), e.getY());
        if(mouseIsOver()) {
            doAction();
        }
    }

    public Point mousePos() {
        return mousePos;
    }

    public int x() {
        return pos.x;
    }

    public int y() {
        return pos.y;
    }

    public Point loc() {
        return pos;
    }

    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
}
