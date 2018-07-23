import { Component, OnInit } from '@angular/core';
import {Http} from '@angular/http';
import { Company } from '../common/Company';
import 'rxjs/add/operator/map';
import swal from 'sweetalert2';

@Component({
  selector: 'app-get-company',
  templateUrl: './get-company.component.html',
  styleUrls: ['./get-company.component.css']
})
export class GetCompanyComponent implements OnInit {

  public company : Company = new Company();
  private _selectedId:number;

  constructor(private _http : Http) { }

  ngOnInit() { 
  }

  public getCompany()
  {
      var self = this;
      if (this._selectedId == null)
      {
        swal ({
          title: "Please enter company's Id number",
          type: 'info'
        })
      }
      else
      {
        this._http.get(`http://localhost:8080/admin/company/${this._selectedId}`)
        .map(
         function(company)
         {
           return company.json();
         }
        ).subscribe(
         function(companyResponse)
         {
           console.log(companyResponse);
           self.company = companyResponse;
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