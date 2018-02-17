package hackathon.baggage.models;

public class User {
    private String profileImageUrl;
    private String name;

    public User(String profileImageUrl, String name) {
        this.profileImageUrl = profileImageUrl;
        this.name = name;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
