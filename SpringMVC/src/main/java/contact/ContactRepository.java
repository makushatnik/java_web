package contact;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Ageev Evgeny on 25.01.2016.
 */
public interface ContactRepository //extends CrudRepository<Contact, Long> {
    extends PagingAndSortingRepository<Contact, Long> {
}
