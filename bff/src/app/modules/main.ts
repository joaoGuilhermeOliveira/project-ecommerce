import { INestApplication } from '@nestjs/common';
import { OpenAPIObject } from '@nestjs/swagger';
import getConfiguration, { Configuration } from './config/config';
import { NestFactory } from '@nestjs/core';
import { AppModule } from './app.module';

class App {
  app: INestApplication;
  swaggerConfig: Omit<OpenAPIObject, 'paths'>;

  private defaultConfig: Configuration;

  constructor() {
    this.defaultConfig = getConfiguration();
    this.bootstrap();
  }

  async bootstrap() {
    const app = await NestFactory.create(AppModule);
    await app.listen(this.defaultConfig.port, async () => {
        return console.log(`Server is running on port ${this.defaultConfig.port}`);
    });
  }
}

export default new App().app;
