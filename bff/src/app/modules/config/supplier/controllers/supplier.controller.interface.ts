import { CreateSupplierRequestDto } from '../dtos/requests/create-supplier-req.dto';


export interface SupplierControllerInterface {
    getSupplierByCnpj(cnpj: string, token: string);

}
