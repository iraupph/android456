package iraupph.buenasapp.model;

public class Chat {

    public long mId;
    public int mImage;
    public String mTitle;
    public String mTimestamp;
    public String mMessage;
    public int mCount;

    public Chat(long id, int image, String title, String timestamp, String message, int count) {
        this.mId = id;
        this.mImage = image;
        this.mTitle = title;
        this.mTimestamp = timestamp;
        this.mMessage = message;
        this.mCount = count;
    }
}
