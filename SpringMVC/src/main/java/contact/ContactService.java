package contact;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Ageev Evgeny on 25.01.2016.
 */
public interface ContactService {
    List<Contact> findAll();
    Contact findById(Long id);
    Contact save(Contact cont);
    Page<Contact> findAllByPage(Pageable page);
}
