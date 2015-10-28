package iraupph.buenasapp.ui.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.util.HashMap;

import iraupph.buenasapp.R;
import iraupph.buenasapp.model.Contact;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.detail_toolbar_layout);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.detail_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Barra que aparece na parte inferior da tela que dá feedback sobre ação ou estado
                Snackbar.make(view, "Ação realizada no FAB!", Snackbar.LENGTH_LONG).show();
            }
        });

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        long contactId = getIntent().getLongExtra(ContactsActivity.DETAIL_EXTRA, -1);
        Contact contact = loadContact(contactId);
        // Personaliza a tela pelo contato selecionado
        ImageView image = (ImageView) findViewById(R.id.detail_image);
        image.setImageResource(contact.mImage);
        toolBarLayout.setTitle(contact.mTitle);
    }

    private Contact loadContact(long id) {
        HashMap<Long, Contact> contactMap = new HashMap<>();
        contactMap.put(4L, new Contact(4, R.drawable.girl, "Aquela Garota Chata"));
        contactMap.put(2L, new Contact(2, R.drawable.johndoe, "John Doe"));
        contactMap.put(1L, new Contact(1, R.drawable.mom, "Mamãe"));
        return contactMap.get(id);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int itemId = item.getItemId();
        if (itemId == android.R.id.home) {
            // Hack pra não perder a transição de volta
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
