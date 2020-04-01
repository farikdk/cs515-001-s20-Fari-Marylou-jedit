package org.gjt.sp.jedit;

public class ViewMaxVerticalFrame extends View {
        ViewMaxVerticalFrame(Buffer buffer, ViewConfig config) {
                super(buffer, config);
        }
        @Override protected void setViewConfig(ViewConfig config, String prefix) {
                config.x = getX();
                config.y = jEdit.getIntegerProperty(prefix + ".y",getY());
                config.width = getWidth();
                config.height = jEdit.getIntegerProperty(prefix + ".height",getHeight());
        }
}
