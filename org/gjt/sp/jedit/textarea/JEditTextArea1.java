package org.gjt.sp.jedit.textarea;

import org.gjt.sp.jedit.Macros;
import org.gjt.sp.jedit.View;

public class JEditTextArea1 extends JEditTextArea {
    public JEditTextArea1(View view) {
        super(view);
    }

    @Override
    protected void checkRecorder(boolean select, Macros.Recorder recorder) {
        if(recorder != null)
            recorder.record("textArea.goToEndOfWhiteSpace(" + select + ");");

        goToEndOfWhiteSpace(select);

    }
}
