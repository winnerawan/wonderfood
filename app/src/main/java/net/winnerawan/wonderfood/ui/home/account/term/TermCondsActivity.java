package net.winnerawan.wonderfood.ui.home.account.term;

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
public class TermCondsActivity extends BaseActivity implements TermCondsView {

    @Inject
    TermCondsMvpPresenter<TermCondsView> mPresenter;

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

        amb.append(getString(R.string.term_condition), false, new RelativeSizeSpan(2f), new StyleSpan(Typeface.BOLD),
                new LeftSpan());
        amb.append("<font color=0xFFC801>@Winnerawan T</font><font color=0x888888> Oct. 28, 2017</font>",
                true, new RelativeSizeSpan(0.8f), new StyleSpan(Typeface.BOLD));
        amb.append(
                "This application (wonderfood) is a copyrighted work belonging to winnerawan t.",
                true, new RelativeSizeSpan(1.0f), new JustifiedSpan(), new QuoteSpan(0xFFFFC801),
                new StyleSpan(Typeface.ITALIC), new ForegroundColorSpan(0xFF555555));
        amb.append(
                "The design or picture used in this application belongs to a third party to be mentioned on the <font color=0xFFC801>About Application</font>",
                true, new RelativeSizeSpan(1.0f), new JustifiedSpan());


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
