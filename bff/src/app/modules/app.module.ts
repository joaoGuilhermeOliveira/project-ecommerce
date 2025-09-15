import { MiddlewareConsumer, Module, NestModule } from '@nestjs/common';
import { ConfigModule } from '@nestjs/config';
import { CustomersModule } from './config/customers/customers.module';
import config from './config/config';

@Module({
    imports: [
        ConfigModule.forRoot({
            isGlobal:true,
            load: [config]
        }),
        CustomersModule
    ]
})
export class AppModule implements NestModule {
    configure(consumer: MiddlewareConsumer) {
    }
}
