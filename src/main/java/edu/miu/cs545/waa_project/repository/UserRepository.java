package edu.miu.cs545.waa_project.repository;

import edu.miu.cs545.waa_project.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find by user info by email
    public User findByEmail(String email);
}
