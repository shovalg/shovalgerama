import { Component, OnInit } from '@angular/core';
import {Http} from '@angular/http';
import 'rxjs/add/operator/map';
import swal from 'sweetalert2';

import { Coupon } from '../common/Coupon';

@Component({
  selector: 'app-get-purchased-coupons-by-price',
  templateUrl: './get-purchased-coupons-by-price.component.html',
  styleUrls: ['./get-purchased-coupons-by-price.component.css']
})
export class GetPurchasedCouponsByPriceComponent implements OnInit {

  public _couponsByPrice :  Coupon[] = [    
  ];
  private _selectedPrice:string;

  constructor(private _http : Http) { }

  ngOnInit() {
  }

  public getPurchasedCouponsByPrice()
  {
    if(this._selectedPrice == undefined || this._selectedPrice == "")
    {
      swal({
        title: 'You must enter coupon top price!',      
        type: 'warning'
      })
    }
    else
    {
      var self = this;
      this._http.get('http://localhost:8080/customer/coupons/byprice/' + this._selectedPrice)
      .map(
        function(couponsByPriceResponse)
        {
          return couponsByPriceResponse.json();
        }
      ).subscribe(
        function(couponsByPrice)
      {
        for(let coupon of couponsByPrice)
        {
          console.log(coupon);
        }
        self._couponsByPrice = couponsByPrice;
      })
    }
  }
}
