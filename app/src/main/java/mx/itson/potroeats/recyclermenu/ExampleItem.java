package mx.itson.potroeats.recyclermenu;

public class ExampleItem {
    private String mImageResource;
    private String mText;

    public ExampleItem(String imageResource, String text) {
        this.mImageResource = imageResource;
        this.mText = text;
    }



    public String getImageResource() {
        return mImageResource;
    }

    public String getText() {
        return mText;
    }
}
