import * as dotenv from 'dotenv';
dotenv.config();

const { PORT, HOST, MS_BASE_URL } = process.env;

export default () => {
  return {
    port: parseInt(PORT, 10) || 8083,
    microServices: {
      baseUrl: MS_BASE_URL,
    }
  };
};