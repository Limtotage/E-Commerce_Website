package com.example.springioc.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.springioc.entity.Role;
import com.example.springioc.security.RoleRepo;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initRoles(RoleRepo roleRepo) {
        return args -> {
            createRoleIfNotExists("ROLE_ADMIN", roleRepo);
            createRoleIfNotExists("ROLE_SELLER", roleRepo);
            createRoleIfNotExists("ROLE_CUSTOMER", roleRepo);
        };
    }

    private void createRoleIfNotExists(String roleName, RoleRepo roleRepo) {
        if (roleRepo.findByName(roleName).isEmpty()) {
            Role role = new Role();
            role.setName(roleName);
            roleRepo.save(role);

            System.out.println("Role oluşturuldu: " + roleName);
        }
    }
}