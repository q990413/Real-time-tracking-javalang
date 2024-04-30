package com.cab302groupproject.model;

import java.util.ArrayList;

public class MockUserDAO implements IUserDAO {
    /**
     * A list of users to be used as a mock database.
     */
    public final ArrayList<User> users = new ArrayList<>();
    private int autoIncrementedId = 0;

    @Override
    public void addUser(User user) {
        user.setId(autoIncrementedId);
        autoIncrementedId++;
        users.add(user);
    }

    @Override
    public void updateUser(User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == user.getId()) {
                users.set(i, user);
                break;
            }
        }
    }

    @Override
    public void deleteUser(User user) {
        users.remove(user);
    }

    @Override
    public User getUser(String email) {
        for (User user : users) {
            if (user.getEmail() == email) {
                return user;
            }
        }
        return null;
    }
}
