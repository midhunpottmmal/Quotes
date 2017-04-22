package myoracle.com.quotes;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import static myoracle.com.quotes.R.raw.quotes;

/**
 * Created by Midhun on 19-04-2017.
 */

public class QuotesAdapter extends PagerAdapter {

    private final Context context;
    private final ArrayList<Quote> quotes;
    LayoutInflater layoutInflater;


    public QuotesAdapter(Context context, ArrayList<Quote> quotesList) {
        this.context = context;
        this.quotes = quotesList;

    }

    @Override
    public int getCount() {

        return this.quotes.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView  = layoutInflater.inflate(R.layout.quotes_cell, collection, false);
        TextView textView = (TextView) itemView.findViewById(R.id.quotesview);
        TextView textViewAuthor = (TextView) itemView.findViewById(R.id.quotesAuthor);
        textView.setText(this.quotes.get(position).getQuote());
        textViewAuthor.setText(this.quotes.get(position).getAuthor());
        collection.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }
}
