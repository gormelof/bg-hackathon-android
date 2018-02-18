package hackathon.baggage.models;

public class Pack {
    private String from;
    private String to;
    private String weight;
    private String user;

    public String getFrom() {
        return from;
    }

    public Pack(String from, String to, String weight, String user) {
        this.from = from;
        this.to = to;
        this.weight = weight;
        this.user = user;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {

        this.weight = weight;
    }

    public String getUserId() {
        return user;
    }

    public void setUserId(String userId) {
        this.user = userId;
    }
}
