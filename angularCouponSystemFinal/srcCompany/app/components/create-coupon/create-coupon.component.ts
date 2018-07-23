import { Component, OnInit } from '@angular/core';

import { Coupon } from '../common/Coupon';

import { Http } from '@angular/http';
import swal from 'sweetalert2';
import {DatePipe} from '@angular/common';

@Component({
  selector: 'app-create-coupon',
  templateUrl: './create-coupon.component.html',
  styleUrls: ['./create-coupon.component.css']
})
export class CreateCouponComponent implements OnInit {

  public coupon : Coupon = new Coupon();

  constructor(private _http : Http, public datepipe: DatePipe) { }

  ngOnInit() {
  }

  public img()
  {
    switch(this.coupon.type)
    {
      case "RESTURANTE":
      this.coupon.image = "http://static.asiawebdirect.com/m/bangkok/portals/pattaya-bangkok-com/homepage/pattaya-top10s/top10-restaurants-naklua-beach/pagePropertiesImage/top10-restaurants-naklua-beach-1200.jpg"
      break;
      case "ELECTRICITY":
      this.coupon.image = "http://www.patlabelsonline.co.uk/blog/wp-content/uploads/2015/08/shutterstock_130275758-638x300.jpg"
      break;
      case "FOOD":
      this.coupon.image = "http://static5.uk.businessinsider.com/image/5a7dc50f7101ad33ca5a5c26-807/red%20robin%20.jpg"
      break;
      case "HEALTH":
      this.coupon.image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSBX-QrZspg0CZ_puiwxisdRdp7igfDvfijChAvKSYI7xeLXajf"
      break;
      case "SPORTS":
      this.coupon.image = "http://decodingannieparkerfilm.com/wp-content/uploads/2018/07/003.jpg"
      break;
      case "CAMPING":
      this.coupon.image = "https://recreation-acm.activefederal.com/assetfactory.aspx?did=7656"
      break;
      case "TRAVELLING":
      this.coupon.image = "http://www.diegomallien.com/wp-content/uploads/2017/03/Meaning-of-travelling.jpg"
      break;
    }
  }

  public addCoupon()
  {
    this.img();
    if(this.coupon.title == null || this.coupon.title == "")
    {
      swal({
        type: 'error',
        title: 'Your coupon details creation failed',
        showConfirmButton: false,
        timer: 1500
      })
    }
    else
    {
      var now = new Date();
      var start_date = this.datepipe.transform(now, 'dd/MM/yyyy');
      var end_date = this.datepipe.transform(this.coupon.end_date, 'dd/MM/yyyy');
      if(start_date == end_date || start_date > end_date) 
      {
        swal({
          title: 'end date must be greater than start date!',      
          type: 'warning'
        }) 
      }
      else
      {
        this._http.post('http://localhost:8080/company/coupon', 
          this.coupon).subscribe(function(response)
          {
            console.log(response);
            swal({
              type: 'success',
              title: 'Your coupon details has been saved',
              showConfirmButton: false,
              timer: 1500
            })
          },function(err)
          {
            console.log(err);
            swal({
              title: err._body,
              type: 'error'
            })
          })     
        // );
        // swal({
        //     type: 'success',
        //     title: 'Your coupon details has been saved',
        //     showConfirmButton: false,
        //     timer: 1500
        //   })
      }
    }
  }
}
