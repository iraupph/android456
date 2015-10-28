package iraupph.buenasapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

import iraupph.buenasapp.R;
import iraupph.buenasapp.model.Contact;
import iraupph.buenasapp.model.adapter.ContactAdapter;

public class ContactsActivity extends AppCompatActivity {

    public static final String DETAIL_EXTRA = "DETAIL_EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        Toolbar toolbar = (Toolbar) findViewById(R.id.contacts_toolbar);
        setSupportActionBar(toolbar);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        final ListView contactList = (ListView) findViewById(R.id.contact_list);
        final ContactAdapter contactAdapter = new ContactAdapter(this, R.layout.view_contact, loadContacts());
        contactList.setAdapter(contactAdapter);

        contactList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent conversationIntent = new Intent(getApplicationContext(), DetailActivity.class);
                conversationIntent.putExtra(DETAIL_EXTRA, contactAdapter.getItem(position).mId);
                startActivity(conversationIntent);
            }
        });
    }

    private List<Contact> loadContacts() {
        return Arrays.asList(
                new Contact(4, R.drawable.girl, "Aquela Garota Chata"),
                new Contact(2, R.drawable.johndoe, "John Doe"),
                new Contact(1, R.drawable.mom, "Mamãe")
        );
    }
}
