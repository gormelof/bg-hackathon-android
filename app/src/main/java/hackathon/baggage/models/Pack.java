package hackathon.baggage.models;

public class Pack {
    private String from;
    private String to;
    private String weight;

    public String getFrom() {
        return from;
    }

    public Pack(String from, String to, String weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
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
}
