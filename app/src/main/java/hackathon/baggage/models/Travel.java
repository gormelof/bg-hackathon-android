package hackathon.baggage.models;

public class Travel {
    private String from;
    private String to;
    private String weight;
    private String date;

    public Travel(String from, String to, String weight, String date) {
        this.from = from;
        this.to = to;
        this.weight = weight;
        this.date = date;
    }

    public String getFrom() {
        return from;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
