package project.saloon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.user.User;
import project.user.UserRepository;

import java.util.List;

@Service
@Transactional
public class SaloonServiceImp implements SaloonService {

    @Autowired
    private SaloonRepository saloonRepository;

    @Override
    public User getSaloonByName(String name) {
        return saloonRepository.findByNameContainsIgnoreCase(name);
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
