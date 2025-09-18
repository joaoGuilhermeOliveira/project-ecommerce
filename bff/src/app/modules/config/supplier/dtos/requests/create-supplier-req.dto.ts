import { AddressDto } from "../../../customers/dtos/address.dto"; 

export interface CreateSupplierRequestDto {
    name: string;
    lastName: string;
    cpf: string;
    email: string;
    address: AddressDto;
    birthDate: string;
    phone: string;
    password: string;
}