import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GetPurchasedCouponsByTypeComponent } from './get-purchased-coupons-by-type.component';

describe('GetPurchasedCouponsByTypeComponent', () => {
  let component: GetPurchasedCouponsByTypeComponent;
  let fixture: ComponentFixture<GetPurchasedCouponsByTypeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GetPurchasedCouponsByTypeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GetPurchasedCouponsByTypeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
