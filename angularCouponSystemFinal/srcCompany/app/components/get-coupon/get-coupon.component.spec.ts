import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GetCouponComponent } from './get-coupon.component';

describe('GetCouponComponent', () => {
  let component: GetCouponComponent;
  let fixture: ComponentFixture<GetCouponComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GetCouponComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GetCouponComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
