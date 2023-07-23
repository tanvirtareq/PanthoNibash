package net.therap.model;

/**
 * @author tanvirtareq
 * @since 7/16/23
 */
public class SessionContext {
    private Object user;

    private String email;

    private String role;

    private Long id;

    private String name;

    private String profileLink;

    private String profilePictureBase64Image;

    public SessionContext() {
    }

    public SessionContext(Object user, String email, String role, Long id, String name,
                          String profileLink, String profilePictureBase64Image) {
        this.user = user;
        this.email = email;
        this.role = role;
        this.id = id;
        this.name = name;
        this.profileLink = profileLink;
        this.profilePictureBase64Image = profilePictureBase64Image;
    }

    public String getProfilePictureBase64Image() {
        return profilePictureBase64Image;
    }

    public void setProfilePictureBase64Image(String profilePictureBase64Image) {
        this.profilePictureBase64Image = profilePictureBase64Image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileLink() {
        return profileLink;
    }

    public void setProfileLink(String profileLink) {
        this.profileLink = profileLink;
    }

    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}