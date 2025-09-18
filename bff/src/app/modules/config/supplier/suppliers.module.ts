import { Module } from "@nestjs/common";
import { SuppliersController } from "./controllers/suppliers.controller";
import { SuppliersProvider } from "./providers/supplier.provider";
import { SupplierService } from "./services/supplier.service";

@Module({
    providers: [
        SuppliersProvider, SupplierService
    ],
    controllers: [SuppliersController],
})
export class SuppliersModule {}