import { CreateCustomerRequestDto } from "../dtos/requests/create-customer-req.dto";

export interface CustomersServiceInterface {
    createCustomer(CreateCustomerRequestDto: CreateCustomerRequestDto);

    getCustomerByCpf(cpf: string, token: string);
}