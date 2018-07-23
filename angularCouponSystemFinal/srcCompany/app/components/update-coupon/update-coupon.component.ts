import { Component, OnInit } from '@angular/core';
import {Http} from '@angular/http';
import { Coupon } from '../common/Coupon';
import { DataService } from '../../services/data.service';
import swal from 'sweetalert2';

@Component({
  selector: 'app-update-coupon',
  templateUrl: './update-coupon.component.html',
  styleUrls: ['./update-coupon.component.css']
})
export class UpdateCouponComponent implements OnInit {

  public coupon : Coupon = new Coupon();
  constructor(private _http : Http, private _service : DataService) { }

  ngOnInit() {
    this.coupon.title = this._service.selectedTitle;
  }

  public updateCoupon()
  {
    this._http.put('http://localhost:8080/company/coupon', this.coupon).subscribe(
      function(couponResponse)
      {
        console.log(couponResponse);
        swal({
          title: 'Coupon Updated successfully!',
          text: 'Please refresh by pressing Get all coupons button if needed',  
          type: 'success'
         });
      },
      function(err)
      {
        console.log(err);
        swal({
          title: 'Update failed to complete!',      
          type: 'error'
         });
      }
     )
     
  }

}
