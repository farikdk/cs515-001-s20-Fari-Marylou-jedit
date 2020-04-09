package org.gjt.sp.jedit.textarea;

import org.gjt.sp.jedit.*;
import org.gjt.sp.jedit.gui.DefaultInputHandler;
import org.gjt.sp.jedit.gui.InputHandler;
import org.gjt.sp.jedit.gui.KeyEventTranslator;
import org.gjt.sp.jedit.input.TextAreaInputHandler;
import org.gjt.sp.jedit.syntax.ParserRuleSet;
import org.gjt.sp.jedit.syntax.TokenMarker;
import org.jedit.keymap.EmacsUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import org.gjt.sp.jedit.View;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.swing.*;
import java.util.Hashtable;
import java.util.Map;


public class JEditTextAreaTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void createJEditTextArea()
    {
        /**
        Buffer buffer = new Buffer(null, false, true, new Hashtable<String,Object>(), true);
        View.ViewConfig config = new View.ViewConfig();
        View view = new View(buffer, config);
        TextArea textarea = null;

        if(view.getInputHandler().getLastActionCount() ==1)
              textarea = JEditTextArea.createJEditTextArea(view);
        assertTrue( textarea instanceof JEditTextArea1);
         */
    }
}
