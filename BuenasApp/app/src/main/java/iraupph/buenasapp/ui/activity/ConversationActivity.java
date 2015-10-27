package iraupph.buenasapp.ui.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

import iraupph.buenasapp.R;
import iraupph.buenasapp.model.Conversation;
import iraupph.buenasapp.model.Message;
import iraupph.buenasapp.model.adapter.ConversationAdapter;

public class ConversationActivity extends Activity {


    private ListView mMessagesList;
    private ImageButton mSend;
    private EditText mMessage;
    private ConversationAdapter mConversationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);

        mMessagesList = (ListView) findViewById(R.id.conv_messages);
        mSend = (ImageButton) findViewById(R.id.conv_send);
        mMessage = (EditText) findViewById(R.id.conv_input);

        int conversationId = getIntent().getIntExtra(ChatsActivity.CONVERSATION_EXTRA, -1);

        mConversationAdapter = new ConversationAdapter(this, R.layout.view_chat, new ArrayList<Message>());
        mMessagesList.setAdapter(mConversationAdapter);

        new ConversationTask().execute(conversationId);
    }

    class ConversationTask extends AsyncTask<Integer, Void, Conversation> {

        @Override
        protected Conversation doInBackground(Integer... params) {
            try {
                // Delay fake no carregamento...
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int id = params[0];
            return new Conversation(id, "John Doe", R.drawable.johndoe, Arrays.asList(
                    new Message("Eaí, curtiu o Xis Moita?", true),
                    new Message("Tava bom pra cacete cara, ogrei!", false),
                    new Message("Te contei que era top ;-)", true),
                    new Message("Hoje é o grande dia então?", false),
                    new Message("Aham! Pela noite vou ir lá apresentar pra galera! Com o Igor, que vai falar de Arduino :-D", true),
                    new Message("Conseguir terminar tudo?", false),
                    new Message("Ficou como tu queria?", false),
                    new Message("Sim, consegui fazer tudo que imaginei!", true),
                    new Message("Espero que eles curtam!", true),
                    new Message("Legal, manda ver!", false),
                    new Message("Boa sorte na palestra!", false)
            ));
        }

        @Override
        protected void onPostExecute(Conversation conversation) {
            ActionBar actionBar = getActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true); // Mostra o botão de navegação UP
                actionBar.setIcon(conversation.mImage); // Ícone do topo
                actionBar.setTitle(conversation.mTitle); // Texto do topo
            }

            mConversationAdapter.addAll(conversation.mMessages);
        }
    }
}
