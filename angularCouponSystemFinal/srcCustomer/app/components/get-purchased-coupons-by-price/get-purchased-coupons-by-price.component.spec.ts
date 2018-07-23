import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GetPurchasedCouponsByPriceComponent } from './get-purchased-coupons-by-price.component';

describe('GetPurchasedCouponsByPriceComponent', () => {
  let component: GetPurchasedCouponsByPriceComponent;
  let fixture: ComponentFixture<GetPurchasedCouponsByPriceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GetPurchasedCouponsByPriceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GetPurchasedCouponsByPriceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
