import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GetAllPurchasedCouponsComponent } from './get-all-purchased-coupons.component';

describe('GetAllPurchasedCouponsComponent', () => {
  let component: GetAllPurchasedCouponsComponent;
  let fixture: ComponentFixture<GetAllPurchasedCouponsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GetAllPurchasedCouponsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GetAllPurchasedCouponsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
