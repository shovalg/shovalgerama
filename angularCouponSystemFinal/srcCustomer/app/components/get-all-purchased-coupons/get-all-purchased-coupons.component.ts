import { Component, OnInit } from '@angular/core';
import {Http} from '@angular/http';
import 'rxjs/add/operator/map';

import { Coupon } from '../common/Coupon';

@Component({
  selector: 'app-get-all-purchased-coupons',
  templateUrl: './get-all-purchased-coupons.component.html',
  styleUrls: ['./get-all-purchased-coupons.component.css']
})
export class GetAllPurchasedCouponsComponent implements OnInit {

  public _coupons :  Coupon[] = [    
  ];

  constructor(private _http : Http) { }

  ngOnInit() {
    this.getPurchasedCoupons();
  }

  public getPurchasedCoupons()
  {
      var self = this;
      this._http.get('http://localhost:8080/customer/coupons')
      .map(
        function(couponsResponse)
        {
          return couponsResponse.json();
        }
      ).subscribe(
        function(coupons)
      {
        for(let coupon of coupons)
        {
          console.log(coupon);
        }
        self._coupons = coupons;
      })
  }

}
