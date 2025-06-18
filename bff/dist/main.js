"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const config_1 = require("./config/config");
const core_1 = require("@nestjs/core");
const app_module_1 = require("./app.module");
class App {
    constructor() {
        this.defaultConfig = (0, config_1.default)();
        this.bootstrap();
    }
    async bootstrap() {
        const app = await core_1.NestFactory.create(app_module_1.AppModule);
        await app.listen(this.defaultConfig.port, async () => {
            return console.log(`Server is running on port ${this.defaultConfig.port}`);
        });
    }
}
exports.default = new App().app;
//# sourceMappingURL=main.js.map