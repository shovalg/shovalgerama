import { Component, OnInit } from '@angular/core';
import {Http} from '@angular/http';
import 'rxjs/add/operator/map';
import swal from 'sweetalert2';

import { Coupon } from '../common/Coupon';

@Component({
  selector: 'app-get-purchased-coupons-by-type',
  templateUrl: './get-purchased-coupons-by-type.component.html',
  styleUrls: ['./get-purchased-coupons-by-type.component.css']
})
export class GetPurchasedCouponsByTypeComponent implements OnInit {

  public _couponsByType :  Coupon[] = [    
  ];
  private _selectedType:string;

  constructor(private _http : Http) { }

  ngOnInit() {
  }

  public getPurchasedCouponsByType()
  {
    if(this._selectedType == undefined)
    {
      swal({
        title: 'Coupon type was not selected',      
        type: 'warning'
      })
    }
    else
    {
      var self = this;
      this._http.get('http://localhost:8080/customer/coupons/bytype/' + this._selectedType)
      .map(
        function(couponsByTypeResponse)
        {
          return couponsByTypeResponse.json();
        }
      ).subscribe(
        function(couponsByType)
      {
        for(let coupon of couponsByType)
        {
          console.log(coupon);
        }
        self._couponsByType = couponsByType;
      })
    }
  }

}
