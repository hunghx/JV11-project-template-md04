package ra.project.service;

import ra.project.model.dto.request.CustomerAddDto;
import ra.project.model.dto.request.CustomerUpdateDto;
import ra.project.model.dto.response.PageDto;
import ra.project.model.entity.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> getCustomers();
    PageDto<Customer> getCustomersPagination(int page, int size);
    Customer getCustomersById(Long id);
    Customer addCustomer(CustomerAddDto request);
    Customer updateCustomer(CustomerUpdateDto request, Long id);
    void deleteCustomer(Long id);
}
