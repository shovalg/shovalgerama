import { Component, OnInit } from '@angular/core';
import { Coupon } from '../common/Coupon';
import {Http} from '@angular/http';
import swal from 'sweetalert2';

import { DataService } from '../../services/data.service';
import 'rxjs/add/operator/map';

@Component({
  selector: 'app-purchase-coupon',
  templateUrl: './purchase-coupon.component.html',
  styleUrls: ['./purchase-coupon.component.css']
})
export class PurchaseCouponComponent implements OnInit {

  public _allCoupons :  Coupon[] = [    
  ];
  public coupon : Coupon = new Coupon();

  constructor(private _http : Http, private _service : DataService) { 
  }

  ngOnInit() {
    this.getAllCoupons();
  }

  getAllCoupons()
  {
    var self = this;
    this._http.get('http://localhost:8080/company/all.coupons')
    .map(
      function (allCouponsResponse)
      {
        return (allCouponsResponse).json();
      }
    ).subscribe(
      function(allCoupons)
      {
        for(let coupon of allCoupons)
        {
          console.log(coupon);
        }
        self._allCoupons = allCoupons;
      }
    )
  }

  public purchaseCoupon(coupon)
  {
    var self = this;
    this._http.put('http://localhost:8080/customer/coupon', 
        coupon).subscribe(function(response)
        {
          console.log(response);
          swal({
            title: 'Your coupon has been purchased',      
            type: 'success'
          })
          self.getAllCoupons();
        },
        function(err)
        {
          console.log(err);
          swal({
            title: err._body,      
            type: 'error'
          })
        }          
      )
  }

}
