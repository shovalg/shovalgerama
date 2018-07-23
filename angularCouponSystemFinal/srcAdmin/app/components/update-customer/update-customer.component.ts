import { Component, OnInit } from '@angular/core';
import {Http} from '@angular/http';
import { Customer } from '../common/Customer';
import { DataService } from '../../services/data.service';
import swal from 'sweetalert2';

@Component({
  selector: 'app-update-customer',
  templateUrl: './update-customer.component.html',
  styleUrls: ['./update-customer.component.css']
})
export class UpdateCustomerComponent implements OnInit {

  public customer : Customer = new Customer();
  constructor(private _http : Http, private _service : DataService) { }

  ngOnInit() {
    this.customer.name = this._service.selectedName;
  }

  public updateCustomer()
  {
    var self = this;
    this._http.put('http://localhost:8080/admin/customer', this.customer).subscribe(
      function(customerResponse)
      {
        console.log(customerResponse);
        swal({
          title: 'Customer Updated successfully!',
          text: 'Please refresh by pressing Get all customers button if needed',
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
