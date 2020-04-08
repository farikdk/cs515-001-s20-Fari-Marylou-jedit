/*
 * jEdit - Programmer's Text Editor
 * :tabSize=8:indentSize=8:noTabs=false:
 * :folding=explicit:collapseFolds=1:
 *
 * Copyright © 2020 jEdit contributors
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package org.gjt.sp.jedit.syntax;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class TokenMarkerTest {
    // Use these to set the spanEndSubst attribute uniquely and then use its value to determine if
    // the unwind was done properly
    char[] line1Pattern = new char[]{'f', 'i', 'r', 's', 't'};
    char[] line2Pattern = new char[]{'s', 'e', 'c', 'o', 'n', 'd'};
    char[] line3Pattern = new char[]{'t', 'h', 'i', 'r', 'd'};
    ParserRuleSet rules;
    TokenMarker tokenMarker;

    @Before
    public void setUp() {
        rules = new ParserRuleSet("text","MAIN");
        tokenMarker = new TokenMarker();
        tokenMarker.addRuleSet(rules);
    }

    @After
    public void tearDown() {
    }

    protected ParserRule createRuleNoLineBreak(ParserRuleSet rules) {
    // Set up parent rule to contain NO_LINE_BREAK which enables an unwind
        byte id = 0;
        byte matchType = 0;
        ParserRule rule = ParserRule.createSpanRule(0, "start", 1,
            "end", rules, id, matchType, true, false, "");
        return rule;
    }

    @Test
    public void noUnwindNullParentTest() {
    // No parent or previous line to parse so check that we don't "unwind" and go back to previous line

        // The context attribute is private in the TokenMarker field, so this test method cannot
        // set its value.  Instead we've created a LineContext and will access it directly since its
        // attributes are public.

        // No parent so we won't unwind
        TokenMarker.LineContext parent = null;
        TokenMarker.LineContext child = new TokenMarker.LineContext(rules,parent);
        child.spanEndSubst = line1Pattern;

        // Set compareLC to a clone of the expected results after unwinding
        TokenMarker.LineContext compareLC = (TokenMarker.LineContext) child.clone();

        // The null value of parent will keep us from unwinding so just set terminated to some value
        boolean terminated = true;

        TokenMarker.LineContext processedLC = tokenMarker.unwind(terminated, child);

        assertTrue(processedLC.equals(compareLC));
    }

    @Test
    public void noUnwindNullRuleTest() {
        // Create a parent LineContext and child LineContext and link them together
        // through the child's parent attribute
        TokenMarker.LineContext parent = new TokenMarker.LineContext(rules,null);
        parent.spanEndSubst = line2Pattern;
        //Link the parent to the child
        TokenMarker.LineContext child = new TokenMarker.LineContext(rules,parent);
        child.spanEndSubst = line1Pattern;

        // Set processedLC to a clone of the expected results after unwinding
        TokenMarker.LineContext compareLC = (TokenMarker.LineContext) child.clone();

        // Set up terminated to block unwind
        boolean terminated = false;
        // Set up rule to disable unwind
        parent.setInRule(null);
        TokenMarker.LineContext processedLC = tokenMarker.unwind(terminated, child);

        assertTrue(processedLC.equals(compareLC));
    }

        @Test
    public void unwindTest() {
        // Create a parent LineContext and child LineContext and link them together
        // through the child's parent attribute
        TokenMarker.LineContext parent = new TokenMarker.LineContext(rules,null);
        parent.spanEndSubst = line2Pattern;
        // Set compareLC to a clone of the expected results after unwinding
        // Need to make a clone before we add the ParserRule since that is set to null when unwinding
        TokenMarker.LineContext compareLC = (TokenMarker.LineContext) parent.clone();
        // Set up parser rule with NO_LINE_BREAK action
        ParserRule parentRule = createRuleNoLineBreak(rules);
        parent.setInRule(parentRule);

        // create child and link to the parent
        TokenMarker.LineContext child = new TokenMarker.LineContext(rules,parent);
        child.spanEndSubst = line1Pattern;

        // Set up terminated to block unwind
        boolean terminated = false;

        TokenMarker.LineContext processedLC = tokenMarker.unwind(terminated, child);

        assertTrue(Arrays.equals(line2Pattern,processedLC.spanEndSubst));
        assertTrue(processedLC.equals(compareLC));

    }

    @Test
    public void unwind1Test() {
        // Create three LineContext link them together through the child's parent attribute
        // Only the middle LineContext will contain a rule so unwind should only step back to middle LineContext
        TokenMarker.LineContext grandparent = new TokenMarker.LineContext(rules,null);
        grandparent.spanEndSubst = line3Pattern;
        grandparent.setInRule(null);

        TokenMarker.LineContext parent = new TokenMarker.LineContext(rules,grandparent);
        parent.spanEndSubst = line2Pattern;
        ParserRule parentRule = createRuleNoLineBreak(rules);
        parent.setInRule(parentRule);

        TokenMarker.LineContext child = new TokenMarker.LineContext(rules,parent);
        child.spanEndSubst = line1Pattern;
        child.setInRule(null);

        // Set compareLC to a clone of the expected results after unwinding
        TokenMarker.LineContext compareLC = (TokenMarker.LineContext) child.parent.clone();
        // Need to reset ParserRule to null since unwind will also do that
        compareLC.setInRule(null);

        // Set up terminated to block unwind
        boolean terminated = false;

        TokenMarker.LineContext processedLC = tokenMarker.unwind(terminated, child);

        assertTrue(Arrays.equals(line2Pattern,processedLC.spanEndSubst));
        assertTrue(processedLC.equals(compareLC));

    }

    @Test
    public void unwindTerminatedTest() {
        // Create a parent LineContext and child LineContext and link them together
        // through the child's parent attribute
        TokenMarker.LineContext parent = new TokenMarker.LineContext(rules,null);
        parent.spanEndSubst = line2Pattern;
        parent.setInRule(null);

        //Link the parent to the child
        TokenMarker.LineContext child = new TokenMarker.LineContext(rules,parent);
        child.spanEndSubst = line1Pattern;

        // Set up terminated to enable unwind
        boolean terminated = true;

        // Set compareLC to a clone of the expected results after unwinding
        TokenMarker.LineContext compareLC = (TokenMarker.LineContext) parent.clone();

        TokenMarker.LineContext processedLC = tokenMarker.unwind(terminated, child);

        assertTrue(Arrays.equals(line2Pattern,processedLC.spanEndSubst));
        assertTrue(processedLC.equals(compareLC));
;    }
}