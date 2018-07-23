import { Component, OnInit } from '@angular/core';
import {Http} from '@angular/http';
import { Company } from '../common/Company';
import { DataService } from '../../services/data.service';
import swal from 'sweetalert2';

@Component({
  selector: 'app-update-company',
  templateUrl: './update-company.component.html',
  styleUrls: ['./update-company.component.css']
})
export class UpdateCompanyComponent implements OnInit {

  public company : Company = new Company();
  constructor(private _http : Http, private _service : DataService) { }

  ngOnInit() {
    this.company.name = this._service.selectedName;
  }

  public updateCompany()
  {
    this._http.put('http://localhost:8080/admin/company', this.company).subscribe(
      function(companyResponse)
      {
        console.log(companyResponse);
        swal({
          title: 'Company Updated successfully!',
          text: 'Please refresh by pressing Get all companies button if needed',  
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
