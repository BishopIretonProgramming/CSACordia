package src.gui.buttons;

import java.awt.Dimension;

public abstract class ButtonSquare extends Button {
    private Dimension size;

    public ButtonSquare() {
        super();
        this.size = new Dimension(0, 0);
    }

    public ButtonSquare(int x, int y, int w, int h) {
        super(x, y);
        this.size = new Dimension(w, h);
    }

    @Override
    public boolean mouseIsOver() {
        int mX = mousePos().x;
        int mY = mousePos().y;
        return (mX >= x() && mX <= x() + size.width) && (mY >= y() && mY <= y() + size.height);
    }

}
