package org.gjt.sp.jedit;

public class ViewMaxHorizontalFrame extends View {
        ViewMaxHorizontalFrame(Buffer buffer, ViewConfig config) {
                super(buffer, config);
        }
        @Override protected void setViewConfig(ViewConfig config, String prefix) {
                config.x = jEdit.getIntegerProperty(prefix + ".x",getX());
                config.y = getY();
                config.width = jEdit.getIntegerProperty(prefix + ".width",getWidth());
                config.height = getHeight();
        }
}
