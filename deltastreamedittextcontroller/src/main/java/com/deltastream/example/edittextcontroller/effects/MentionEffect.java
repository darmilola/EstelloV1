package com.deltastream.example.edittextcontroller.effects;

import android.text.Spannable;
import android.text.Spanned;

import com.deltastream.example.edittextcontroller.RTEditText;
import com.deltastream.example.edittextcontroller.spans.MentionSpan;
import com.deltastream.example.edittextcontroller.spans.RTSpan;
import com.deltastream.example.edittextcontroller.spans.ReferenceSpan;
import com.deltastream.example.edittextcontroller.utils.Selection;

import org.json.JSONException;
import org.json.JSONObject;

public class MentionEffect extends CharacterEffect<String, MentionSpan> {

    private String displayName;
    JSONObject jsonObject;
    String mentionJson;

    @Override
    protected RTSpan<String> newSpan(String mentionJson) {
        MentionSpan mentionSpan = new MentionSpan(mentionJson);
        return mentionSpan;
    }

    @Override
    public void applyToSelection(RTEditText editor, String mentionJson) {
        Selection selection = getSelection(editor);
        Spannable str = editor.getText();
        try {
            jsonObject = new JSONObject(mentionJson);
            displayName = jsonObject.getString("displayName");
            this.mentionJson = mentionJson;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (RTSpan<String> span : getSpans(str, selection, SpanCollectMode.EXACT)) {
            str.removeSpan(span);
        }
        str.setSpan(newSpan(mentionJson), selection.start(), selection.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }
}