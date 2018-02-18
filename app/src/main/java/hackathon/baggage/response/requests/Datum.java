
package hackathon.baggage.response.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("sent_to")
    @Expose
    private SentTo sentTo;
    @SerializedName("sent_from")
    @Expose
    private SentFrom sentFrom;
    @SerializedName("pack")
    @Expose
    private Pack pack;
    @SerializedName("travel")
    @Expose
    private Travel travel;
    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("created_at")
    @Expose
    private String createdAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SentTo getSentTo() {
        return sentTo;
    }

    public void setSentTo(SentTo sentTo) {
        this.sentTo = sentTo;
    }

    public SentFrom getSentFrom() {
        return sentFrom;
    }

    public void setSentFrom(SentFrom sentFrom) {
        this.sentFrom = sentFrom;
    }

    public Pack getPack() {
        return pack;
    }

    public void setPack(Pack pack) {
        this.pack = pack;
    }

    public Travel getTravel() {
        return travel;
    }

    public void setTravel(Travel travel) {
        this.travel = travel;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}
