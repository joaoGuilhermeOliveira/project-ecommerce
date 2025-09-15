import { INestApplication } from '@nestjs/common';
import { OpenAPIObject } from '@nestjs/swagger';
import { NestFactory } from '@nestjs/core';
import { AppModule } from './app.module';
import { ConfigService } from '@nestjs/config';

class App {
  app: INestApplication;
  swaggerConfig: Omit<OpenAPIObject, 'paths'>;

  constructor() {
    this.bootstrap();
  }

  async bootstrap() {
    const app = await NestFactory.create(AppModule);

    const configService = app.get(ConfigService);

    await app.listen(configService.get<number>('port'), async () => {
      console.log(`Server is running on port ${configService.get<number>('port')}`);
    });

    this.app = app;
  }
}

export default new App().app;