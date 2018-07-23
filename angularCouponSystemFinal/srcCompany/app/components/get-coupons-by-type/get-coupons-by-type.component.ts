import { Component, OnInit } from '@angular/core';
import {Http} from '@angular/http';
import { Coupon } from '../common/Coupon';

import { DataService } from '../../services/data.service';
import 'rxjs/add/operator/map';

@Component({
  selector: 'app-get-coupons-by-type',
  templateUrl: './get-coupons-by-type.component.html',
  styleUrls: ['./get-coupons-by-type.component.css']
})
export class GetCouponsByTypeComponent implements OnInit {

  public _couponsByType :  Coupon[] = [    
  ];
  private _selectedType:string;

  constructor(private _http : Http, private _service : DataService) {
   }

  ngOnInit() {
  }


  public getCouponByType()
  {
      var self = this;
      this._http.get('http://localhost:8080/company/coupons/' + this._selectedType)
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
      }
        // function(coupon)
        // {
        //   console.log(coupon);
        //   self.coupon = coupon;
        // }
      )
  }

}
