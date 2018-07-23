import { Component, OnInit } from '@angular/core';
import { Coupon } from '../common/Coupon';
import { Http } from '@angular/http';

import { DataService } from '../../services/data.service';
import 'rxjs/add/operator/map';
import swal from 'sweetalert2';

@Component({
  selector: 'app-get-all-coupons',
  templateUrl: './get-all-coupons.component.html',
  styleUrls: ['./get-all-coupons.component.css']
})
export class GetAllCouponsComponent implements OnInit {

  public _allCoupons :  Coupon[] = [    
  ];
  public coupon : Coupon = new Coupon();
  private _couponId : number;
  private _couponTitle : String;

  constructor(private _http : Http, private _service : DataService) {
   }

  ngOnInit() {
    this.getAllCoupons();
  }

  getAllCoupons()
  {
    var self = this;
    this._http.get('http://localhost:8080/company/coupons')
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

   public editCoupon(_couponTitle)
   {
       this._service.selectedTitle = _couponTitle;
   }

  public deleteCoupon(_couponId)
  {
    swal({
      title: 'Are you sure?',
      text: "You won't be able to revert this!",
      type: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
      if (result.value)
      {
        var self = this;
        this._service.selectedId = _couponId;
        this._http.delete(`http://localhost:8080/company/coupon/remove/${_couponId}`).subscribe(
          function(coupon)
          {
            self.getAllCoupons();
          }
        )
        swal(
          'Deleted!',
          'Coupon has been deleted.',
          'success'          
        )
      }
    })
  }
}
