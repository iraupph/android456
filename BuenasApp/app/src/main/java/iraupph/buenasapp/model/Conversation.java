package iraupph.buenasapp.model;

import java.util.List;

public class Conversation {

    public long mId;
    public String mTitle;
    public int mImage;
    public List<Message> mMessages;

    public Conversation(long id, String title, int image, List<Message> messages) {
        this.mId = id;
        this.mTitle = title;
        this.mImage = image;
        this.mMessages = messages;
    }
}
