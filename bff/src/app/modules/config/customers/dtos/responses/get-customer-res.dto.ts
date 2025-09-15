import { AddressDto } from "../address.dto";

export class GetCustomerResponseDto {
     name: string;
     cpf: string;
     email: string;
     address: AddressDto;
     birthDate: Date;
     phone: string
     status: string;
}