package project.saloon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.user.User;
import project.user.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SaloonServiceImp implements SaloonService {

    @Autowired
    private SaloonRepository saloonRepository;

    @Override
    public Saloon getSaloonByName(String name) {
        return saloonRepository.findByNameContainsIgnoreCase(name);
    }

    @Override
    public Optional<Saloon> getSaloonById(Long id) {
        return saloonRepository.findById(id);
    }

    @Override
    public List<Saloon> getAllSaloon() {
        return saloonRepository.findAll();
    }

    @Override
    public void save(Saloon saloon) {
        saloonRepository.save(saloon);
    }

    @Override
    public void deleteAll() {
        saloonRepository.deleteAll();
    }

    @Override
    public void deleteSaloonByName(String name) {
        saloonRepository.deleteByName(name);
    }

}
