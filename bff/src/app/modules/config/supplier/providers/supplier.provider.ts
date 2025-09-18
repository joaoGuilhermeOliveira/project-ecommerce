
import { ConfigService } from '@nestjs/config';
import axios from 'axios';
import { Injectable } from '@nestjs/common';
import { GetSupplierResponseDto } from '../dtos/responses/get-supplier-res.dto';


@Injectable()
export class SuppliersProvider {
    constructor(private configService: ConfigService) {
        }

    async getSupplierByCnpj(cnpj: string, token: string): Promise<GetSupplierResponseDto> {
        const url = `${this.configService.get('microServices.baseUrl')}/customers`;

        const authorization = `Bearer ${token}`;

        const response = await axios.get(url, {
            params: { cnpj },
            headers: {
                'Authorization': authorization,
            }
        });

        return response.data;
    }
}