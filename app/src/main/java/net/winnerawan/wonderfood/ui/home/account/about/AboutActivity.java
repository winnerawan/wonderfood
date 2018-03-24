package net.winnerawan.wonderfood.ui.home.account.about;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.TypedValue;
import android.widget.LinearLayout;

import com.bluejamesbond.text.DocumentView;
import com.bluejamesbond.text.style.JustifiedSpan;
import com.bluejamesbond.text.style.LeftSpan;
import com.bluejamesbond.text.style.TextAlignment;
import com.bluejamesbond.text.util.ArticleBuilder;

import net.winnerawan.wonderfood.R;
import net.winnerawan.wonderfood.ui.base.BaseActivity;
import net.winnerawan.wonderfood.ui.helper.QuoteSpan;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, September 2017
 */
public class AboutActivity extends BaseActivity implements AboutView {

    @Inject
    AboutMvpPresenter<AboutView> mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacypolicy);
        getActivityComponent().inject(this);
        overridePendingTransition(R.anim.anim_pop_left, R.anim.anim_push_left);
        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(this);
        setUp();
    }

    @Override
    protected void setUp() {
        ArticleBuilder amb = new ArticleBuilder();

        amb.append(getString(R.string.about_apps), false, new RelativeSizeSpan(2f), new StyleSpan(Typeface.BOLD),
                new LeftSpan());
        amb.append("<font color=0xFFC801>@Winnerawan T</font><font color=0x888888> Oct. 28, 2017</font>",
                true, new RelativeSizeSpan(0.8f), new StyleSpan(Typeface.BOLD));
        amb.append(
                "This application (wonderfood) is a copyrighted work belonging to winnerawan t, and other resource : picture, design, " +
                        "assets may be belongs to third party." +
                        " Many thanks to <font color=0xFFC801>@amitshekhariitbhu</font> that his mvp architecture, library i " +
                        "use to create this application",
                true, new RelativeSizeSpan(1.0f), new JustifiedSpan(), new QuoteSpan(0xFFFFC801),
                new StyleSpan(Typeface.ITALIC), new ForegroundColorSpan(0xFF555555));
        amb.append("<font color=0xFFC801>Third Party Library</font>\n",  true, new RelativeSizeSpan(1.0f), new JustifiedSpan());
        amb.append("1. <font color=0xFFC801>RxJava2</font> https://github.com/ReactiveX/RxJava\n", true, new RelativeSizeSpan(1.0f), new JustifiedSpan());
        amb.append("2. <font color=0xFFC801>Dagger2</font> https://google.github.io/dagger/\n", true, new RelativeSizeSpan(1.0f), new JustifiedSpan());
        amb.append("3. <font color=0xFFC801>AndroidDebugDatabase</font> https://github.com/amitshekhariitbhu/Android-Debug-Database\n", true, new RelativeSizeSpan(1.0f), new JustifiedSpan());
        amb.append("4. <font color=0xFFC801>Calligraphy</font> https://github.com/chrisjenx/Calligraphy\n", true, new RelativeSizeSpan(1.0f), new JustifiedSpan());
        amb.append("5. <font color=0xFFC801>GreenDao</font> http://greenrobot.org/greendao/\n", true, new RelativeSizeSpan(1.0f), new JustifiedSpan());
        amb.append("6. <font color=0xFFC801>ButterKnife</font> http://jakewharton.github.io/butterknife/\n", true, new RelativeSizeSpan(1.0f), new JustifiedSpan());
        amb.append("7. <font color=0xFFC801>Fast Android Networking</font> https://github.com/amitshekhariitbhu/Fast-Android-Networking\n", true, new RelativeSizeSpan(1.0f), new JustifiedSpan());
        amb.append("8. <font color=0xFFC801>Banner Slider</font> https://github.com/saeedsh92/Banner-Slider\n", true, new RelativeSizeSpan(1.0f), new JustifiedSpan());
        amb.append("9. <font color=0xFFC801>Circle Image View</font> https://github.com/hdodenhof/CircleImageView\n", true, new RelativeSizeSpan(1.0f), new JustifiedSpan());
        amb.append("10. <font color=0xFFC801>Floating Action Buttons</font> https://github.com/Clans/FloatingActionButton\n", true, new RelativeSizeSpan(1.0f), new JustifiedSpan());
        amb.append("11. <font color=0xFFC801>Text Justify</font> https://github.com/bluejamesbond/TextJustify-Android\n", true, new RelativeSizeSpan(1.0f), new JustifiedSpan());
        amb.append("12. <font color=0xFFC801>Better Pickers</font> https://github.com/code-troopers/android-betterpickers\n", true, new RelativeSizeSpan(1.0f), new JustifiedSpan());
        amb.append("13. <font color=0xFFC801>Lovely-dialog</font> https://github.com/yarolegovich/LovelyDialog\n", true, new RelativeSizeSpan(1.0f), new JustifiedSpan());
        amb.append("14. <font color=0xFFC801>Pretty Time</font> https://github.com/ocpsoft/prettytime\n", true, new RelativeSizeSpan(1.0f), new JustifiedSpan());
        amb.append("15. <font color=0xFFC801>And the other who may i forgot to mention it.</font>\n", true, new RelativeSizeSpan(1.0f), new JustifiedSpan());


        amb.append("<font color=0xFFC801>Resources</font>\n\n", true, new RelativeSizeSpan(1.0f), new JustifiedSpan());
        amb.append("1. <font color=0xFFC801>Freepik</font> http://www.freepik.com\n", true, new RelativeSizeSpan(1.0f), new JustifiedSpan());
        amb.append("2. <font color=0xFFC801>Android Material Icon</font> https://material.io/icons/\n", true, new RelativeSizeSpan(1.0f), new JustifiedSpan());
        amb.append("3. <font color=0xFFC801>And the other who may i forgot to mention it</font>\n", true, new RelativeSizeSpan(1.0f), new JustifiedSpan());


        addDocumentView(amb, DocumentView.FORMATTED_TEXT);

    }

    public DocumentView addDocumentView(CharSequence article, int type, boolean rtl) {
        final DocumentView documentView = new DocumentView(this, type);
        documentView.getDocumentLayoutParams().setTextColor(0xffffffff);
        documentView.getDocumentLayoutParams().setTextTypeface(Typeface.DEFAULT);
        documentView.getDocumentLayoutParams().setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        documentView.getDocumentLayoutParams().setTextAlignment(TextAlignment.JUSTIFIED);
        documentView.getDocumentLayoutParams().setInsetPaddingLeft(30f);
        documentView.getDocumentLayoutParams().setInsetPaddingRight(30f);
        documentView.getDocumentLayoutParams().setInsetPaddingTop(30f);
        documentView.getDocumentLayoutParams().setInsetPaddingBottom(30f);
        documentView.getDocumentLayoutParams().setLineHeightMultiplier(1f);
        documentView.getDocumentLayoutParams().setReverse(rtl);
        documentView.setText(article);
        documentView.setFadeInDuration(800);
        documentView.setFadeInAnimationStepDelay(30);
        documentView.setFadeInTween((t, b, c, d) -> c * (t /= d) * t * t + b);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT));
        linearLayout.addView(documentView);

        LinearLayout articleList = (LinearLayout) findViewById(R.id.articleList);
        articleList.addView(linearLayout);


        return documentView;
    }

    public DocumentView addDocumentView(CharSequence article, int type) {
        return addDocumentView(article, type, false);

    }

    private void finishAction() {
        finish();
        overridePendingTransition(R.anim.anim_pop_right, R.anim.anim_push_right);
    }

    public void onBackPressed() {
        finishAction();
    }

}
