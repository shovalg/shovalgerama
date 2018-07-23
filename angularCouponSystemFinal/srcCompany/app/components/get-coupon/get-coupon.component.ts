import { Component, OnInit } from '@angular/core';
import {Http} from '@angular/http';
import { Coupon } from '../common/Coupon';
import 'rxjs/add/operator/map';
import swal from 'sweetalert2';

@Component({
  selector: 'app-get-coupon',
  templateUrl: './get-coupon.component.html',
  styleUrls: ['./get-coupon.component.css']
})
export class GetCouponComponent implements OnInit {

  public coupon : Coupon = new Coupon();
  private _selectedId:number;

  constructor(private _http : Http) { }

  ngOnInit() {
  }

  public getCoupon()
  {
      var self = this;
      if (this._selectedId == null)
      {
        swal ({
          title: "Please enter coupon's Id number",
          type: 'info'
        })
      }
      else
      {
        this._http.get(`http://localhost:8080/company/coupon/${this._selectedId}`)
        .map(
          function(coupon)
          {
            return coupon.json();
          }
        ).subscribe(
          function(couponResponse)
          {
            console.log(couponResponse);
            self.coupon = couponResponse;
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

}
