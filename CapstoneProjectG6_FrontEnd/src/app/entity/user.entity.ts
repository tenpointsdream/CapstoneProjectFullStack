import { UserType } from "./UserSubmit";

export class User {
    public name!: string;
    public username!: string;
    public password!: string;
    public email!: string;
    public userType!: UserType;
}