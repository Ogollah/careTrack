package com.stephen.careTrack.security.jwt;

import com.stephen.careTrack.model.Location;
import com.stephen.careTrack.model.User;

/**
 * The `JwtResponseWithUserDetails` class represents a response containing a JSON Web Token (JWT) along with user details.
 * It encapsulates the JWT, the associated user, and the user's location.
 *
 * This class is used to provide authentication and authorization responses with user-specific information.
 *
 * @author Ogolla
 * @version 1.0
 * @see com.stephen.careTrack.model.User
 * @see com.stephen.careTrack.model.Location
 */
public class JwtResponseWithUserDetails {
    private String jwt;
    private User user;
    private Location location;

    public JwtResponseWithUserDetails(String jwt, User user, Location location) {
        this.jwt = jwt;
        this.user = user;
        this.location = location;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
