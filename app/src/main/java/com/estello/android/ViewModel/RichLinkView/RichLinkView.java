package com.estello.android.ViewModel.RichLinkView;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import android.text.Spannable;
import android.text.style.URLSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.estello.android.R;
import com.squareup.picasso.Picasso;

/**
 * Created by ponna on 16-01-2018.
 */

public class RichLinkView extends RelativeLayout {

    private View view;
    Context context;
    private MetaData meta;

    LinearLayout linearLayout;
    ImageView imageView;
    TextView textViewTitle;
    TextView textViewDesp;
    TextView textViewSiteName;
    ImageView favicon;
    ImageView smallImage;

    private String main_url;

    private boolean isDefaultClick = true;

    private RichLinkListener richLinkListener;


    public RichLinkView(Context context) {
        super(context);
        this.context = context;
    }

    public RichLinkView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public RichLinkView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RichLinkView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
    }

    public void initView() {

        if(findLinearLayoutChild() != null) {
            this.view = findLinearLayoutChild();
        } else  {
            this.view = this;
            inflate(context, R.layout.link_preview_layout,this);
        }

        linearLayout = (LinearLayout) findViewById(R.id.link_preview_root);
        imageView = (ImageView) findViewById(R.id.link_prev_image);
        textViewTitle = (TextView) findViewById(R.id.link_preview_title);
        textViewDesp = (TextView) findViewById(R.id.link_preview_desc);
        textViewSiteName = (TextView) findViewById(R.id.link_preview_sitename);
        favicon = findViewById(R.id.link_preview_favicon);
        smallImage = findViewById(R.id.link_image_small);



        if(meta.getImageurl().equals("") || meta.getImageurl().isEmpty()) {
            imageView.setVisibility(GONE);
        } else {
            imageView.setVisibility(VISIBLE);
            Picasso.get()
                    .load(meta.getImageurl())
                    .into(imageView);
            Picasso.get()
                    .load(meta.getImageurl())
                    .into(smallImage);
        }

        if(meta.getFavicon().equalsIgnoreCase("") || meta.getFavicon().isEmpty()){

            favicon.setVisibility(GONE);
        }
        else{
            favicon.setVisibility(VISIBLE);
            Picasso.get()
                    .load(meta.getFavicon()).placeholder(R.drawable.greyimage)
                    .into(favicon);
        }

        if(meta.getTitle().isEmpty() || meta.getTitle().equals("")) {
            textViewTitle.setVisibility(GONE);
        } else {
            textViewTitle.setVisibility(VISIBLE);
            textViewTitle.setText(meta.getTitle());
        }

        if(meta.getDescription().isEmpty() || meta.getDescription().equals("")) {
            textViewDesp.setVisibility(GONE);
        } else {
            textViewDesp.setVisibility(VISIBLE);
            textViewDesp.setText(meta.getDescription());
        }
        if(meta.getSitename().isEmpty() || meta.getSitename().equals("")){
            textViewSiteName.setVisibility(GONE);
        }
        else{
            textViewSiteName.setVisibility(VISIBLE);
            textViewSiteName.setText(meta.getSitename());
        }


        linearLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isDefaultClick) {
                    richLinkClicked();
                } else {
                    if(richLinkListener != null) {
                        richLinkListener.onClicked(view, meta);
                    } else {
                        richLinkClicked();
                    }
                }
            }
        });

    }

    private void richLinkClicked() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(main_url));
        context.startActivity(intent);
    }

    protected LinearLayout findLinearLayoutChild() {
        if (getChildCount() > 0 && getChildAt(0) instanceof LinearLayout) {
            return (LinearLayout) getChildAt(0);
        }
        return null;
    }

    public void setLinkFromMeta(MetaData metaData) {
        meta = metaData;
        initView();
    }

    public MetaData getMetaData() {
        return meta;
    }


    public void setDefaultClickListener(boolean isDefault) {
        isDefaultClick = isDefault;
    }

    public void setClickListener(RichLinkListener richLinkListener1) {
        richLinkListener = richLinkListener1;
    }

    public void setLink(String url, final ViewListener viewListener) {
        main_url = url;
        RichPreview richPreview = new RichPreview(new ResponseListener() {
            @Override
            public void onData(MetaData metaData) {
                meta = metaData;

                if(meta.getTitle().isEmpty() || meta.getTitle().equals("")) {
                    viewListener.onSuccess(true);
                }

                initView();
            }

            @Override
            public void onError(Exception e) {
                viewListener.onError(e);
            }
        });
        richPreview.getPreview(url);
    }

    private static void removeUnderlines(Spannable p_Text) {
        URLSpan[] spans = p_Text.getSpans(0, p_Text.length(), URLSpan.class);

        for(URLSpan span:spans) {
            int start = p_Text.getSpanStart(span);
            int end = p_Text.getSpanEnd(span);
            p_Text.removeSpan(span);
            span = new URLSpanNoUnderline(span.getURL());
            p_Text.setSpan(span, start, end, 0);
        }
    }

}
