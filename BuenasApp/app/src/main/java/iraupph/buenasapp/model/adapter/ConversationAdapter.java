package iraupph.buenasapp.model.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import iraupph.buenasapp.R;
import iraupph.buenasapp.model.Message;

public class ConversationAdapter extends ArrayAdapter<Message> {

    // Adicionando uma regra de layout com esse id ele na verdade remove a regra
    private static final int REMOVE_RULE = 0;

    private final LayoutInflater mInflater;
    private List<Message> mMessages;

    public ConversationAdapter(Context context, int resource, List<Message> messages) {
        super(context, resource, messages);
        this.mMessages = messages;
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MessageViewHolder holder;
        if (convertView != null) {
            holder = (MessageViewHolder) convertView.getTag();
        } else {
            convertView = mInflater.inflate(R.layout.view_message, parent, false);
            holder = new MessageViewHolder(convertView);
            convertView.setTag(holder);
        }

        Message message = getItem(position);

        holder.mText.setText(message.mText);

        // Mexe na posição da mensagem e no fundo dependendo de quem é a mensagem
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) holder.mText.getLayoutParams();
        if (message.mIsMine) {
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, REMOVE_RULE);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            holder.mText.setBackgroundResource(R.color.gaucho_yellow_light);
        } else {
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, REMOVE_RULE);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            holder.mText.setBackgroundResource(R.color.gaucho_green_light);
        }
        holder.mText.setLayoutParams(layoutParams);

        return convertView;
    }

    static class MessageViewHolder {

        public TextView mText;

        public MessageViewHolder(View view) {
            mText = (TextView) view.findViewById(R.id.message_text);
        }
    }
}
