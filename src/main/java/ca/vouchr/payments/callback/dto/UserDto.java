package ca.vouchr.payments.callback.dto;

import java.util.ArrayList;
import java.util.Collection;

public class UserDto {


    private String firstName;
    private String lastName;
    private Collection<UserIdDto> userIds = new ArrayList<>();

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Collection<UserIdDto> getUserIds() {
        return userIds;
    }

    public void setUserIds(Collection<UserIdDto> userIds) {
        this.userIds = userIds;
    }

    public static class UserIdDto {
        private String type;
        private String id;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }


}
