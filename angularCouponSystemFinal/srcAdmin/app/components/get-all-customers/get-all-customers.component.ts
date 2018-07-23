import { Component, OnInit } from '@angular/core';
import { Customer } from '../common/Customer';
import { Http } from '@angular/http';

import { DataService } from '../../services/data.service';
import 'rxjs/add/operator/map';
import swal from 'sweetalert2';

@Component({
  selector: 'app-get-all-customers',
  templateUrl: './get-all-customers.component.html',
  styleUrls: ['./get-all-customers.component.css']
})
export class GetAllCustomersComponent implements OnInit {

  public _allCustomers : Customer[] = [
    new Customer()
  ];
  private _customerId : number;
  private _customerName : String;

  constructor(private _http : Http, private _service : DataService) {
    this.getAllCustomers();
   }

  ngOnInit() {
  }

  getAllCustomers()
  {
    var self = this;
    this._http.get('http://localhost:8080/admin/customers')
    .map(
      function (allCustomersResponse)
      {
        return allCustomersResponse.json();
      }
    ).subscribe(
      function(allCustomers)
      {
        for(let customer of allCustomers)
        {
          console.log(customer);
        }
        self._allCustomers = allCustomers;
      }
    )
  }

  public editCustomer(_customerName)
  {
    this._service.selectedName = _customerName;
  }

  public deleteCustomer(_customerId)
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
        this._service.selectedId = _customerId;
        this._http.delete(`http://localhost:8080/admin/customer/remove/${_customerId}`).subscribe(
          function(customer)
          {
            self.getAllCustomers();
          }
        )
        swal(
          'Deleted!',
          'Customer has been deleted.',
          'success'          
        )
      }   
    })
  }
}
