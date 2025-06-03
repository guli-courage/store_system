package system.mapper;


import org.springframework.stereotype.Repository;
import system.pojo.Manager;

@Repository("ManagerMapper")
public interface ManagerMapper {

    Manager selectByNameAndPass(Manager manager);
}
