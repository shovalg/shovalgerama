import { Component, OnInit } from '@angular/core';
import { Company } from '../common/Company';
import { Http } from '@angular/http';
import swal from 'sweetalert2';

@Component({
  selector: 'app-create-company',
  templateUrl: './create-company.component.html',
  styleUrls: ['./create-company.component.css']
})
export class CreateCompanyComponent implements OnInit {

  public company : Company = new Company();

  constructor(private _http : Http) { }

  ngOnInit() {
  }

  public addCompany()
  {
    if(this.company.name == null || this.company.password == null ||
       this.company.name == "" || this.company.password == "")
    {
      swal({
        type: 'error',
        title: 'Your company details creation failed',
        showConfirmButton: false,
        timer: 1500
      })
    }
    else
    {
      console.log(this.company);
      this._http.post('http://localhost:8080/admin/company',
      this.company).subscribe(function(response)
      {
        console.log(response);
          swal({
            type: 'success',
            title: 'Your company details has been saved',
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
    }
  }
}
