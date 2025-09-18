import {
  Body,
  Controller,
  Get,
  Post,
  Query,
  Headers
} from '@nestjs/common';import { SupplierControllerInterface } from "./supplier.controller.interface";
import { SupplierService } from "../services/supplier.service";


@Controller('suppliers')
export class SuppliersController implements SupplierControllerInterface {
  constructor(private customersService: SupplierService) {}


  @Get('')
  async getSupplierByCnpj(
    @Query('cnpj') cnpj: string,
    @Headers('authorization') authorization: string,
  ): Promise<any> {
    return this.customersService.getSupplierByCnpj(cnpj, authorization);
  }
}
