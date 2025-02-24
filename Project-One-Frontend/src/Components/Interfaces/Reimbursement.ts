//Global interface to store and manage Reimbursement data

import { User } from "./User";

export interface Reimbursement {
    reimbursementID:number,
    description:string,
    amount:number,
    status:string,
    userId:number
}