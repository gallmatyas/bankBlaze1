package hu.bankblaze.bankblaze.service;

import hu.bankblaze.bankblaze.model.Permission;
import hu.bankblaze.bankblaze.repo.PermissionRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;



    public void savePermisson (Permission permission){
        permissionRepository.save(permission);
    }

    private Permission getPermissionById(Long id) {
        return permissionRepository.findById(id).orElse(null);
    }

    public void modifyForRetail(Long id, Boolean newForRetail) {
        Permission permission = getPermissionById(id);
        permission.setForRetail(newForRetail);
        permissionRepository.save(permission);
    }

    public void modifyForCorporate(Long id, Boolean newForCorporate) {
        Permission permission = getPermissionById(id);
        permission.setForCorporate(newForCorporate);
        permissionRepository.save(permission);
    }

    public void modifyForTellers(Long id, Boolean newForTeller) {
        Permission permission = getPermissionById(id);
        permission.setForTeller(newForTeller);
        permissionRepository.save(permission);
    }

    public void modifyForPremium(Long id, Boolean newForPremium) {
        Permission permission = getPermissionById(id);
        permission.setForPremium(newForPremium);
        permissionRepository.save(permission);
    }



}
