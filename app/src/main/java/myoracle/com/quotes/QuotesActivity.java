package myoracle.com.quotes;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

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
        setContentView(R.layout.activity_quotes);

        toolbar = (Toolbar) findViewById(R.id.main_app_bar);
        setSupportActionBar(toolbar);
        this.viewPager= (ViewPager) findViewById(R.id.pager);
        this.quotesAdapter=new QuotesAdapter(this,quoteList);
        this.viewPager.setAdapter(quotesAdapter);


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
                sendIntent.putExtra(Intent.EXTRA_TEXT,textView.getText());
                sendIntent.setType("text/plain");
                Intent.createChooser(sendIntent, "Share via");
                startActivity(sendIntent);
                return true;
            case R.id.action_quote_copy:

//                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
//                ClipData clip = ClipData.n
//                clipboard.setPrimaryClip(clip);

                return true;
            case R.id.action_privacy_policy:
                android.app.FragmentManager fragmentManager =getFragmentManager();
                PrivacyDialogFragment privacyDialogFragment = new PrivacyDialogFragment();
                privacyDialogFragment.show(fragmentManager, "Sample Fragment");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
