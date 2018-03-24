package net.winnerawan.wonderfood.ui.home.account.privacypolicy;

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
public class PrivacyPolicyActivity extends BaseActivity implements PrivacyPolicyView {

    @Inject
    PrivacyPolicyMvpPresenter<PrivacyPolicyView> mPresenter;

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

        amb.append(getString(R.string.privacy_policy), false, new RelativeSizeSpan(2f), new StyleSpan(Typeface.BOLD),
                new LeftSpan());
        amb.append("<font color=0xFFC801>@Winnerawan T</font><font color=0x888888> Oct. 28, 2017</font>",
                true, new RelativeSizeSpan(0.8f), new StyleSpan(Typeface.BOLD));
        amb.append(
                "This privacy policy has been compiled to better serve those who are concerned with how their 'Personally Identifiable Information' (PII) is being used online. PII, as described in US privacy law and information security, is information that can be used on its own or with other information to identify, contact, or locate a single person, or to identify an individual in context. Please read our privacy policy carefully to get a clear understanding of how we collect, use, protect or otherwise handle your Personally Identifiable Information in accordance with our website.\n",
                true, new RelativeSizeSpan(1.0f), new JustifiedSpan(), new QuoteSpan(0xFFFFC801),
                new StyleSpan(Typeface.ITALIC), new ForegroundColorSpan(0xFF555555));
        amb.append(
                "<font " +
                        "color=0xFFC801>What personal information do we collect from the people that visit our blog, website or app?</font>\n" +
                        "\n" +
                        "When ordering or registering on our site, as appropriate, you may be asked to enter your name, email address or other details to help you with your experience.\n" +
                        "\n" +
                        "<font " +
                        "color=0xFFC801>When do we collect information?</font>\n" +
                        "\n" +
                        "We collect information from you when you register on our site, place an order or enter information on our site.\n" +
                        "\n" +
                        "<font " +
                        "color=0xFFC801>How do we use your information?</font>\n" +
                        "\n" +
                        "We may use the information we collect from you when you register, make a purchase, sign up for our newsletter, respond to a survey or marketing communication, surf the website, or use certain other site features in the following ways:\n" +
                        "\n" +
                        "• To personalize your experience and to allow us to deliver the type of content and product offerings in which you are most interested.",
                true, new RelativeSizeSpan(1.0f), new JustifiedSpan());
        amb.append(
                "<font " +
                        "color=0xFFC801>Third-party disclosure</font>\n" +
                        "\n" +
                        "We do not sell, trade, or otherwise transfer to outside parties your Personally Identifiable Information.\n" +
                        "\n" +
                        "<font " +
                        "color=0xFFC801>Third-party links</font>\n" +
                        "\n" +
                        "Occasionally, at our discretion, we may include or offer third-party products or services on our website. These third-party sites have separate and independent privacy policies. We therefore have no responsibility or liability for the content and activities of these linked sites. Nonetheless, we seek to protect the integrity of our site and welcome any feedback about these sites.\n",
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
