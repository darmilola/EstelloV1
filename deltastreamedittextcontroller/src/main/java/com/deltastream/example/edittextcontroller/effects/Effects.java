/*
 * Copyright (C) 2015-2018 Emanuel Moecklin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.deltastream.example.edittextcontroller.effects;




import com.deltastream.example.edittextcontroller.RTEditText;
import com.deltastream.example.edittextcontroller.fonts.RTTypeface;
import com.deltastream.example.edittextcontroller.spans.AbsoluteSizeSpan;
import com.deltastream.example.edittextcontroller.spans.BackgroundColorSpan;
import com.deltastream.example.edittextcontroller.spans.BoldSpan;
import com.deltastream.example.edittextcontroller.spans.ForegroundColorSpan;
import com.deltastream.example.edittextcontroller.spans.ForumPostModel;
import com.deltastream.example.edittextcontroller.spans.ItalicSpan;
import com.deltastream.example.edittextcontroller.spans.LinkSpan;
import com.deltastream.example.edittextcontroller.spans.MentionSpan;
import com.deltastream.example.edittextcontroller.spans.ReferenceSpan;
import com.deltastream.example.edittextcontroller.spans.StrikethroughSpan;
import com.deltastream.example.edittextcontroller.spans.SubscriptSpan;
import com.deltastream.example.edittextcontroller.spans.SuperscriptSpan;
import com.deltastream.example.edittextcontroller.spans.TypefaceSpan;
import com.deltastream.example.edittextcontroller.spans.UnderlineSpan;


import java.util.ArrayList;

public class Effects {
    // character effects
    public static final Effect<Boolean, BoldSpan> BOLD = new BoldEffect();                             // boolean effect
    public static final Effect<Boolean, ItalicSpan> ITALIC = new ItalicEffect();                       // boolean effect
    public static final Effect<Boolean, UnderlineSpan> UNDERLINE = new UnderlineEffect();              // boolean effect
    public static final Effect<Boolean, StrikethroughSpan> STRIKETHROUGH = new StrikethroughEffect();  // boolean effect
    public static final Effect<Boolean, SuperscriptSpan> SUPERSCRIPT = new SuperscriptEffect();        // boolean effect
    public static final Effect<Boolean, SubscriptSpan> SUBSCRIPT = new SubscriptEffect();              // boolean effect
    public static final Effect<Integer, AbsoluteSizeSpan> FONTSIZE = new AbsoluteSizeEffect();         // non-boolean effect
    public static final Effect<Integer, ForegroundColorSpan> FONTCOLOR = new ForegroundColorEffect();
    public static final Effect<Integer, BackgroundColorSpan> BGCOLOR = new BackgroundColorEffect();    // non-boolean effect
    public static final Effect<String, LinkSpan> LINK = new LinkEffect();
    public static final Effect<String, ReferenceSpan> POSTREF = new ReferenceEffect();
    public static final Effect<String, MentionSpan> MENTION = new MentionEffect();
    public static final Effect<String, ReferenceSpan> HASHTAG = new HashtagEffect();
    public static final Effect<RTTypeface, TypefaceSpan> TYPEFACE = new TypefaceEffect();  // non-boolean effect

    // paragraph effects
    public static final BulletEffect BULLET = new BulletEffect();                                      // boolean effect
    public static final NumberEffect NUMBER = new NumberEffect();                                      // boolean effect
    public static final IndentationEffect INDENTATION = new IndentationEffect();                       // non-boolean effect
    public static final AlignmentEffect ALIGNMENT = new AlignmentEffect();                             // non-boolean effect

    /*
     * ALL_EFFECTS is a list of all defined effects, for simpler iteration over all effects.
     */
    public static final ArrayList<Effect> ALL_EFFECTS = new ArrayList<Effect>();

    static {
        // character effects
        ALL_EFFECTS.add(BOLD);
        ALL_EFFECTS.add(ITALIC);
        ALL_EFFECTS.add(UNDERLINE);
        ALL_EFFECTS.add(STRIKETHROUGH);
        ALL_EFFECTS.add(SUPERSCRIPT);
        ALL_EFFECTS.add(SUBSCRIPT);
        ALL_EFFECTS.add(FONTSIZE);
        ALL_EFFECTS.add(POSTREF);
        ALL_EFFECTS.add(FONTCOLOR);
        ALL_EFFECTS.add(BGCOLOR);
        ALL_EFFECTS.add(TYPEFACE);
        ALL_EFFECTS.add(LINK);
        ALL_EFFECTS.add(MENTION);
        ALL_EFFECTS.add(HASHTAG);
        // paragraph effects
        ALL_EFFECTS.add(BULLET);
        ALL_EFFECTS.add(NUMBER);
        ALL_EFFECTS.add(INDENTATION);
        ALL_EFFECTS.add(ALIGNMENT);
    }

    /*
     * FORMATTING_EFFECTS is a list of all effects which will be removed when the formatting is removed from the text.
     */
    public static final ArrayList<Effect> FORMATTING_EFFECTS = new ArrayList<Effect>();

    static {
        // character effects
        FORMATTING_EFFECTS.add(BOLD);
        FORMATTING_EFFECTS.add(ITALIC);
        FORMATTING_EFFECTS.add(UNDERLINE);
        FORMATTING_EFFECTS.add(STRIKETHROUGH);
        FORMATTING_EFFECTS.add(SUPERSCRIPT);
        FORMATTING_EFFECTS.add(SUBSCRIPT);
        FORMATTING_EFFECTS.add(FONTSIZE);
        FORMATTING_EFFECTS.add(FONTCOLOR);
        FORMATTING_EFFECTS.add(POSTREF);
        FORMATTING_EFFECTS.add(MENTION);
        FORMATTING_EFFECTS.add(HASHTAG);
        FORMATTING_EFFECTS.add(TYPEFACE);
        FORMATTING_EFFECTS.add(BGCOLOR);
        FORMATTING_EFFECTS.add(LINK);

        // paragraph effects
        FORMATTING_EFFECTS.add(BULLET);
        FORMATTING_EFFECTS.add(NUMBER);
        FORMATTING_EFFECTS.add(INDENTATION);
        FORMATTING_EFFECTS.add(ALIGNMENT);
    }

    /**
     * This important method makes sure that all paragraph effects are applied to whole paragraphs.
     * While it's optimized for performance it's still an expensive operation so it shouldn't be
     * called too often.
     *
     * @param exclude if an Effect has just been applied, there's no need to cleanup that Effect.
     */
    public static void cleanupParagraphs(RTEditText editor, Effect...exclude) {
        cleanupParagraphs(editor, Effects.ALIGNMENT, exclude);
        cleanupParagraphs(editor, Effects.INDENTATION, exclude);
        cleanupParagraphs(editor, Effects.BULLET, exclude);
        cleanupParagraphs(editor, Effects.NUMBER, exclude);
    }

    private static void cleanupParagraphs(RTEditText editor, ParagraphEffect effect, Effect...exclude) {
        for (Effect e : exclude) {
            if (effect == e) {
                return;
            }
        }

        effect.applyToSelection(editor, null, null);
    }

}
