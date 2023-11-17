package hu.bankblaze.bankblaze.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.security.Permission;

@Service
public interface PermissionRepository extends JpaRepository<Permission,Long> {


}