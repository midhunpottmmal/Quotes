package myoracle.com.quotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

import myoracle.com.quotes.adapter.QuotesAdapter;
import myoracle.com.quotes.model.Quote;

/**
 * Created by Midhun on 19-04-2017.
 */

public class QuotesActivity extends AppCompatActivity {

    private Toolbar toolbar;
    ViewPager viewPager;
    QuotesAdapter quotesAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();

        ArrayList<Quote> quoteList = (ArrayList<Quote>) bundle.get("quotes");
        Integer index = (Integer) bundle.get("index");
        setContentView(R.layout.activity_quotes);
        Toast.makeText(this,"Long Click Copy",Toast.LENGTH_SHORT).show();
        toolbar = (Toolbar) findViewById(R.id.main_app_bar);
        setSupportActionBar(toolbar);
        this.viewPager = (ViewPager) findViewById(R.id.pager);
        this.quotesAdapter = new QuotesAdapter(this, quoteList,index);
        this.viewPager.setAdapter(quotesAdapter);

        AdView mAdView = (AdView) findViewById(R.id.adView3);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.quotes_cell_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_quote_share:

                TextView textView = (TextView) findViewById(R.id.quotesview);
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, textView.getText());
                sendIntent.setType("text/plain");
                Intent.createChooser(sendIntent, "Share via");
                startActivity(sendIntent);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }


}
