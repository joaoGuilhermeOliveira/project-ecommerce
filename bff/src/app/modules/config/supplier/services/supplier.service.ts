import { Injectable } from "@nestjs/common";
import { SuppliersServiceInterface } from "./supplier.service.interface";
import { SuppliersProvider } from "../providers/supplier.provider";


@Injectable()
export class SupplierService implements SuppliersServiceInterface {
    constructor(private supplierProvider: SuppliersProvider) {}

    async getSupplierByCnpj(cnpj: string, token: string) {
        return await this.supplierProvider.getSupplierByCnpj(cnpj, token);
    }

}