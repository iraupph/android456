package iraupph.buenasapp.ui.activity;

import android.Manifest;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import iraupph.buenasapp.R;
import iraupph.buenasapp.model.Contact;
import iraupph.buenasapp.model.adapter.ContactAdapter;

public class ContactsActivity extends AppCompatActivity {

    public static final String DETAIL_EXTRA = "DETAIL_EXTRA";
    private static final int CONTACT_TRANSITION = 10;
    private static final int CONTACTS_PERMISSION = 100;
    private static final int APP_SETTINGS = 666;

    private ContactAdapter mContactAdapter;
    private ListView mContactList;
    private FrameLayout mContactsPermisionView;

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

        mContactList = (ListView) findViewById(R.id.contact_list);
        mContactAdapter = new ContactAdapter(this, R.layout.view_contact, new ArrayList<Contact>());
        mContactList.setAdapter(mContactAdapter);

        mContactList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent conversationIntent = new Intent(getApplicationContext(), DetailActivity.class);
                conversationIntent.putExtra(DETAIL_EXTRA, mContactAdapter.getItem(position).mId);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    // Pra fazer a transição de elementos compartilhados, passa a atividade de destino,
                    // a view que é compartilhada e a string que identifica a transição
                    startActivityForResult(conversationIntent, CONTACT_TRANSITION,
                            ActivityOptions.makeSceneTransitionAnimation(ContactsActivity.this, view.findViewById(R.id.contact_image), getString(R.string.transition_image)).toBundle());
                } else {
                    startActivity(conversationIntent);
                }
            }
        });

        mContactsPermisionView = (FrameLayout) findViewById(R.id.contacts_permission_information);
        Button contactsPermissionButton = (Button) findViewById(R.id.contacts_permission_enable);
        contactsPermissionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Isso abre as configurações do sistema da nossa app, pra permitir as mudanças nas permissões
                Intent settingsIntent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + getPackageName()));
                startActivity(settingsIntent);
            }
        });

        if (!hasPermissions(Manifest.permission.READ_CONTACTS)) {
            // O sistema vai pedir a liberação da permissão
            ActivityCompat.requestPermissions(ContactsActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, CONTACTS_PERMISSION);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Valida de novo aqui para o caso de trocar as permissões com a app aberta (multi-task)
        if (hasPermissions(Manifest.permission.READ_CONTACTS)) {
            onContactsAllowed();
        } else {
            onContactsProhibited();
        }
    }

    private void onContactsAllowed() {
        // Carrega e mostra a lista de contatos
        mContactList.setVisibility(View.VISIBLE);
        mContactsPermisionView.setVisibility(View.GONE);
        mContactAdapter.clear();
        mContactAdapter.addAll(loadContacts());
    }

    private void onContactsProhibited() {
        // Mostra o aviso da permissão
        mContactsPermisionView.setVisibility(View.VISIBLE);
        mContactList.setVisibility(View.GONE);
    }


    public boolean hasPermissions(String permission) {
        // Função genérica pra verificar permissões
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            return checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        // Chamada assíncrona do resultado da requisição do sistema pela liberação da permissão
        if (requestCode == CONTACTS_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                onContactsAllowed();
            } else {
                onContactsProhibited();
            }
        }
    }

    private List<Contact> loadContacts() {
        return Arrays.asList(
                new Contact(4, R.drawable.girl, "Aquela Garota Chata"),
                new Contact(2, R.drawable.johndoe, "John Doe"),
                new Contact(1, R.drawable.mom, "Mamãe")
        );
    }
}
