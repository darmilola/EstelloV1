package com.deltastream.example.edittextcontroller;

public interface MentionHashTagListener {

    void onMentioning(CharSequence sequence);
    void onHashTagging(CharSequence sequence);
    void onStopMentioning();
    void onStopHashTags();
    void onMentionStarted();
    void onHashTagsStarted();
}
