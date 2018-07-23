import { Component, OnInit } from '@angular/core';
import { Customer } from '../common/Customer';
import { Http } from '@angular/http';
import swal from 'sweetalert2';

@Component({
  selector: 'app-create-customer',
  templateUrl: './create-customer.component.html',
  styleUrls: ['./create-customer.component.css']
})
export class CreateCustomerComponent implements OnInit {

  public customer : Customer = new Customer;

  constructor(private _http : Http) { }

  ngOnInit() {
  }

  public addCustomer()
  {
    if(this.customer.name == null || this.customer.password == null ||
       this.customer.name == "" || this.customer.password == "")
    {
      swal({
        type: 'error',
        title: 'Your customer details creation failed',
        showConfirmButton: false,
        timer: 1500
      })
    }
    else
    {
      this._http.post('http://localhost:8080/admin/customer',
      this.customer).subscribe(function(response)
      {
        console.log(response);
          swal({
            type: 'success',
            title: 'Your customer details has been saved',
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
    });
    }
  }
}
