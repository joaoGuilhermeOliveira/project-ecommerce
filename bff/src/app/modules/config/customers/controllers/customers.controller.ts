import {
  Body,
  Controller,
  Get,
  Post,
  Query,
  Headers
} from '@nestjs/common';
import { CreateCustomerRequestDto } from '../dtos/requests/create-customer-req.dto';
import { CustomersControllerInterface } from './customers.controller.interface';
import { CustomersService } from '../services/customers.service';

@Controller('customers')
export class CustomersController implements CustomersControllerInterface {
  constructor(private customersService: CustomersService) {}

  @Post('')
  async createCustomer(@Body() body: CreateCustomerRequestDto) {
    return this.customersService.createCustomer(body);
  }

  @Get('')
  async getCustomerByCpf(
    @Query('cpf') cpf: string,
    @Headers('authorization') authorization: string,
  ) {
    return this.customersService.getCustomerByCpf(cpf, authorization);
  }
}
