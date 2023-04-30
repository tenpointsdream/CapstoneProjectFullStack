import { UserType } from "./UserSubmit";

export interface UserProfile {
    id: number;
    name: string;
    username: string;
    password: string;
    email: string;
    userType: UserType;
}