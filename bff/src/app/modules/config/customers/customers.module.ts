import { Module } from "@nestjs/common";
import { CustomersProvider} from "./providers/customers.provider"
import { CustomersService } from "./services/customers.service";
import { CustomersController } from "./controllers/customers.controller";

@Module({
    providers: [
        CustomersProvider, CustomersService
    ],
    controllers: [CustomersController],
})
export class CustomersModule {}