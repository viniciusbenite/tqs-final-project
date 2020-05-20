package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ServiceServiceImp implements ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public project.service.Service getServiceByName(String name) {
        return serviceRepository.getServiceByName(name);
    }

    @Override
    public Optional<project.service.Service> getServiceById(Long id) {
        return serviceRepository.getServiceById(id);
    }

    @Override
    public List<project.service.Service> getAllService() {
        return serviceRepository.findAll();
    }

    @Override
    public void save(project.service.Service service) {
        serviceRepository.save(service);
    }

    @Override
    public void deleteAll() {
        serviceRepository.deleteAll();
    }

    @Override
    public void deleteServiceByName(String name) {
        serviceRepository.deleteByName(name);
    }
}
