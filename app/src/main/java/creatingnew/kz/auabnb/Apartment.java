package creatingnew.kz.auabnb;

/**
 * Created by Алишер on 14.06.2016.
 */
public class Apartment  {

    private String obgectId;
    private String Title;
    private int Price;
    private String Image;
    private int gradus;
    private String type;
    private boolean check;

    public boolean isCheck() {
        return check;
    }

    public String getObjectId() {
        return obgectId;
    }

    public void setObgectId(String obgectId) {
        this.obgectId = obgectId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
    public void setGradus(int gradus){ this.gradus=gradus;}
    public int getGradus(){return gradus;}
    public void setWeather(String type){
        this.type=type;
    }
    public String getType(){return type;}

    public boolean getCheck(){ return check;}
}
