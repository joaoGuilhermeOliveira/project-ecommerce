import { MiddlewareConsumer, Module, NestModule } from '@nestjs/common';
import { ConfigModule } from '@nestjs/config';

@Module({
    imports: [
        ConfigModule.forRoot({
            isGlobal:true,
        })
    ]
})
export class AppModule implements NestModule {
    configure(consumer: MiddlewareConsumer) {
    }
}
