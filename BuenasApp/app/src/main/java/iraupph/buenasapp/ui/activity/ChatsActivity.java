package iraupph.buenasapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

import iraupph.buenasapp.R;
import iraupph.buenasapp.model.Chat;
import iraupph.buenasapp.model.adapter.ChatAdapter;

public class ChatsActivity extends AppCompatActivity {

    public static final String CONVERSATION_EXTRA = "CONVERSATION_EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);

        final ListView chatList = (ListView) findViewById(R.id.chat_list);
        final ChatAdapter chatAdapter = new ChatAdapter(this, R.layout.view_chat, loadChats());
        chatList.setAdapter(chatAdapter);

        // Ao clicar em um item da lista
        chatList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent conversationIntent = new Intent(getApplicationContext(), ConversationActivity.class);
                // Adicionando dados pra serem usados na outra activity
                conversationIntent.putExtra(CONVERSATION_EXTRA, chatAdapter.getItem(position).mId);
                startActivity(conversationIntent);
            }
        });
    }

    private List<Chat> loadChats() {
        // Mock de chats
        return Arrays.asList(
                new Chat(1, R.drawable.mom, "Mamãe", "19:01", "Filho, o que queres pra janta?", 1),
                new Chat(2, R.drawable.johndoe, "John Doe", "18:45", "Boa sorte na palestra!", 0),
                new Chat(3, R.drawable.nerds, "Galera do Trampo", "15:19", "Ícaro, a app tá quebrando de novo!", 1),
                new Chat(4, R.drawable.girl, "Aquela Garota Chata", "13:17", "Ei, pq não me responde?", 3)
        );
    }
}
