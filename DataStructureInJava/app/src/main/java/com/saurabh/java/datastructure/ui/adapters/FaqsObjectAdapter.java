package com.saurabh.java.datastructure.ui.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.saurabh.java.datastructure.R;
import com.saurabh.java.datastructure.db.tables.FAQ;

import java.util.List;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

//TODO: find the cause of FAQListAdapter submitlist not refresh screen.
public class FaqsObjectAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    // A menu item view type.
    private static final int FAQ_ITEM_VIEW_TYPE = 0;
    // The Native Express ad view type.
    private static final int NATIVE_EXPRESS_AD_VIEW_TYPE = 1;
    // The list of Native Express ads and menu items.
    private List<Object> mRecyclerViewItems;
    private Activity mContext;

    public FaqsObjectAdapter(Activity context) {
        this.mContext = context;
    }

    public void submitList(List<Object> recyclerViewItems) {
        this.mRecyclerViewItems = recyclerViewItems;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mRecyclerViewItems == null ? 0 : mRecyclerViewItems.size();
    }

    /**
     * Determines the view type for the given position.
     */
    @Override
    public int getItemViewType(int position) {
//        return (0 != position && (position % FaqsListFragment.ITEMS_PER_AD == 0)) ? NATIVE_EXPRESS_AD_VIEW_TYPE
//                : FAQ_ITEM_VIEW_TYPE;
        return FAQ_ITEM_VIEW_TYPE;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        switch (viewType) {
            case FAQ_ITEM_VIEW_TYPE:
                View faqItemLayoutView = LayoutInflater.from(
                        viewGroup.getContext()).inflate(R.layout.adapter_row_item_faqs,
                        viewGroup, false);
                return new FaqItemViewHolder(faqItemLayoutView);
//            case NATIVE_EXPRESS_AD_VIEW_TYPE:
//                // fall through
//            default:
//                View nativeExpressLayoutView = LayoutInflater.from(
//                        viewGroup.getContext()).inflate(R.layout.row_item_native_express_ad_container,
//                        viewGroup, false);
//                return new NativeExpressAdViewHolder(nativeExpressLayoutView);

        }
        throw new IllegalArgumentException("This view type is not supported..");
    }

    /**
     * Replaces the content in the views that make up the menu item view and the
     * Native Express ad view. This method is invoked by the layout manager.
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case FAQ_ITEM_VIEW_TYPE:
                FaqItemViewHolder menuItemHolder = (FaqItemViewHolder) holder;
                FAQ faqItem = (FAQ) mRecyclerViewItems.get(position);
                // Add the Faq item details to the Faq item view.
                menuItemHolder.tvFaqsId.setText(String.valueOf(faqItem.get_id()));
                menuItemHolder.tvFaqsQuestion.setText(faqItem.getQuestion());
                menuItemHolder.tvFaqsAnswer.setText(faqItem.getAnswer());
                menuItemHolder.llAnswer.setVisibility(View.GONE);
                menuItemHolder.ivMoreOrLess.setImageResource(R.drawable.ic_down_arrow);
                break;
//
//            case NATIVE_EXPRESS_AD_VIEW_TYPE:
//                // fall through
//            default:
//                NativeExpressAdViewHolder nativeExpressHolder =
//                        (NativeExpressAdViewHolder) holder;
//                NativeExpressAdView adView =
//                        (NativeExpressAdView) mRecyclerViewItems.get(position);
//                ViewGroup adCardView = (ViewGroup) nativeExpressHolder.itemView;
//                if (adCardView.getChildCount() > 0) {
//                    adCardView.removeAllViews();
//                }
//
//                // Add the Native Express ad to the native express ad view.
//                adCardView.addView(adView);
        }
    }

//    private void launchQuestionCardActivity(int position) {
//        Intent quesCardIntent = new Intent(mContext, QuestionCardActivity.class);
//        quesCardIntent.putExtra(QuestionCardActivity.KEY_QUESTION_POSITION, position);
//        //quesCardIntent.putParcelableArrayListExtra(QuestionCardActivity.KEY_QUESTION_PARCEL, mRecyclerViewItems);
//        mContext.startActivity(quesCardIntent);
//    }

    public class FaqItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CardView mRootView;
        private AppCompatTextView tvFaqsId;
        private AppCompatTextView tvFaqsQuestion;
        private ImageView ivMoreOrLess;
        private LinearLayout llAnswer;
        private AppCompatTextView tvFaqsAnswer;


        FaqItemViewHolder(View itemView) {
            super(itemView);

            mRootView = itemView.findViewById(R.id.faq_card_view);
            tvFaqsId = itemView.findViewById(R.id.tv_faqs_id);
            tvFaqsQuestion = itemView.findViewById(R.id.tv_faqs_question);
            ivMoreOrLess = itemView.findViewById(R.id.iv_more_or_less);
            llAnswer = itemView.findViewById(R.id.ll_answer);
            tvFaqsAnswer = itemView.findViewById(R.id.tv_faqs_answer);

            mRootView.setOnClickListener(this);
            ivMoreOrLess.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {

            int position = this.getLayoutPosition();
            if (v.getId() == R.id.iv_more_or_less) {
                if (View.VISIBLE == llAnswer.getVisibility()) {
                    llAnswer.setVisibility(View.GONE);
                    ivMoreOrLess.setImageResource(R.drawable.ic_down_arrow);
                } else {
                    llAnswer.setVisibility(View.VISIBLE);
                    ivMoreOrLess.setImageResource(R.drawable.ic_up_arrow);
                }
            } else if (v.getId() == R.id.faq_card_view) {
//                launchQuestionCardActivity(position);
            }
        }
    }

}
