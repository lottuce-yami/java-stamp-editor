package moe.lottuce.stampeditor.controllers;

import moe.lottuce.stampeditor.drawables.Drawable;

public abstract class DrawableController {
    protected MainController mainController;

    protected Drawable drawable;

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

    public abstract void initDrawable();
}
