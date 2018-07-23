import { BrowserModule } from '@angular/platform-browser';
import { NgModule, Component } from '@angular/core';


import { AppComponent } from './app.component';

import {RouterModule} from '@angular/router';
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';
import { CreateCompanyComponent } from './components/create-company/create-company.component';
import { GetCompanyComponent } from './components/get-company/get-company.component';
import { CreateCustomerComponent } from './components/create-customer/create-customer.component';
import { GetAllCompaniesComponent } from './components/get-all-companies/get-all-companies.component';
import { WelcomeComponent } from './components/welcome/welcome.component';
import { MenuBarComponent } from './components/menu-bar/menu-bar.component';
import { UpdateCompanyComponent } from './components/update-company/update-company.component';
import { DataService } from '../app/services/data.service';
import { GetCustomerComponent } from './components/get-customer/get-customer.component';
import { GetAllCustomersComponent } from './components/get-all-customers/get-all-customers.component';
import { UpdateCustomerComponent } from './components/update-customer/update-customer.component';

@NgModule({
  declarations: [
    AppComponent,
    CreateCompanyComponent,
    GetCompanyComponent,
    CreateCustomerComponent,
    GetAllCompaniesComponent,
    WelcomeComponent,
    MenuBarComponent,
    UpdateCompanyComponent,
    GetCustomerComponent,
    GetAllCustomersComponent,
    UpdateCustomerComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot([
      {
        path:'create-company',
        component: CreateCompanyComponent
      },
      {
        path:'create-customer',
        component: CreateCustomerComponent
      },
      {
        path:'get-company',
        component: GetCompanyComponent
      },
      {
        path:'get-customer',
        component: GetCustomerComponent
      },
      {
        path:'get-all-companies',
        component: GetAllCompaniesComponent
      },
      {
        path:'get-all-customers',
        component: GetAllCustomersComponent
      },
      {
        path:'update-company',
        component: UpdateCompanyComponent
      },
      {
        path:'update-customer',
        component: UpdateCustomerComponent
      },
      {
        path:'',
        component: WelcomeComponent
      }
  ])
  ],
  providers: [DataService],
  bootstrap: [AppComponent]
})
export class AppModule { }
