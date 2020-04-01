package org.gjt.sp.jedit;

public class ViewMaxVerticalHorizontalFrame extends View {
        ViewMaxVerticalHorizontalFrame(Buffer buffer, ViewConfig config) {
                super(buffer, config);
        }
        @Override protected void setViewConfig(ViewConfig config, String prefix) {
                config.x = jEdit.getIntegerProperty(prefix + ".x",getX());
                config.y = jEdit.getIntegerProperty(prefix + ".y",getY());
                config.width = jEdit.getIntegerProperty(prefix + ".width",getWidth());
                config.height = jEdit.getIntegerProperty(prefix + ".height",getHeight());
        }
}
