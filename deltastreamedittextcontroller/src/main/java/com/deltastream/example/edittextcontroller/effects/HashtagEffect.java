package com.deltastream.example.edittextcontroller.effects;

import android.text.Spannable;
import android.text.Spanned;

import com.deltastream.example.edittextcontroller.RTEditText;
import com.deltastream.example.edittextcontroller.spans.RTSpan;
import com.deltastream.example.edittextcontroller.spans.ReferenceSpan;
import com.deltastream.example.edittextcontroller.utils.Selection;

import org.json.JSONException;
import org.json.JSONObject;

public class HashtagEffect extends CharacterEffect<String, ReferenceSpan> {

    private String hashtagId;
    JSONObject jsonObject;
    @Override
    protected RTSpan<String> newSpan(String hashtagId) {
        return new ReferenceSpan(hashtagId);
    }

    @Override
    public void applyToSelection(RTEditText editor, String hashtagJson) {
        Selection selection = getSelection(editor);
        Spannable str = editor.getText();

        try {
            jsonObject = new JSONObject(hashtagJson);
            hashtagId = jsonObject.getString("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (RTSpan<String> span : getSpans(str, selection, SpanCollectMode.EXACT)) {
            str.removeSpan(span);
        }
        str.setSpan(newSpan(hashtagId), selection.start(), selection.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }
}