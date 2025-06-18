"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const { PORT } = process.env;
const getConfiguration = () => ({
    port: parseInt(PORT, 10) || 3000,
});
exports.default = getConfiguration;
//# sourceMappingURL=config.js.map