package iraupph.buenasapp.model.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import iraupph.buenasapp.R;
import iraupph.buenasapp.model.Chat;

public class ChatAdapter extends ArrayAdapter<Chat> {

    private final Context mContext;
    private final LayoutInflater mInflater;

    public ChatAdapter(Context context, int resource, List<Chat> objects) {
        super(context, resource, objects);
        mContext = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Pattern de view holder pra otimização de recursos
        ChatViewHolder holder;
        if (convertView != null) {
            holder = (ChatViewHolder) convertView.getTag();
        } else {
            convertView = mInflater.inflate(R.layout.view_chat, parent, false);
            holder = new ChatViewHolder(convertView);
            convertView.setTag(holder);
        }

        // Pega o item da posição...
        Chat chat = getItem(position);
        // E monta a linha com os atributos dele
        holder.mImage.setImageResource(chat.mImage);
        holder.mTitle.setText(chat.mTitle);
        holder.mTimestamp.setText(chat.mTimestamp);
        holder.mMessage.setText(chat.mMessage);
        holder.mCount.setText(String.valueOf(chat.mCount));

        // Trocar a cor de chats "lidos"
        if (chat.mCount == 0) {
            holder.mMessage.setTextColor(mContext.getResources().getColor(android.R.color.darker_gray));
            holder.mCount.setVisibility(View.GONE);
        }

        return convertView;
    }

    static class ChatViewHolder {

        public ImageView mImage;
        public TextView mTitle;
        public TextView mTimestamp;
        public TextView mMessage;
        public TextView mCount;

        public ChatViewHolder(View view) {
            mImage = (ImageView) view.findViewById(R.id.chat_image);
            mTitle = (TextView) view.findViewById(R.id.chat_title);
            mTimestamp = (TextView) view.findViewById(R.id.chat_timestamp);
            mMessage = (TextView) view.findViewById(R.id.chat_message);
            mCount = (TextView) view.findViewById(R.id.chat_count);
        }
    }
}
