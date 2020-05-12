package edu.miu.cs545.waa_project.service;

import edu.miu.cs545.waa_project.domain.User;

import java.util.List;

public interface UserService {
    User findByEmail(String email);
}
