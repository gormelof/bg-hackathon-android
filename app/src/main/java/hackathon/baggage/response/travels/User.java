
package hackathon.baggage.response.travels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("reputation")
    @Expose
    private Integer reputation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getReputation() {
        return reputation;
    }

    public void setReputation(Integer reputation) {
        this.reputation = reputation;
    }

}
