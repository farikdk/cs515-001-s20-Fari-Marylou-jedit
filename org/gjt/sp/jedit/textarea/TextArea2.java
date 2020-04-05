package org.gjt.sp.jedit.textarea;

import org.gjt.sp.jedit.IPropertyManager;
import org.gjt.sp.jedit.input.InputHandlerProvider;

public class TextArea2 extends TextArea {
    /**
     * Creates a new JEditTextArea.
     *
     * @param propertyManager      the property manager that contains informations like shortcut bindings
     * @param inputHandlerProvider the inputHandlerProvider
     */
    protected TextArea2(IPropertyManager propertyManager, InputHandlerProvider inputHandlerProvider) {
        super(propertyManager, inputHandlerProvider);
    }

    @Override /**
     * Has 4 states based on # of invocations:
     *   1. last character of code (before inline comment)
     *   2. last non whitespace character of the line
     *   3. end of line
     *   4. end of last visible line
     * @param select true if you want to extend selection
     * @since jEdit 4.3pre18
     */
    public void smartEnd(boolean select) {
        goToEndOfWhiteSpace(select);
    } //}}}
}
