package edu.miu.cs545.waa_project.service;

import edu.miu.cs545.waa_project.domain.User;
import edu.miu.cs545.waa_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getAll() { return (List<User>) userRepository.findAll(); }

    @Override
    public User save(User user) { return userRepository.save(user); }

    @Override
    public User find(Long id) { return userRepository.findById(id).get(); }

}
