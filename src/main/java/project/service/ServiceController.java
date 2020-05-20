package project.service;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static project.constants.Paths.SERVICE;

@RestController
@RequestMapping(value = SERVICE)
public class ServiceController {

    @Autowired
    private ServiceService serviceService;
    @Autowired
    private ServiceRepository serviceRepository;

    @ApiOperation("Get all services")
    @GetMapping
    public List<Service> all() {
        return serviceService.getAllService();
    }

    @ApiOperation("Create a new service")
    @PostMapping("/")
    public Service newService(@RequestBody Service newService) {
        return serviceRepository.save(newService);
    }

    @ApiOperation("Get a single service")
    @GetMapping("/{id}")
    public Service getService(@PathVariable Long id) {
        return serviceRepository.getServiceById(id)
                .orElseThrow(() -> new ServiceNotFoundException(id));
    }

    @ApiOperation("Edit a service")
    @PutMapping("/{id}")
    public Service editService(@RequestBody Service newService,
                               @PathVariable Long id) {
        return serviceRepository.findById(id)
                .map(service -> {
                    service.setName(newService.getName());
                    service.setId(newService.getId());
                    service.setPrice(newService.getPrice());
                    service.setDescription(newService.getDescription());
                    service.setAvailable(newService.getAvailable());
                    service.setSaloon(newService.getSaloon());
                    service.setSchedules(newService.getSchedules());
                    service.setReservation(newService.getReservation());
                    return serviceRepository.save(service);
                })
                .orElseGet(() -> {
                    newService.setId(id);
                    return serviceRepository.save(newService);
                });
    }

    @ApiOperation("Delete a service")
    @DeleteMapping("/{id}")
    public void deleteService(@PathVariable Long id) {
        serviceRepository.deleteById(id);
    }

    @ApiOperation("Delete all service")
    @DeleteMapping("/")
    public void purgeService() {
        serviceRepository.deleteAll();
    }
}
