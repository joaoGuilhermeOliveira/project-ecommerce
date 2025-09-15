import { AddressDto } from "../address.dto";

export class CreateCustomerRequestDto {
    name: string;
    lastName: string;
    cpf: string;
    email: string;
    address: AddressDto;
    birthDate: string;
    phone: string;
    password: string
}