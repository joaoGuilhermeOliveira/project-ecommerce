declare const getConfiguration: () => {
    port: number;
};
export default getConfiguration;
export type Configuration = ReturnType<typeof getConfiguration>;
