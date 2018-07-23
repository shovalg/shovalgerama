import { BrowserModule } from '@angular/platform-browser';
import { NgModule, Component } from '@angular/core';

import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { DataService } from '../app/services/data.service';

import { AppComponent } from './app.component';
import { GetAllPurchasedCouponsComponent } from './components/get-all-purchased-coupons/get-all-purchased-coupons.component';
import { PurchaseCouponComponent } from './components/purchase-coupon/purchase-coupon.component';
import { MenuBarComponent } from './components/menu-bar/menu-bar.component';
import { WelcomeComponent } from './components/welcome/welcome.component';
import { GetPurchasedCouponsByTypeComponent } from './components/get-purchased-coupons-by-type/get-purchased-coupons-by-type.component';
import { GetPurchasedCouponsByPriceComponent } from './components/get-purchased-coupons-by-price/get-purchased-coupons-by-price.component';

@NgModule({
  declarations: [
    AppComponent,
    GetAllPurchasedCouponsComponent,
    PurchaseCouponComponent,
    MenuBarComponent,
    WelcomeComponent,
    GetPurchasedCouponsByTypeComponent,
    GetPurchasedCouponsByPriceComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot([
      {
        path:'get-all-purchased-coupons',
        component: GetAllPurchasedCouponsComponent
      },
      {
        path:'get-purchased-coupons-by-type',
        component: GetPurchasedCouponsByTypeComponent
      },
      {
        path:'get-purchased-coupons-by-price',
        component: GetPurchasedCouponsByPriceComponent
      },
      {
        path:'purchase-coupon',
        component: PurchaseCouponComponent
      },
      {
        path:'',
        component: WelcomeComponent
      }
    ])
  ],
  // providers: [],
  providers: [DataService],
  bootstrap: [AppComponent]
})
export class AppModule { }
