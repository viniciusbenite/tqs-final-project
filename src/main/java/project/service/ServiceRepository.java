package project.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.user.User;

import java.util.List;

@Repository
@Transactional
public interface ServiceRepository extends JpaRepository<Service, Long> {

    List<Service> findAll();
    Service getServiceByName(String name);

    void deleteAll();
    void deleteByName(String name);

}