import { Component, OnInit } from '@angular/core';
import { Company } from '../common/Company';
import { Http } from '@angular/http';

import { DataService } from '../../services/data.service';
import 'rxjs/add/operator/map';
import swal from 'sweetalert2';
import { text } from '@angular/core/src/render3/instructions';

@Component({
  selector: 'app-get-all-companies',
  templateUrl: './get-all-companies.component.html',
  styleUrls: ['./get-all-companies.component.css']
})
export class GetAllCompaniesComponent implements OnInit {

  public _allCompanies : Company[] = [
    new Company()
  ];
  private _companyId : number;
  private _companyName : String;

  constructor(private _http : Http, private _service : DataService) { 
    this.getAllCompanies();
  }

  ngOnInit() {
  }

  getAllCompanies()
  {
    var self = this;
      this._http.get('http://localhost:8080/admin/companies')
      .map(
        function (allCompaniesResponse)
        {
          return allCompaniesResponse.json();
        }
      ).subscribe(
        function(allCompanies)
        {
          for(let company of allCompanies)
          {
            console.log(company);
          }
          self._allCompanies = allCompanies;
        }
      )
  }

  public editCompany(_companyName)
  {
    this._service.selectedName = _companyName;
  }

  public deleteCompany(_companyId)
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
        this._service.selectedId = _companyId;
        this._http.delete(`http://localhost:8080/admin/company/remove/${_companyId}`).subscribe(
          function(company)
          {
            self.getAllCompanies();
          }
        )
        swal(
          'Deleted!',
          'Company has been deleted.',
          'success'          
        )
      }   
    })
  }
}