import { Component, OnInit } from '@angular/core';
import {Http} from '@angular/http';
import { Customer } from '../common/Customer';
import 'rxjs/add/operator/map';
import swal from 'sweetalert2';

@Component({
  selector: 'app-get-customer',
  templateUrl: './get-customer.component.html',
  styleUrls: ['./get-customer.component.css']
})
export class GetCustomerComponent implements OnInit {

  public customer : Customer = new Customer();
  private _selectedId:number;

  constructor(private _http : Http) { }

  ngOnInit() {
  }

  public getCustomer()
  {
      var self = this;
      if (this._selectedId == null)
      {
        swal ({
          title: "Please enter customer's Id number",
          type: 'info'
        })
      }
      else
      {
        this._http.get(`http://localhost:8080/admin/customer/${this._selectedId}`)
        .map(
          function(customer)
          {
            return customer.json();
          }
        ).subscribe(
          function(customerResponse)
          {
            console.log(customerResponse);
            self.customer = customerResponse;
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
