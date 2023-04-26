export class UserSubmit{
    constructor(
        public name: string,
        public username: string,
        public password: string,
        public email: string,
        public userType: string
    ){}
}

export enum UserType{
    ADMIN, USER
}