package iraupph.buenasapp.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

import iraupph.buenasapp.R;
import iraupph.buenasapp.model.Chat;
import iraupph.buenasapp.model.adapter.ChatAdapter;

public class ChatsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);

        ListView mChatList = (ListView) findViewById(R.id.chat_list);
        ChatAdapter mChatAdapter = new ChatAdapter(this, R.layout.view_chat, loadChats());
        mChatList.setAdapter(mChatAdapter);
    }

    private List<Chat> loadChats() {
        // Mock de chats
        return Arrays.asList(
                new Chat(R.drawable.mom, "Mamãe", "19:01", "Filho, o que queres pra janta?", 1),
                new Chat(R.drawable.johndoe, "John Doe", "18:45", "Boa sorte na palestra!", 0),
                new Chat(R.drawable.nerds, "Galera do Trampo", "15:19", "Ícaro, a app tá quebrando de novo!", 1),
                new Chat(R.drawable.girl, "Aquela Garota Chata", "13:17", "Ei, pq não me responde?", 3)
        );
    }
}
