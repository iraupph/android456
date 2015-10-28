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
import iraupph.buenasapp.model.Contact;

public class ContactAdapter extends ArrayAdapter<Contact> {

    private final LayoutInflater mInflater;

    public ContactAdapter(Context context, int resource, List<Contact> objects) {
        super(context, resource, objects);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ContactViewHolder holder;
        if (convertView != null) {
            holder = (ContactViewHolder) convertView.getTag();
        } else {
            convertView = mInflater.inflate(R.layout.view_contact, parent, false);
            holder = new ContactViewHolder(convertView);
            convertView.setTag(holder);
        }

        Contact contact = getItem(position);
        holder.mImage.setImageResource(contact.mImage);
        holder.mTitle.setText(contact.mTitle);

        return convertView;
    }

    static class ContactViewHolder {

        public ImageView mImage;
        public TextView mTitle;

        public ContactViewHolder(View view) {
            mImage = (ImageView) view.findViewById(R.id.contact_image);
            mTitle = (TextView) view.findViewById(R.id.contact_title);
        }
    }
}
