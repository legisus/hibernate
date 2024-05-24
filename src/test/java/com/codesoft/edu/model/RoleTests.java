package com.codesoft.edu.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RoleTests {

    @Test
    void constraintViolationOnEmptyRoleName() {
        Role emptyRole = new Role();
        emptyRole.setName("");

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Role>> violations = validator.validate(emptyRole);
        assertEquals(1, violations.size());
    }

    @Test
    void toStringTest(){
        Role role= new Role();
        role.setName("NEW_ROLE");
        String expected = "Role {name = NEW_ROLE}";
        assertEquals(expected, role.toString());
    }
}
