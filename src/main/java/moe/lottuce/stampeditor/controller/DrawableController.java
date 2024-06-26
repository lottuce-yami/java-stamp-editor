package moe.lottuce.stampeditor.controller;

import moe.lottuce.stampeditor.drawable.Drawable;

public abstract class DrawableController {
    protected MainController mainController;

    protected Drawable drawable;

    public abstract void initDrawable();

    public MainController getMainController() {
        return mainController;
    }

    public void setMainController(MainController controller) {
        this.mainController = controller;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }
}
