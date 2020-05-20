package project.saloon;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import project.user.User;
import project.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static project.constants.Paths.SALOON;

@RestController
@RequestMapping(value = SALOON)
@Api(value = "Métodos para gerenciar salões")
public class SaloonController {

    @Autowired
    private SaloonService saloonService;
    @Autowired
    private SaloonRepository saloonRepository;

    @ApiOperation("Get all saloons")
    @GetMapping
    public List<Saloon> all() {
        return saloonService.getAllSaloon();
    }

    @ApiOperation("Create a new saloon")
    @PostMapping("/")
    public Saloon newSaloon(@RequestBody Saloon newSaloon) {
        return saloonRepository.save(newSaloon);
    }

    @ApiOperation("Get a single saloon")
    @GetMapping("/{id}")
    public Saloon getSaloon(@PathVariable Long id) {
        return saloonRepository.getSaloonById(id)
                .orElseThrow(() -> new SaloonNotFoundException(id));
    }

    @ApiOperation("Edit a saloon")
    @PutMapping("/{id}")
    public Saloon editSaloon(@RequestBody Saloon newSaloon,
                             @PathVariable Long id) {
        return saloonRepository.findById(id)
                .map(saloon -> {
                    saloon.setOwner(newSaloon.getOwner());
                    saloon.setServices(newSaloon.getServices());
                    saloon.setSchedule(newSaloon.getSchedule());
                    saloon.setName(newSaloon.getName());
                    saloon.setPostalCode(newSaloon.getPostalCode());
                    saloon.setCity(newSaloon.getCity());
                    saloon.setCountry(newSaloon.getCountry());
                    saloon.setStatus(newSaloon.getStatus());
                    saloon.setType(newSaloon.getType());
                    saloon.setContact(newSaloon.getContact());

                    return saloonRepository.save(saloon);
                })
                .orElseGet(() -> {
                    newSaloon.setId(id);
                    return saloonRepository.save(newSaloon);
                });
    }

    @ApiOperation("Delete a saloon")
    @DeleteMapping("/{id}")
    public void deleteSaloon(@PathVariable Long id) {
        saloonRepository.deleteById(id);
    }

    @ApiOperation("Delete all saloons")
    @DeleteMapping("/")
    public void purgeSaloon() {
        saloonRepository.deleteAll();
    }
}
