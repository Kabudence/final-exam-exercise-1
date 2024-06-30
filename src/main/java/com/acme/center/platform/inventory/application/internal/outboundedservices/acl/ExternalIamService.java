package com.acme.center.platform.inventory.application.internal.outboundedservices.acl;

import com.acme.center.platform.iam.domain.model.entities.Role;
import com.acme.center.platform.iam.interfaces.acl.IamContextFacade;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ExternalIamService {

    private final IamContextFacade iamContextFacade;

    public ExternalIamService(IamContextFacade iamContextFacade) {
        this.iamContextFacade = iamContextFacade;
    }

    public Set<Role> fetchRolesByUserId(Long userId) {
        var roles = iamContextFacade.fetchRolesByUserId(userId);
        if(roles.isEmpty()) return Set.of();
        return roles;
    }

}
