export class Coupon{
    constructor(public title?:string, public start_date?:Date, public end_date?:Date, public amount?:number,
                public type?:string, public message?:string, public price?:number, public image?:string)
    {
        
    }
}