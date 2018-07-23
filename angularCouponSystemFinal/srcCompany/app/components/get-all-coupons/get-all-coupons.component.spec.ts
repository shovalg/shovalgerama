import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GetAllCouponsComponent } from './get-all-coupons.component';

describe('GetAllCouponsComponent', () => {
  let component: GetAllCouponsComponent;
  let fixture: ComponentFixture<GetAllCouponsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GetAllCouponsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GetAllCouponsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
