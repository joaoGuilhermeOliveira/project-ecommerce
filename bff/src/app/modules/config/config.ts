const { PORT } = process.env;

const getConfiguration = () => ({
  port: parseInt(PORT, 10) || 3000,
});

export default getConfiguration;
export type Configuration = ReturnType<typeof getConfiguration>;