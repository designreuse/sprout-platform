package net.savantly.sprout.core.security.privilege;

import java.util.List;

import net.savantly.spring.fixture.AbstractBaseFixture;
import net.savantly.spring.fixture.Fixture;

public class PrivilegeFixture extends AbstractBaseFixture<Privilege, PrivilegeRepository>{
	
	public static final String GENERAL_READ = "GENERAL_READ";
	public static final String GENERAL_ADMIN = "GENERAL_ADMIN";
    
    PrivilegeRepository repository;

    public PrivilegeFixture(PrivilegeRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public void addEntities(List<Privilege> entityList) {
        if(!repository.findById(GENERAL_READ).isPresent()){
            entityList.add(new Privilege(GENERAL_READ));
        }
        if(!repository.findById(GENERAL_ADMIN).isPresent()){
            entityList.add(new Privilege(GENERAL_ADMIN));
        }
    }

    @Override
    public void addDependencies(List<Fixture<?>> dependencies) {
    
    }

}
