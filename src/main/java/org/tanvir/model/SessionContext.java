package org.tanvir.model;

/**
 * @author tanvirtareq
 * @since 7/16/23
 */
public class SessionContext {

    private String email;

    private Role role;

    private Long id;

    private String name;

    private String profileLink;

    private String profilePictureBase64Image;

    public SessionContext() {
    }

    public SessionContext(Customer customer) {

        this.email = customer.getEmail();
        this.role = Role.CUSTOMER;
        this.id = customer.getId();
        this.name = customer.getName();
        this.profileLink = "/customer/" + customer.getId();
        this.profilePictureBase64Image = customer.getProfilePicBase64Image();;
    }

    public SessionContext(Hotel hotel) {

        this.email = hotel.getEmail();
        this.role = Role.HOTEL;
        this.id = hotel.getId();
        this.name = hotel.getName();
        this.profileLink = "/hotel/" + hotel.getId();
        this.profilePictureBase64Image = hotel.getHotelImageBase64Image();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SessionContext{" +
                "email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", profileLink='" + profileLink + '\'' +
                '}';
    }
}