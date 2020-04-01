package org.gjt.sp.jedit;

public class ViewDefaultFrame extends View {
    ViewDefaultFrame(Buffer buffer, ViewConfig config) {
        super(buffer, config);
    }

    @Override protected void setViewConfig(ViewConfig config, String prefix) {
        config.x = getX();
        config.y = getY();
        config.width = getWidth();
        config.height = getHeight();
    }
}
