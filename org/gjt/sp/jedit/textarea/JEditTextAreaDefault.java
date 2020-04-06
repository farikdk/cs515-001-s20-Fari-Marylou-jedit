package org.gjt.sp.jedit.textarea;

import org.gjt.sp.jedit.Macros;
import org.gjt.sp.jedit.View;

public class JEditTextAreaDefault extends JEditTextArea {
    public JEditTextAreaDefault(View view) {
        super(view);
    }
    @Override
    protected void checkRecorder(boolean select, Macros.Recorder recorder) {

        if(recorder != null)
            recorder.record("textArea.goToLastVisibleLine(" + select + ");");
        goToLastVisibleLine(select);

    }
}
