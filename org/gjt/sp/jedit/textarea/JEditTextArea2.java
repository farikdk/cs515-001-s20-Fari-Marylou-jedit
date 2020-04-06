package org.gjt.sp.jedit.textarea;

import org.gjt.sp.jedit.Macros;
import org.gjt.sp.jedit.View;

public class JEditTextArea2 extends JEditTextArea {
    public JEditTextArea2(View view) {
        super(view);
    }

    @Override
    protected void checkRecorder(boolean select, Macros.Recorder recorder) {
        if(recorder != null)
            recorder.record("textArea.goToEndOfLine(" + select + ");");

        goToEndOfLine(select);

    }
}
