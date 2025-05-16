package ra.project.model.mapper;

import ra.project.model.dto.request.CustomerAddDto;
import ra.project.model.dto.request.CustomerUpdateDto;
import ra.project.model.entity.Customer;

public class CustomerMapper {
    public static Customer mapCustomerAddDtoToEntity(CustomerAddDto request){
        Customer customer = new Customer();
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());
        customer.setSex(request.getSex());
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        return customer;
    }
    public static void mapCustomerUpdateDtoToEntity(CustomerUpdateDto request, Customer entity){
        if (request.getFirstName()!=null){
            entity.setFirstName(request.getFirstName());
        }
        if (request.getSex()!=null){
            entity.setSex(request.getSex());
        }
        if (request.getEmail()!=null){
            entity.setEmail(request.getEmail());
        }
        if (request.getLastName()!=null){
            entity.setLastName(request.getLastName());
        }
        if (request.getPhone()!=null){
            entity.setPhone(request.getPhone());
        }

    }
}
