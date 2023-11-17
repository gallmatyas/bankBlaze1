package hu.bankblaze.bankblaze.repo;

import hu.bankblaze.bankblaze.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;



@Service
public interface PermissionRepository extends JpaRepository<Permission,Long> {


}