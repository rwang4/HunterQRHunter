package com.example.hunterqrhunter;

import com.example.hunterqrhunter.model.User;

// this is an interface for the callback function
// FYI read doc: https://stackoverflow.com/questions/50416544/how-to-access-result-of-void-oncomplete-of-a-task or search callback function/ asynchronization for Java
public interface userObjectReceivedListener {
    void userObjectReceived(User result);

}
