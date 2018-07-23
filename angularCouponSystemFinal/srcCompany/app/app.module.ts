import { BrowserModule } from '@angular/platform-browser';
import { NgModule, Component } from '@angular/core';


import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { DataService } from '../app/services/data.service';
import {DatePipe} from '@angular/common';

import { AppComponent } from './app.component';
import { CreateCouponComponent } from './components/create-coupon/create-coupon.component';
import { GetAllCouponsComponent } from './components/get-all-coupons/get-all-coupons.component';
import { MenuBarComponent } from './components/menu-bar/menu-bar.component';
import { WelcomeComponent } from './components/welcome/welcome.component';
import { GetCouponComponent } from './components/get-coupon/get-coupon.component';
import { UpdateCouponComponent } from './components/update-coupon/update-coupon.component';
import { GetCouponsByTypeComponent } from './components/get-coupons-by-type/get-coupons-by-type.component';


@NgModule({
  declarations: [
    AppComponent,
    CreateCouponComponent,
    GetAllCouponsComponent,
    MenuBarComponent,
    WelcomeComponent,
    GetCouponComponent,
    UpdateCouponComponent,
    GetCouponsByTypeComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot([
      {
        path:'create-coupon',
        component: CreateCouponComponent
      },
      {
        path:'get-coupon',
        component: GetCouponComponent
      },
      {
        path:'get-all-coupons',
        component: GetAllCouponsComponent
      },
      {
        path:'update-coupon',
        component: UpdateCouponComponent
      },
      {
        path:'get-coupons-by-type',
        component: GetCouponsByTypeComponent
      },
      {
        path:'',
        component: WelcomeComponent
      }
    ])
  ],
  providers: [DataService, DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
