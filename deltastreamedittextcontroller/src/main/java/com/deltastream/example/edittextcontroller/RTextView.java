/*
 * Copyright 2015 Emanuel Moecklin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.deltastream.example.edittextcontroller;

import android.content.Context;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import com.deltastream.example.edittextcontroller.api.format.RTFormat;
import com.deltastream.example.edittextcontroller.api.format.RTText;
import com.deltastream.example.edittextcontroller.spans.LinkSpan;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;

/**

 * The actual rich text editor (extending android.widget.EditText).
 */
public class RTextView extends AppCompatTextView implements LinkSpan.LinkSpanListener {

    private MentionClickedListener mentionClickedListener;
    private HashTagClickedListener hashTagClickedListener;

    public interface MentionClickedListener{
        public void onMentionClicked(int clickedPosition);
    }
    public interface HashTagClickedListener{
        public void onHashTagClicked(int position);
    }


    public void setHashTagClickedListener(HashTagClickedListener hashTagClickedListener) {

        this.hashTagClickedListener = hashTagClickedListener;
    }

    public void setMentionClickedListener(MentionClickedListener mentionClickedListener) {
        this.mentionClickedListener = mentionClickedListener;
    }

    public RTextView(Context context){
        super(context);
        init();

    }

    public RTextView(Context context,AttributeSet attrs){
        super(context,attrs);
        init();
    }

    public RTextView(Context context,AttributeSet attrs,int defStyle){
        super(context,attrs,defStyle);
        init();
    }

    private void init(){

        setMovementMethod(LinkMovementMethod.getInstance());
    }

    public Spannable cloneSpannable(String mText) {

        String text = mText;
        return new ClonedSpannableString(text != null ? text : "");
    }

    public void setText(RTText rtText){
        if(rtText.getFormat()instanceof RTFormat.Html){

                RTText rtSpanned = rtText.convertTo(RTFormat.SPANNED);
                //SpannableString spannableString = new SpannableString(rtSpanned.getText().toString());
                //Spannable text = cloneSpannable(rtSpanned)
                SpannableString clickedHashedText = clickHashTags(rtSpanned.getText());
                super.setText(clickedHashedText,BufferType.SPANNABLE);

        }
    }
    private SpannableString clickHashTags(CharSequence text){
        SpannableString spannableString = new SpannableString(text);
        Matcher tagMatcher = Pattern.compile("#([A-Za-z0-9_-]+)").matcher(text);

           int i = 0;
           while (tagMatcher.find()) {
               int j = i + 1;
               i = j;
               RTTextViewHashTagsSpan RTTextViewHashTagsSpan = new RTTextViewHashTagsSpan(ContextCompat.getColor(getContext(), R.color.mention_hashtag_color)) {
                   @Override
                   public void onClick(@NonNull View widget) {

                       hashTagClickedListener.onHashTagClicked(j);
                   }
               };

               spannableString.setSpan(RTTextViewHashTagsSpan, tagMatcher.start(), tagMatcher.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

           }
               Matcher mentionMatcher = Pattern.compile("@([A-Za-z0-9_-\\u00A0]+)").matcher(text);

                 int k = -1;
                 while (mentionMatcher.find()) {
                     int l = k + 1;
                     k = l;
                     RTTextViewMentionsSpan rtTextViewMentionsSpan = new RTTextViewMentionsSpan(ContextCompat.getColor(getContext(), R.color.mention_hashtag_color)) {
                         @Override
                         public void onClick(@NonNull View widget) {

                             if(mentionClickedListener != null) mentionClickedListener.onMentionClicked(l);
                             //Toast.makeText(getContext(), "Mention at position "+Integer.toString(l), Toast.LENGTH_SHORT).show();
                         }
                     };
                     spannableString.setSpan(rtTextViewMentionsSpan, mentionMatcher.start(), mentionMatcher.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                 }
                 return spannableString;
    }

    @Override
    public void onClick(LinkSpan linkSpan) {
        Toast.makeText(getContext(), linkSpan.getURL(), Toast.LENGTH_SHORT).show();
    }

    public abstract class RTTextViewHashTagsSpan extends ClickableSpan{

        private int mNormalTextColor;

        public RTTextViewHashTagsSpan(int normalTextColor){
            this.mNormalTextColor = normalTextColor;
        }

        @Override
        public void updateDrawState(TextPaint ds){
            super.updateDrawState(ds);
            ds.setUnderlineText(false);
            ds.setColor(mNormalTextColor);
        }
    }
    public abstract class RTTextViewMentionsSpan extends ClickableSpan{
        private boolean mIsPressed;
        private int mNormalTextColor;

        public RTTextViewMentionsSpan(int normalTextColor){
            this.mNormalTextColor = normalTextColor;
        }

        @Override
        public void updateDrawState(TextPaint ds){
            super.updateDrawState(ds);
            ds.setUnderlineText(false);
            ds.setColor(mNormalTextColor);
        }
    }

}




