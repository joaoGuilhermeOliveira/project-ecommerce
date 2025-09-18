import { AddressDto } from "../../../customers/dtos/address.dto"; 

export enum StatusEnum {
  ACTIVE = "ACTIVE",
  INACTIVE = "INACTIVE",
  PENDING = "PENDING"
}

export interface GetSupplierResponseDto {
  cnpj: string;
  name: string;
  phone_number: string;
  email: string;
  address: AddressDto;
  status: StatusEnum;
}
