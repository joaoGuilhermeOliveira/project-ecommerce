import axios from 'axios';
import { CreateCustomerRequestDto } from '../dtos/requests/create-customer-req.dto';
import { ConfigService } from '@nestjs/config';
import { Injectable } from '@nestjs/common';
import { GetCustomerResponseDto } from '../dtos/responses/get-customer-res.dto';

@Injectable()
export class CustomersProvider {
    constructor(private configService: ConfigService) {
        }
    async createCustomer(customerDto: CreateCustomerRequestDto) {
        const url = `${this.configService.get('microServices.baseUrl')}/customers`;

        const response = await axios.post(url, customerDto, {
            headers: { 'Content-Type': 'application/json' }
        });
        return response.data;
    }

    async getCustomerByCpf(cpf: string, token: string): Promise<GetCustomerResponseDto> {
        const url = `${this.configService.get('microServices.baseUrl')}/customers`;

        const authorization = `Bearer ${token}`;

        const response = await axios.get(url, {
            params: { cpf },
            headers: {
                'Authorization': authorization,
            }
        });

        return response.data;
    }
}