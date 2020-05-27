package project.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.user.User;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface ServiceRepository extends JpaRepository<Service, Long> {

    List<Service> findAll();

    Service getServiceByName(String name);

    Optional<Service> getServiceById(Long id);

    void deleteAll();
    void deleteByName(String name);

}