import { CreateCustomerRequestDto } from '../dtos/requests/create-customer-req.dto';

export interface CustomersControllerInterface {
  createCustomer(body: CreateCustomerRequestDto);
}
