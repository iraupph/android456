package iraupph.buenasapp.ui.activity;

import android.app.ActionBar;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

import iraupph.buenasapp.R;
import iraupph.buenasapp.model.Conversation;
import iraupph.buenasapp.model.Message;
import iraupph.buenasapp.model.adapter.ConversationAdapter;

public class ConversationActivity extends AppCompatActivity {

    private EditText mMessage;
    private ConversationAdapter mConversationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);

        mMessage = (EditText) findViewById(R.id.conv_input);

        ListView messagesList = (ListView) findViewById(R.id.conv_messages);
        ImageButton send = (ImageButton) findViewById(R.id.conv_send);

        mConversationAdapter = new ConversationAdapter(this, R.layout.view_chat, new ArrayList<Message>());
        messagesList.setAdapter(mConversationAdapter);

        // Definimos uma funcionalidade pro clique no "enviar"
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = mMessage.getText().toString();
                if (!TextUtils.isEmpty(message)) {
                    // Se tem texto, envia e limpa o input
                    mConversationAdapter.add(new Message(message, true));
                    mMessage.setText("");
                }
            }
        });

        // Buscamos os dados passados da outra tela pra esta e rodamos a tarefa de "baixar" as mensagens
        int conversationId = getIntent().getIntExtra(ChatsActivity.CONVERSATION_EXTRA, -1);
        new ConversationTask().execute(conversationId);
    }

    // Parâmetros da tarefa são: classe da entrada, do item de progresso e do retorno
    class ConversationTask extends AsyncTask<Integer, Void, Conversation> {

        @Override
        protected Conversation doInBackground(Integer... params) {
            // Rodando em uma thread paralela
            int id = params[0]; // Entrada é um vetor, queremos só o primeiro item
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
            // Esta função executa no final da tarefa e na thread principal
            ActionBar actionBar = getActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true); // Mostra o botão de navegação UP
                actionBar.setIcon(conversation.mImage); // Ícone do topo
                actionBar.setTitle(conversation.mTitle); // Texto do topo
            }
            // Recebemos os items, adiciona no adapter e ele é atualizado
            mConversationAdapter.addAll(conversation.mMessages);
        }
    }
}
