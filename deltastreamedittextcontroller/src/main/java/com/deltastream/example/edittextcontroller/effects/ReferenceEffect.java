package com.deltastream.example.edittextcontroller.effects;

import android.text.Spannable;
import android.text.Spanned;

import com.deltastream.example.edittextcontroller.RTEditText;

import com.deltastream.example.edittextcontroller.spans.RTSpan;
import com.deltastream.example.edittextcontroller.spans.ReferenceSpan;
import com.deltastream.example.edittextcontroller.utils.Selection;

import org.json.JSONException;
import org.json.JSONObject;

public class ReferenceEffect extends CharacterEffect<String, ReferenceSpan> {

    private String postId;
    JSONObject jsonObject;
    @Override
    protected RTSpan<String> newSpan(String refPostId) {
        return new ReferenceSpan(refPostId);
    }

    @Override
    public void applyToSelection(RTEditText editor, String postJson) {
        Selection selection = getSelection(editor);
        Spannable str = editor.getText();

        try {
            jsonObject = new JSONObject(postJson);
            postId = jsonObject.getString("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (RTSpan<String> span : getSpans(str, selection, SpanCollectMode.EXACT)) {
            str.removeSpan(span);
        }
        str.setSpan(newSpan(postId), selection.start(), selection.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
}