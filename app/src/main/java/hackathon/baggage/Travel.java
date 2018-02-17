package hackathon.baggage;


import java.util.ArrayList;

public class Travel {
    User user;
    String baggageCapacity;
    String source;
    String destination;
    String date;

    public Travel(User user, String baggageCapacity, String source, String destination, String date) {
        this.user = user;
        this.baggageCapacity = baggageCapacity;
        this.source = source;
        this.destination = destination;
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBaggageCapacity() {
        return baggageCapacity;
    }

    public void setBaggageCapacity(String baggageCapacity) {
        this.baggageCapacity = baggageCapacity;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Demo travel verileri
     * @return
     */
    public static ArrayList<Travel> getDemoData() {
        ArrayList<Travel> data = new ArrayList<Travel>();

        Travel travel1 = new Travel(
                new User("https://avatars3.githubusercontent.com/u/6938857?s=460&v=4", "Ömer Faruk Görmel"),
                "23", "Sakarya", "İzmir", "23 Şubat 2018"
        );
        Travel travel2 = new Travel(
                new User("https://avatars1.githubusercontent.com/u/6316032?s=460&v=4", "Furkan Başaran"),
                "34", "Istanbul", "London", "10 Mart 2018"
        );
        Travel travel3 = new Travel(
                new User("https://avatars1.githubusercontent.com/u/5949615?s=460&v=4", "Ömer Kantar"),
                "23", "Bursa", "Miami", "8 Haziran 2018"
        );

        data.add(travel1);
        data.add(travel2);
        data.add(travel3);

        return data;
    }


}
