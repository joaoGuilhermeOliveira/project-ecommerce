import { Injectable } from "@nestjs/common";
import { CreateCustomerRequestDto } from "../dtos/requests/create-customer-req.dto";
import { CustomersProvider } from "../providers/customers.provider";
import { CustomersServiceInterface } from "./customers.service.interface";

@Injectable()
export class CustomersService implements CustomersServiceInterface {
    constructor(private customersProvider: CustomersProvider) {}

    async createCustomer(customerDto: CreateCustomerRequestDto) {
        return await this.customersProvider.createCustomer(customerDto);
    }

    async getCustomerByCpf(cpf: string, token: string) {
        return await this.customersProvider.getCustomerByCpf(cpf, token);
    }

}